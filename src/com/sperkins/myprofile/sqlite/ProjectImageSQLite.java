package com.sperkins.myprofile.sqlite;

import com.sperkins.myprofile.utils.DatabaseUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProjectImageSQLite extends SQLiteOpenHelper{

	//Project Images Table Columns
	public static final String TABLE_PROJECT_IMAGE = "ProjectImages";
	public static final String PROJECT_IMAGE_ID = "_id";
	public static final String PROJECT_IMAGE_PROJECT_ID = "project_id";
	public static final String PROJECT_IMAGE_IS_MAIN_IMAGE = "is_main_image";
	public static final String PROJECT_IMAGE_URL = "url";
	public static final String PROJECT_IMAGE_META = "meta";	//A short sentence of what the image is about
			
	static DatabaseUtils dbUtils = new DatabaseUtils();
	
	public static final String DATABASE_NAME = dbUtils.getDatabase_name();
	public static final int DATABASE_VERSION = dbUtils.getDatabase_version();
	
	//Create Image table
		public static final String CREATE_IMAGE_TABLE = "create table "
						+ TABLE_PROJECT_IMAGE + " ( "
						+ ", " + PROJECT_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
						+ ", " + PROJECT_IMAGE_PROJECT_ID + " INTEGER NOT NULL "
						+ ", " + PROJECT_IMAGE_IS_MAIN_IMAGE + " Boolean DEFAULT 0"
						+ ", " + PROJECT_IMAGE_URL + " NVARCHAR(30) NULL DEFAULT 'default.png' "
						+ ", FOREIGN KEY (" + PROJECT_IMAGE_PROJECT_ID + ") REFERENCES Projects(_id)"
						+ " );";
		
		
	
	public ProjectImageSQLite(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(CREATE_IMAGE_TABLE);
	}
	
	public void onUpgrade(SQLiteDatabase database, int oldVersion , int newVersion){
		Log.w(ProjectsSQLite.class.getName(),
				"Upgrading database from version " + oldVersion 
				+ " to " + newVersion + ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT_IMAGE);
		
		//re-create database
		onCreate(database);
	}
	
	@Override
	public void onOpen(SQLiteDatabase database){
		Log.w(ProjectsSQLite.class.getName(), "Opening database " + DATABASE_NAME);
	}

}
