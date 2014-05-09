package com.example.sqlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;

/**
 * This is the Data source class. It maintains database connection, adding new projects and fetching a list of project
 * @author alexforce
 *
 */
public class ProjectsDao {
	
	private SQLiteDatabase database;
	private ProjectsSQLite dbHelper;
	private String[] allColumns = {
			ProjectsSQLite.PROJECT_ID
		 ,	ProjectsSQLite.PROJECT_NAME
		 ,	ProjectsSQLite.PROJECT_DESCRIPTION
		 ,  ProjectsSQLite.PROJECT_LANGUAGE
		 ,  ProjectsSQLite.PROJECT_IMAGE
		 ,  ProjectsSQLite.PROJECT_DATE
	
	};
	
	public ProjectsDao(Context context){
		dbHelper = new ProjectsSQLite(context);
	}
	
	/**
	 * open the database
	 * @throws SQLException
	 */
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();	
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public Project createProject(String name, String description, String image_url, DateFormat date){
		ContentValues values = new ContentValues();
		
		//Preparing values
		values.put(ProjectsSQLite.PROJECT_NAME, name);
		values.put(ProjectsSQLite.PROJECT_DESCRIPTION, description);
		values.put(ProjectsSQLite.PROJECT_IMAGE, image_url);
		values.put(ProjectsSQLite.PROJECT_DATE, date.toString());
		
		//Insert values
		long insertId = database.insert(ProjectsSQLite.TABLE_PROJECTS, null, values);
		
		//Use cursor to perfor data
		Cursor cursor = database.query(ProjectsSQLite.TABLE_PROJECTS
				, allColumns
				, ProjectsSQLite.PROJECT_ID + " = " + insertId
				, null, null, null, null);
		
		//Return to the first element in the list
		cursor.moveToFirst();
		
		//Store information about all populate data
		Project newProject = cursorToProject(cursor);
	
		//close connection
		cursor.close();
		
		return newProject;
	}
	
	public void deleteProject(Project project){
		long id = project.getId();
		
		Log.w("Delete project", "Deleting project "+ project.getProject_name() +" id: "+ project.getId());
		
		if(id > 0){
			//delete the data
			database.delete(ProjectsSQLite.TABLE_PROJECTS
					, ProjectsSQLite.PROJECT_ID + " = " + id, null);
		}
	}
	
	/**
	 * Get all the projects.
	 *  projects can be ordered using the column name and the orderby clause.
	 * @param String orderBy Column eg: date
	 * @param String order ASC or DESC
	 */
	
	public List<Project> getAllProject(String orderByColumn,String order){
		List<Project> projects = new ArrayList<Project>();
	
		String[] ordersList = { "ASC", "DESC"};
		String orderByClause = null;
		
		if(order == null || order.isEmpty() || orderByColumn == null || orderByColumn.isEmpty()){
			
			Log.w("getAllProjects by order", "Order empty or not in the list : " + order);
		
		}else{
			//Set the order by column and order you wish to arrange data
			orderByClause = orderByColumn + " " + order;
			
		}
		
		Cursor cursor = database.query(ProjectsSQLite.TABLE_PROJECTS
				, allColumns
				, null, null, null, null, orderByColumn + " " + order);
		
		cursor.moveToFirst();
		//Loop through all data
		while(!cursor.isAfterLast()){
			Project project = cursorToProject(cursor);
			projects.add(project);
			
			//move to the next row
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return projects;
	}
	
	
	/**
	 * Map cursor data into the project object
	 * @param cursor
	 * @return {@link Project}
	 */
	private Project cursorToProject(Cursor cursor){
		Project project = new Project();
		project.setId(cursor.getLong(0));
		project.setProject_name(cursor.getString(1));
		project.setProject_description(cursor.getString(2));
		project.setLanguage(cursor.getString(3));
		project.setImage_url(cursor.getString(4));
		project.setProject_date(processDate(cursor.getString(5)));
		
		return project;
	}
	
	/**
	 * converts a string date into a Date object 
	 * @param string_date
	 * @return (sql)Date
	 */
	public Date processDate(String string_date){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = (Date) format.parse(string_date);
			
			Log.w("Convert string to date", "Convert string ("+ string_date + ") to (date "+ date.toString() +")");		
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
