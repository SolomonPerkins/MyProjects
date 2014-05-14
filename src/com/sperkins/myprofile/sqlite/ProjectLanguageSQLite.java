package com.sperkins.myprofile.sqlite;

import com.sperkins.myprofile.utils.DatabaseUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProjectLanguageSQLite extends SQLiteOpenHelper{
	
	//Language Table Columns
	public static final String TABLE_LANGUAGE = "Language";
	public static final String LANGUAGE_ID = "_id";
	public static final String LANGUAGE_PROJECT_ID = "project_id";	//reference the project
	public static final String LANGUAGE_NAME = "name";
	public static final String LANGUAGE_REFERENCE = "langauge_reference"; //Url to a wiki page about the language
	public static final String LANGUAGE_IMAGE_URL = "image_url";	//The language icon.
	
	static DatabaseUtils dbUtils = new DatabaseUtils();
	
	public static final String DATABASE_NAME = dbUtils.getDatabase_name();
	public static final int DATABASE_VERSION = dbUtils.getDatabase_version();
	
	
	//Create Language table
	public static final String CREATE_LANGUAGE_TABLE = "create table "
		+ TABLE_LANGUAGE + " ( "
		+ ", " + LANGUAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
		+ ", " + LANGUAGE_PROJECT_ID + " INTEGER NOT NULL "
		+ ", " + LANGUAGE_NAME + " NVARCHAR(20) NOT NULL"
		+ ", " + LANGUAGE_REFERENCE + " NVARCHAR(30) NULL DEFAULT '' "
		+ ", FOREIGN KEY (" + LANGUAGE_PROJECT_ID + ") REFERENCES Projects(" + LANGUAGE_PROJECT_ID +")"
		+ " );";

	
	public ProjectLanguageSQLite(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(CREATE_LANGUAGE_TABLE);
	}
	
	public void onUpgrade(SQLiteDatabase database, int oldVersion , int newVersion){
		Log.w(ProjectsSQLite.class.getName(),
				"Upgrading database from version " + oldVersion 
				+ " to " + newVersion + ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_LANGUAGE);
		
		//re-create database
		onCreate(database);
	}
	
	@Override
	public void onOpen(SQLiteDatabase database){
		Log.w(ProjectsSQLite.class.getName(), "Opening database " + DATABASE_NAME);
	}

}
