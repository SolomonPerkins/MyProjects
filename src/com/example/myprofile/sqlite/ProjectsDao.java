package com.example.myprofile.sqlite;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myprofile.models.Language;
import com.example.myprofile.models.Project;
import com.example.myprofile.models.ProjectImage;
import com.example.myprofile.models.ProjectListView;

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
		 ,  ProjectsSQLite.PROJECT_DATE
		 , 	ProjectsSQLite.PROJECT_INTRODUCTION
	};
	
	// Links the Project -> Language
	//					 -> ProjectImages
	private String getProjectsListQuery = "SELECT "
			+ " P."+ ProjectsSQLite.PROJECT_ID + " AS project_id , P."
				+ ProjectsSQLite.PROJECT_DATE +" AS project_date, P."
				+ ProjectsSQLite.PROJECT_INTRODUCTION + " AS introduction"
			+ " PL."+ProjectsSQLite.LANGUAGE_ID + " AS language_id, PL." 
				+ ProjectsSQLite.LANGUAGE_NAME + "AS language_name, PL." 
				+ ProjectsSQLite.LANGUAGE_IMAGE_URL +" AS language_image"
			+ " PI."+ ProjectsSQLite.PROJECT_IMAGE_ID + " AS image_id, PI."
				+ ProjectsSQLite.PROJECT_IMAGE_IS_MAIN_IMAGE + " AS is_main, PI."
				+ ProjectsSQLite.PROJECT_IMAGE_URL + " AS image_url, PI."
				+ ProjectsSQLite.PROJECT_IMAGE_META + " AS meta"
			
			+ " FROM " + ProjectsSQLite.TABLE_PROJECTS + " AS P" //Project
			+ " LEFT JOIN " + ProjectsSQLite.TABLE_LANGUAGE + " AS PL "	//Join Language
				+ " ON PL." + ProjectsSQLite.LANGUAGE_PROJECT_ID + "=P."+ProjectsSQLite.PROJECT_ID
			
			+ " LEFT JOIN "+ ProjectsSQLite.TABLE_PROJECT_IMAGE	+ " AS PI"//Join project image
				+ " ON PI." + ProjectsSQLite.PROJECT_IMAGE_PROJECT_ID + "= P." + ProjectsSQLite.PROJECT_ID
				+ " AND PI."+ ProjectsSQLite.PROJECT_IMAGE_IS_MAIN_IMAGE  + " = 1"	//the main image to be displayed on the list view
		
			+ " ORDER BY P."+ ProjectsSQLite.PROJECT_DATE + " DESC ";
	
	
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
	
	public Project createProject(String name, String projectDate, String introduction){
		ContentValues values = new ContentValues();
		
		//Preparing values
		values.put(ProjectsSQLite.PROJECT_NAME, name);
		values.put(ProjectsSQLite.PROJECT_DATE, projectDate);
		values.put(ProjectsSQLite.PROJECT_INTRODUCTION, introduction);
		
		//Insert values
		long insertId = database.insert(ProjectsSQLite.TABLE_PROJECTS, null, values);
		
		//Store results returned in a cursor.
		Cursor cursor = database.query(ProjectsSQLite.TABLE_PROJECTS
				, allColumns
				, ProjectsSQLite.PROJECT_ID + " = " + insertId
				, null, null, null, null);
		
		//Return to the first element in the list
		cursor.moveToFirst();
		
		//Store information about the project created
		Project newProject = cursorToProject(cursor);
		
		//close connection
		cursor.close();
		
		return newProject;
	}
	
	/**
	 * 
	 * @param project
	 */
	public void deleteProject(Project project){
		long id = project.getId();
		
		Log.w("Delete project", "Deleting project "+ project.getProjectName() +" id: "+ project.getId());
		
		if(id <= 0 ){
			Log.w("Delete project", "unable to delete project with id : "+ id);
		}else{
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
	 * @return List < {@link Project} >
	 */
	
	public List<ProjectListView> getAllProject(String orderByColumn,String order){
		List<ProjectListView> projects = new ArrayList<ProjectListView>();
	
		String[] ordersList = { "ASC", "DESC"};
		String orderByClause = null;
		
		if(order == null || order.isEmpty() || orderByColumn == null || orderByColumn.isEmpty()){
			
			Log.w("getAllProjects by order", "Order empty or not in the list : " + order);
		
		}else{
			//Set the order by column and order you wish to arrange data
			orderByClause = orderByColumn + " " + order;
			
		}
		
		//TODO: Use rawQuery instead
//		Cursor cursor = database.query(ProjectsSQLite.TABLE_PROJECTS
//				, allColumns
//				, null, null, null, null, orderByColumn + " " + order);
		
		Cursor cursor = database.rawQuery(getProjectsListQuery, null);
		
		cursor.moveToFirst();
		//Loop through all data
		while(!cursor.isAfterLast()){
			ProjectListView project = cursorToProjectListView(cursor);
			projects.add(project);
			
			//move to the next row
			cursor.moveToNext();
		}
		
		Log.w("getAllProjects ", "Returning " + projects.size() + "projects");
		
		
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
		project.setProjectName(cursor.getString(1));
		project.setProjectDate(processDate(cursor.getString(3)));
		
		return project;
	}
	
	/**
	 * Will populate all the model data for each component on the list view
	 * @param cursor
	 * @return
	 */
	private ProjectListView cursorToProjectListView(Cursor cursor){
		ProjectListView listViewItem = new ProjectListView();
		
		Project project = cursorToProject(cursor);
		Language language = new Language();	//should get the language details
		ProjectImage image = new ProjectImage();	// should get the image details
		
		//populating each model
		listViewItem.setProject(project);
		listViewItem.setLanguage(language);
		listViewItem.setProjectImage(image);
		
		return listViewItem;
		
	}
	
	/**
	 * converts a string date into a Date object 
	 * @param string_date
	 * @return (sql)Date
	 */
	public Date processDate(String string_date){
			//TODO: string date should be converted to Date format
			

			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				
				date = (Date) format.parse(string_date);
				
				Log.w("Convert string to date", "Convert string ("+ string_date + ") to (date "+ date.toString() +")");		
				return date;

			} catch (ParseException e) {
				
				e.printStackTrace();
				return null;
			}
						
		
	}
}
