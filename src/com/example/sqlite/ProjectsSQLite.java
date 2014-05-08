package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProjectsSQLite extends SQLiteOpenHelper{
	
	public static final String TABLE_PROJECTS = "Projects";
	public static final String COLUMN_ID = "_id";
	public static final String	COLUMN_COMMENT = "Store the list of projects";
	
	public static final String DATABASE_NAME = "projects.db";
	public static final int DATABASE_VERSION = 1;
	
	//Create database
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PROJECTS + "(" 
			+ COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_COMMENT
			+ " text not null;";
			
	
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
