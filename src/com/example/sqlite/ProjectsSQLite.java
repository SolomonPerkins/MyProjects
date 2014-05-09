package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProjectsSQLite extends SQLiteOpenHelper{
	
	public static final String TABLE_PROJECTS = "Projects";
	public static final String  PROJECT_ID = "id";
	public static final String	PROJECT_NAME = "name";
	public static final String	PROJECT_DESCRIPTION = "description";
	public static final String	PROJECT_IMAGE = "image_url";
	public static final String	PROJECT_LANGUAGE = "language";	//the programming language used
	public static final String	PROJECT_DATE = "date";
	
	//The default image URL
	public static final String DEFAULT_IMAGE_URL = "default.png";
	
	
	public static final String DATABASE_NAME = "projects.db";
	public static final int DATABASE_VERSION = 1;
	
	//Create database
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PROJECTS + "( " 
			+ PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
			+ ", " + PROJECT_NAME + " NVARCHAR(100) not null "
			+ ", " + PROJECT_DESCRIPTION + " NVARCHAR(1000) DEFAULT ''"
			+ ", " + PROJECT_LANGUAGE + " NVARCHAR(100) NOT NULL"
			+ ", " + PROJECT_IMAGE + " NVARCHAR(100) NOT NULL DEFAULT "
			+ ", " + PROJECT_DATE + "DATE"
			+ " );";
			
	
	public ProjectsSQLite(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(DATABASE_CREATE);
	}
	
	/**
	 * Used if you want to upgrade your database version
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
		Log.w(ProjectsSQLite.class.getName(),
				"Upgrading database from version " + oldVersion 
				+ " to " + newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
		
		onCreate(database);
	}
}
