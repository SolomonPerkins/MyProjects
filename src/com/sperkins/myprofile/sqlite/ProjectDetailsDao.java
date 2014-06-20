package com.sperkins.myprofile.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.sperkins.myprofile.models.Project;
import com.sperkins.myprofile.models.ProjectDetails;
import com.sperkins.myprofile.models.ProjectFeature;
import com.sperkins.myprofile.utils.DatabaseUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProjectDetailsDao extends BasicCRUD{

	private SQLiteDatabase database;
	private ProjectsSQLite dbHelper;
	private String[] allColumns = {
			ProjectDetailsSQLite.PROJECT_DETAILS_ID
		,	ProjectDetailsSQLite.PROJECT_DETAILS_PROJECT_ID
		,	ProjectDetailsSQLite.PROJECT_DETAILS_DESCRIPTION
		,	ProjectDetailsSQLite.PROJECT_DETAILS_DIFFICULTY
	};
	
	
	private DatabaseUtils dbUtils = new DatabaseUtils();
	
	
	public ProjectDetailsDao(Context context){
		dbHelper = new ProjectsSQLite(context);
	}
	
	
	public void open(){
		database = dbHelper.getWritableDatabase();
		
		this.setDatabase(database);
		this.setTable(ProjectDetailsSQLite.TABLE_PROJECT_DETAILS);
	}
	
	public void close(){
		database.close();
	}
	
	/**
	 * 
	 * @param projectID
	 * @param description
	 * @param difficulty
	 * @return {@link ProjectDetails}
	 */
	public ProjectDetails createProjectDetails(int projectID, String description, String difficulty){
		ContentValues values = new ContentValues();
		
		//Preparing values
		values.put(ProjectDetailsSQLite.PROJECT_DETAILS_PROJECT_ID, projectID);
		values.put(ProjectDetailsSQLite.PROJECT_DETAILS_DESCRIPTION, description);
		values.put(ProjectDetailsSQLite.PROJECT_DETAILS_DIFFICULTY, difficulty);
	
		Cursor cursor = this.create(values, allColumns, ProjectDetailsSQLite.PROJECT_DETAILS_ID);

		//Store information about the project created
		ProjectDetails newProject = dbUtils.cursorToProjectDetails(cursor);
		
		//close connection
		cursor.close();
		
		return newProject;
	
		
	}
	
	public List<ProjectFeature> getProjectFeatures(long project_id){
		if(project_id <= 0 ){
			Log.w("get project features", "unable to get features with id : "+ project_id);	
			
			return null;
		}
		
		String getFeaturesListQuery = "SELECT "
				+ " PF." + ProjectsSQLite.PROJECT_FEATURES_ID + " AS feature_id "
				+ ", PF." + ProjectsSQLite.PROJECT_FEATURES_PROJECT_FEATURE + " AS feature"
				+ ", PF." + ProjectsSQLite.PROJECT_FEATURES_PROJECT_TYPE + " AS feature_type"
				+ " FROM " + ProjectsSQLite.TABLE_PROJECT_FEATURES + " AS PF"
				+ " WHERE " + ProjectsSQLite.PROJECT_FEATURES_PROJECT_ID +" = " + project_id;
		
		
		List<ProjectFeature> features = new ArrayList<ProjectFeature>();
		
		Cursor cursor = database.rawQuery(getFeaturesListQuery, null);
		
		cursor.moveToFirst();
		//Loop through all data
		while(!cursor.isAfterLast()){
			
			ProjectFeature afeature = dbUtils.cursorToFeatures(cursor );
			
			if(afeature != null){
				features.add(afeature);
			}
			
			cursor.moveToNext();
			
		}
		return features;
	}
	
	/**
	 * 
	 * @param projectDetails
	 */
	public void deleteProjectDetails(ProjectDetails projectDetails){
		long id = projectDetails.getId();
		
		Log.w("Delete project", "Deleting project details "+ projectDetails.getProjectId());
		
		if(id <= 0 ){
			Log.w("Delete project details", "unable to delete project with id : "+ id);
		}else{
			//delete the data
			database.delete(ProjectDetailsSQLite.TABLE_PROJECT_DETAILS
					, ProjectDetailsSQLite.PROJECT_DETAILS_ID + " = " + id, null);
			
		}
	}
	
	/**
	 * Returns only the details not extracted by projectsDao
	 * @return
	 */
	public ProjectDetails getProjectDetails(long project_id){
		
		ProjectDetails details = new ProjectDetails();
		
		//Get details
		Cursor cursor = database.query(ProjectsSQLite.TABLE_PROJECT_DETAILS
				, allColumns
				, ProjectsSQLite.PROJECT_DETAILS_PROJECT_ID + "=?" 
				, new String[] { String.valueOf(project_id)}
				, null, null, null);
		
		details = dbUtils.cursorToProjectDetails(cursor);
		
		//Get the features
		details.setProjectFeaturesList(this.getProjectFeatures(project_id));
		
		return details;
	}
	
}



