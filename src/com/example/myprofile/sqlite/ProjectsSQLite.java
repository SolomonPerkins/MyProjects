package com.example.myprofile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myprofile.utils.DatabaseUtils;

public class ProjectsSQLite extends SQLiteOpenHelper{
	
	//Projects TABLE Columns
	public static final String TABLE_PROJECTS = "Projects";
	public static final String  PROJECT_ID = "_id";
	public static final String	PROJECT_NAME = "name";
	public static final String	PROJECT_INTRODUCTION = "introduction";
	public static final String	PROJECT_DATE = "date";
		
	
	static DatabaseUtils dbUtils = new DatabaseUtils();
	
	public static final String DATABASE_NAME = dbUtils.getDatabase_name();
	public static final int DATABASE_VERSION = dbUtils.getDatabase_version();
	
	//Create projects table
	private static final String CREATE_PROJECTS_TABLE = "create table "
			+ TABLE_PROJECTS + " ( " 
			+ PROJECT_ID + " INTEGER primary key autoincrement "
			+ ", " + PROJECT_NAME + " NVARCHAR(500) not null "
			+ ", " + PROJECT_INTRODUCTION + " NVARCHAR(200) NOT NULL "
			+ ", " + PROJECT_DATE + " DATE "
			+ " );";
	
				
	public ProjectsSQLite(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * Creates the list of table
	 */
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(CREATE_PROJECTS_TABLE);

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

	@Override
	public void onOpen(SQLiteDatabase database){
		Log.w(ProjectsSQLite.class.getName(), "Opening database " + DATABASE_NAME);
	}
	
}
