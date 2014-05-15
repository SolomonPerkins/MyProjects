package com.sperkins.myprofile.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sperkins.myprofile.models.Project;
import com.sperkins.myprofile.models.ProjectImage;
import com.sperkins.myprofile.models.ProjectImage;
import com.sperkins.myprofile.utils.DatabaseUtils;

public class ProjectImageDao {

	private SQLiteDatabase database;
	private ProjectsSQLite dbHelper;
	private String[] allColumns = {
			ProjectsSQLite.PROJECT_IMAGE_ID
		,	ProjectsSQLite.PROJECT_IMAGE_PROJECT_ID
		,	ProjectsSQLite.PROJECT_IMAGE_IS_MAIN_IMAGE
		,	ProjectsSQLite.PROJECT_IMAGE_URL
		,	ProjectsSQLite.PROJECT_IMAGE_META
	};
	
	private DatabaseUtils dbUtils = new DatabaseUtils();
	
	public ProjectImageDao(Context context){
		dbHelper = new ProjectsSQLite(context);
	}
	
	public void open() throws Exception{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	/**
	 * Create a project image
	 * @param project_id
	 * @param is_main_image
	 * @param image_url
	 * @param meta
	 * @return
	 */
	public long createProjectImage(long project_id, Boolean is_main_image, String image_url, String meta ){
		
		if(project_id == 0){
			Log.e(this.getClass() + " createProjectImage ", "project_id is "+ project_id);
			
			return -1;
		}
		
		ProjectImage image = new ProjectImage();
		ContentValues values = new ContentValues();
		
		values.put(ProjectsSQLite.PROJECT_IMAGE_PROJECT_ID, project_id);
		values.put(ProjectsSQLite.PROJECT_IMAGE_IS_MAIN_IMAGE, dbUtils.getBooleanConvert(is_main_image));
		values.put(ProjectsSQLite.PROJECT_IMAGE_URL, image_url);
		values.put(ProjectsSQLite.PROJECT_IMAGE_META, meta);
		
		long insertId = database.insert(ProjectsSQLite.TABLE_PROJECT_IMAGE, null, values);
		
		if(insertId < 0){
			Log.e(this.getClass() + " createProjectImage", "error inserting value");
		}
		
		return insertId;
	}
	
	/**
	 * delete a project
	 * @param image
	 */
	public int deleteProjectImage(ProjectImage image){
		long id = image.getId();
		
		Log.w(this.getClass() +" Delete projectImage", "Deleting image "+ image.getProjectImage_url() +" id: "+ image.getId());
		
		if(id <= 0 ){
			Log.w("Delete project", "unable to delete project with id : "+ id);
			return -1;
		}else{
			//delete the data
			 return database.delete(ProjectsSQLite.TABLE_PROJECT_IMAGE
					, ProjectsSQLite.PROJECT_IMAGE_ID + " = " + id, null);
			
		}
	}

	/**
	 * 
	 * @param orderByColumn
	 * @param order
	 * @return
	 */
	public List<ProjectImage> getAllProjectImages(String orderByColumn,String order){
		List<ProjectImage> images = new ArrayList<ProjectImage>();
	
		String[] ordersList = { "ASC", "DESC"};
		String orderByClause = null;
		
		if(order == null || order.isEmpty() || orderByColumn == null || orderByColumn.isEmpty()){
			
			Log.w("getAllProjects by order", "Order empty or not in the list : " + order);
		
		}else{
			//Set the order by column and order you wish to arrange data
			orderByClause = orderByColumn + " " + order;
			
		}
		
		//TODO: Use rawQuery instead
		Cursor cursor = database.query(ProjectsSQLite.TABLE_PROJECT_IMAGE
				, allColumns
				, null, null, null, null, orderByColumn + " " + order);
		
		cursor.moveToFirst();
		
		//Loop through all data
		while(!cursor.isAfterLast()){
			ProjectImage image = dbUtils.cursorToImage(cursor);
			images.add(image);
			
			//move to the next row
			cursor.moveToNext();
		}
		
		Log.w("getAllProjects ", "Returning " + images.size() + "projects");
		
		
		cursor.close();
		
		return images;
	}
	
}
