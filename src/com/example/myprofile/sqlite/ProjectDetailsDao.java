package com.example.myprofile.sqlite;

import com.example.myprofile.models.Project;
import com.example.myprofile.models.ProjectDetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProjectDetailsDao extends BasicCRUD{

	private SQLiteDatabase database;
	private ProjectDetailsSQLite dbHelper;
	private String[] allColumns = {
			ProjectDetailsSQLite.PROJECT_DETAILS_ID
		,	ProjectDetailsSQLite.PROJECT_DETAILS_PROJECT_ID
		,	ProjectDetailsSQLite.PROJECT_DETAILS_DESCRIPTION
		,	ProjectDetailsSQLite.PROJECT_DETAILS_DIFFICULTY
	};
	
	
	
	
	public ProjectDetailsDao(Context context){
		dbHelper = new ProjectDetailsSQLite(context);
	}
	
	
	public void open(){
		database = dbHelper.getWritableDatabase();
		
		this.setDatabase(database);
		this.setTable(ProjectDetailsSQLite.TABLE_PROJECT_DETAILS);
	}
	
	public void close(){
		database.close();
	}
	
	public ProjectDetails createProjectDetails(int projectID, String description, String difficulty){
		ContentValues values = new ContentValues();
		
		//Preparing values
		values.put(ProjectDetailsSQLite.PROJECT_DETAILS_PROJECT_ID, projectID);
		values.put(ProjectDetailsSQLite.PROJECT_DETAILS_DESCRIPTION, description);
		values.put(ProjectDetailsSQLite.PROJECT_DETAILS_DIFFICULTY, difficulty);
	
		Cursor cursor = this.create(values, allColumns, ProjectDetailsSQLite.PROJECT_DETAILS_ID);
//		//Insert values
//		long insertId = database.insert(ProjectDetailsSQLite.TABLE_PROJECT_DETAILS, null, values);
//	
//		//Store results returned in a cursor.
//		Cursor cursor = database.query(ProjectDetailsSQLite.TABLE_PROJECT_DETAILS
//				, allColumns
//				, ProjectDetailsSQLite.PROJECT_DETAILS_ID + " = " + insertId
//				, null, null, null, null);
//		
//		//Return to the first element in the list
//		cursor.moveToFirst();
//		
		//Store information about the project created
		ProjectDetails newProject = cursorToProjectDetails(cursor);
		
		//close connection
		cursor.close();
		
		return newProject;
	
		
	}
	
	public void deleteProjectDetails(ProjectDetails projectDetails){
		long id = projectDetails.getId();
		
		Log.w("Delete project", "Deleting project details "+ projectDetails.getProject_id());
		
		if(id <= 0 ){
			Log.w("Delete project details", "unable to delete project with id : "+ id);
		}else{
			//delete the data
			database.delete(ProjectDetailsSQLite.TABLE_PROJECT_DETAILS
					, ProjectDetailsSQLite.PROJECT_DETAILS_ID + " = " + id, null);
			
		}
	}
	
	public ProjectDetails cursorToProjectDetails(Cursor cursor){
		
		return null;
	}
	
	
}



