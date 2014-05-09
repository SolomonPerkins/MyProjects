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
			+ TABLE_PROJECTS + " ( " 
			+ PROJECT_ID + " INTEGER primary key autoincrement "
			+ ", " + PROJECT_NAME + " text not null "
			+ ", " + PROJECT_DESCRIPTION + " text "
			+ ", " + PROJECT_LANGUAGE + " text not null "
			+ ", " + PROJECT_IMAGE + " text not null DEFAULT \'default.png\'"
			+ ", " + PROJECT_DATE + " numeric "
			+ " );";
			
	
	public ProjectsSQLite(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * Creates the list of table
	 */
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
