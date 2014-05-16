package com.sperkins.myprofile.sqlite;

import com.sperkins.myprofile.utils.DatabaseUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BiographicsSQLite extends SQLiteOpenHelper {
	
	//biographic Table Columns
	public static final String TABLE_BIOGRAPHIC = "Biographic";
	public static final String BIOGRAPHIC_ID = "_id";
	public static final String BIOGRAPHIC_PROJECT_ID = "project_id";	
	public static final String BIOGRAPHIC_TYPE = "type";
	public static final String BIOGRAPHIC_DETAILS = "details"; 
	public static final String BIOGRAPHIC_DATE = "date";	
	public static final String BIOGRAPHIC_CATEGORY = "category";	
	
	static DatabaseUtils dbUtils = new DatabaseUtils();
	
	public static final String DATABASE_NAME = dbUtils.getDatabase_name();
	public static final int DATABASE_VERSION = dbUtils.getDatabase_version();
	
	//Create biographic table
	public static final String CREATE_BIOGRAPHIC_TABLE = "create table "
			+ TABLE_BIOGRAPHIC + " ( "
			+ ", " + BIOGRAPHIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
			+ ", " + BIOGRAPHIC_PROJECT_ID + " INTEGER NOT NULL "
			+ ", " + BIOGRAPHIC_TYPE + " NVARCHAR(40) NOT NULL"
			+ ", " + BIOGRAPHIC_DETAILS + " NVARCHAR(500) NULL DEFAULT '' "
			+ ", " + BIOGRAPHIC_CATEGORY + " NVARCHAR(20) NOT NULL"
			+ ", FOREIGN KEY (" + BIOGRAPHIC_PROJECT_ID + ") REFERENCES Projects(_id)"
			+ " );";
	
	public BiographicsSQLite(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(CREATE_BIOGRAPHIC_TABLE);
	}
	
	public void onUpgrade(SQLiteDatabase database, int oldVersion , int newVersion){
		Log.w(ProjectsSQLite.class.getName(),
				"Upgrading database from version " + oldVersion 
				+ " to " + newVersion + ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_BIOGRAPHIC);
		
		//re-create database
		onCreate(database);
	}
	
	@Override
	public void onOpen(SQLiteDatabase database){
		Log.w(ProjectsSQLite.class.getName(), "Opening database " + DATABASE_NAME);
	}


}
