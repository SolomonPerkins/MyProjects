package com.example.myprofile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myprofile.utils.DatabaseUtils;

public class ProjectDetailsSQLite extends SQLiteOpenHelper{
	
	//ProjectDetails Table
	public static final String TABLE_PROJECT_DETAILS = "ProjectDetails";
	public static final String  PROJECT_DETAILS_ID = "_id";
	public static final String  PROJECT_DETAILS_PROJECT_ID = "project_id";	//reference the project
	public static final String  PROJECT_DETAILS_DESCRIPTION = "description";
	public static final String  PROJECT_DETAILS_DIFFICULTY = "difficulty";
	
	static DatabaseUtils dbUtils = new DatabaseUtils();
	public static final String DATABASE_NAME = dbUtils.getDatabase_name();
	public static final int DATABASE_VERSION = dbUtils.getDatabase_version();

	//Database table syntax
	public static final String CREATE_PROJECT_DETAILS_TABLE = "create table "
			+ TABLE_PROJECT_DETAILS + " ( "
			+ ", " + PROJECT_DETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
			+ ", " + PROJECT_DETAILS_PROJECT_ID + " INTEGER NOT NULL "
			+ ", " + PROJECT_DETAILS_DESCRIPTION + " NVARCHAR(1000) "
			+ ", " + PROJECT_DETAILS_DIFFICULTY + " NVARCHAR(15) DEFAULT 'Easy' "
			+ ", FOREIGN KEY (" + PROJECT_DETAILS_PROJECT_ID + ") REFERENCES Projects(_id)"
			+ " );";
			
	
	public ProjectDetailsSQLite(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(CREATE_PROJECT_DETAILS_TABLE);
	}
	
	public void onUpgrade(SQLiteDatabase database, int oldVersion , int newVersion){
		Log.w(ProjectsSQLite.class.getName(),
				"Upgrading database from version " + oldVersion 
				+ " to " + newVersion + ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT_DETAILS);
		
		//re-create database
		onCreate(database);
	}
	
	@Override
	public void onOpen(SQLiteDatabase database){
		Log.w(ProjectsSQLite.class.getName(), "Opening database " + DATABASE_NAME);
	}

}
