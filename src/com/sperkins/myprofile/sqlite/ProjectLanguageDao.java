package com.sperkins.myprofile.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sperkins.myprofile.models.Language;
import com.sperkins.myprofile.utils.DatabaseUtils;

public class ProjectLanguageDao {
	private SQLiteDatabase database;
	private ProjectsSQLite dbHelper;
	private String[] allColumns ={
			ProjectsSQLite.LANGUAGE_ID
		,	ProjectsSQLite.LANGUAGE_PROJECT_ID
		,	ProjectsSQLite.LANGUAGE_NAME
		,	ProjectsSQLite.LANGUAGE_REFERENCE
		,	ProjectsSQLite.LANGUAGE_IMAGE_URL
	};

	private DatabaseUtils dbUtils = new DatabaseUtils();
	
	public ProjectLanguageDao(Context context){
		dbHelper = new ProjectsSQLite(context);
	}
	
	public void open(){
		database = dbHelper.getWritableDatabase();
		
	}
	
	public void close(){
		database.close();
	}
	
	
	public long createProjectLanguage(long project_id, String name, String reference){
		if(project_id == 0){
			Log.e(this.getClass() + " createProjectLanguage ", "project_id is "+ project_id);
			
			return -1;
		}
		
		//TODO: Create function to return the url based on name
		String image_url = null;
		
		Language language = new Language();
		ContentValues values = new ContentValues();
		
		values.put(ProjectsSQLite.LANGUAGE_PROJECT_ID, project_id);
		values.put(ProjectsSQLite.LANGUAGE_NAME, name);
		values.put(ProjectsSQLite.LANGUAGE_REFERENCE, reference);
		values.put(ProjectsSQLite.LANGUAGE_IMAGE_URL, image_url);
		
		long insertId = database.insert(ProjectsSQLite.TABLE_PROJECT_IMAGE, null, values);
		
		if(insertId < 0){
			Log.e(this.getClass() + " language", "error inserting value");
		}
		
		return insertId;
	}
	
	/**
	 * delete a project
	 * @param language
	 */
	public int deleteLanguage(Language language){
		long id = language.getId();
		
		Log.w(this.getClass() +" Delete language", "Deleting language "+ language.getLanguage_name() +" id: "+ language.getId());
		
		if(id <= 0 ){
			Log.w("Delete language", "unable to delete language : "+ language.getLanguage_name());
			return -1;
		}else{
			//delete the data
			 return database.delete(ProjectsSQLite.TABLE_LANGUAGE
					, ProjectsSQLite.LANGUAGE_ID + " = " + id, null);
			
		}
	}

	/**
	 * 
	 * @param orderByColumn
	 * @param order
	 * @return
	 */
	public List<Language> getAllLanguages(String orderByColumn,String order){
		List<Language> language = new ArrayList<Language>();
	
		String[] ordersList = { "ASC", "DESC"};
		String orderByClause = null;
		
		if(order == null || order.isEmpty() || orderByColumn == null || orderByColumn.isEmpty()){
			
			Log.w("getAllLanguages by order", "Order empty or not in the list : " + order);
		
		}else{
			//Set the order by column and order you wish to arrange data
			orderByClause = orderByColumn + " " + order;
			
		}
		
		//TODO: Use rawQuery instead
		Cursor cursor = database.query(ProjectsSQLite.TABLE_LANGUAGE
				, allColumns
				, null, null, null, null, orderByColumn + " " + order);
		
		cursor.moveToFirst();
		
		//Loop through all data
		while(!cursor.isAfterLast()){
			Language alanguage = dbUtils.cursorToLanguage(cursor);
			language.add(alanguage);
			
			//move to the next row
			cursor.moveToNext();
		}
		
		Log.w("getAllLanguages ", "Returning " + language.size() + "languages");
				
		cursor.close();
		
		return language;
	}
}
