package com.sperkins.myprofile.sqlite;

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

import com.sperkins.myprofile.models.Language;
import com.sperkins.myprofile.models.Project;
import com.sperkins.myprofile.models.ProjectImage;
import com.sperkins.myprofile.models.ProjectListView;
import com.sperkins.myprofile.utils.DatabaseUtils;

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
	
	private DatabaseUtils dbUtils = new DatabaseUtils();
	
	// Links the Project -> Language
	//					 -> ProjectImages
	private String getProjectsListQuery = "SELECT "
			+ " P." + ProjectsSQLite.PROJECT_ID + " AS project_id , "	//0
			+ " P." + ProjectsSQLite.PROJECT_NAME +" AS project_name, " //1
			+ " (P." + ProjectsSQLite.PROJECT_DATE +") AS project_date, " //2
			+ " P." + ProjectsSQLite.PROJECT_INTRODUCTION + " AS project_introduction, "	//3
			+ " PL."+ ProjectsSQLite.LANGUAGE_ID + " AS language_id, " //4
			+ " PL."+ ProjectsSQLite.LANGUAGE_NAME + " AS language_name, "	//5 
			+ " PL."+ ProjectsSQLite.LANGUAGE_IMAGE_URL +" AS language_image, "	//6
			+ " PI."+ ProjectsSQLite.PROJECT_IMAGE_ID + " AS image_id, " 	//7
			+ " PI."+ ProjectsSQLite.PROJECT_IMAGE_IS_MAIN_IMAGE + " AS is_main, "	//8 
			+ " PI."+ ProjectsSQLite.PROJECT_IMAGE_URL + " AS image_url, " 	//9
			+ " PI."+ ProjectsSQLite.PROJECT_IMAGE_META + " AS image_meta"	//10
			
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
		Project newProject = dbUtils.cursorToProject(cursor);
		
		//close connection
		cursor.close();
		
		return newProject;
	}
	
	/**
	 * 
	 * @param project
	 */
	public int deleteProject(Project project){
		long id = project.getId();
		
		Log.w("Delete project", "Deleting project "+ project.getProjectName() +" id: "+ project.getId());
		
		if(id <= 0 ){
			Log.w("Delete project", "unable to delete project with id : "+ id);
			return -1;
		}else{
			//delete the data
			return database.delete(ProjectsSQLite.TABLE_PROJECTS
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
		
		//Link projects to language, main image.
		Cursor cursor = database.rawQuery(getProjectsListQuery, null);
		
		cursor.moveToFirst();
		
		//Loop through all data
		while(!cursor.isAfterLast()){
			ProjectListView project = dbUtils.cursorToProjectListView(cursor);
			
			if(project != null){
				projects.add(project);
			}
//			Log.w("Data: ", cursor.getLong(0) + " " + cursor.getString(2));
			
			//move to the next row
			cursor.moveToNext();
		}
		
		Log.w("getAllProjects ", "Returning " + projects.size() + "projects");
		
		
		cursor.close();
		
		return projects;
	}
	
	
	public void insertDa(){
		ContentValues insert = new ContentValues();
		
		//project
		insert.put(ProjectsSQLite.PROJECT_NAME, "Trial");
		insert.put(ProjectsSQLite.PROJECT_DATE, "2014-04-04");
		insert.put(ProjectsSQLite.PROJECT_INTRODUCTION, "Basic introduction");
		
		long project_id = database.insert(ProjectsSQLite.TABLE_PROJECTS, null, insert);
		
		insert = new ContentValues();
		
		//Language
		insert.put(ProjectsSQLite.LANGUAGE_PROJECT_ID, project_id);
		insert.put(ProjectsSQLite.LANGUAGE_NAME, "C++");
		insert.put(ProjectsSQLite.LANGUAGE_IMAGE_URL, "programming_language.png");
		
		long lan_id = database.insert(ProjectsSQLite.TABLE_LANGUAGE, null, insert);
		
		insert = new ContentValues();
		
		//Image
		insert.put(ProjectsSQLite.PROJECT_IMAGE_PROJECT_ID, project_id);
		insert.put(ProjectsSQLite.PROJECT_IMAGE_IS_MAIN_IMAGE, 1);
		insert.put(ProjectsSQLite.PROJECT_IMAGE_URL, "car.jpg");
		insert.put(ProjectsSQLite.PROJECT_IMAGE_META, "of course it is a car");
		
		long image_id = database.insert(ProjectsSQLite.TABLE_PROJECT_IMAGE, null, insert);
		
		Log.w("Insert", "project id: "+ project_id  + " language " + lan_id + " image id : "+ image_id);
		
	}	
}
