package com.sperkins.myprofile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

	
	
	public ProjectLanguageDao(Context context){
		dbHelper = new ProjectsSQLite(context);
	}
	
	public void open(){
		database = dbHelper.getWritableDatabase();
		
	}
	
	public void close(){
		database.close();
	}
	
	
	
}
