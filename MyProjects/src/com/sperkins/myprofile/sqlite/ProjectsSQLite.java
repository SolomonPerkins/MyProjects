package com.sperkins.myprofile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sperkins.myprofile.utils.DatabaseUtils;

public class ProjectsSQLite extends SQLiteOpenHelper {

	// Projects TABLE Columns
	public static final String TABLE_PROJECTS = "Projects";
	public static final String PROJECT_ID = "id";
	public static final String PROJECT_NAME = "name";
	public static final String PROJECT_INTRODUCTION = "introduction";
	public static final String PROJECT_DATE = "date";

	// ProjectDetails Table Columns
	public static final String TABLE_PROJECT_DETAILS = "ProjectDetails";
	public static final String PROJECT_DETAILS_ID = "id";
	public static final String PROJECT_DETAILS_PROJECT_ID = "project_id"; // reference the project
	public static final String PROJECT_DETAILS_DESCRIPTION = "description";
	public static final String PROJECT_DETAILS_DIFFICULTY = "difficulty";

	//Project Feature
	public static final String TABLE_PROJECT_FEATURES = "ProjectFeatures";
	public static final String PROJECT_FEATURES_ID = "id";
	public static final String PROJECT_FEATURES_PROJECT_ID = "project_id"; // reference the project
	public static final String PROJECT_FEATURES_PROJECT_FEATURE = "feature";
	public static final String PROJECT_FEATURES_PROJECT_TYPE = "type";
			
	
	// Language Table Columns
	public static final String TABLE_LANGUAGE = "Language";
	public static final String LANGUAGE_ID = "id";
	public static final String LANGUAGE_PROJECT_ID = "project_id"; // reference the project
	public static final String LANGUAGE_NAME = "name";
	public static final String LANGUAGE_REFERENCE = "langauge_reference"; // Url to a wiki page about the language
	public static final String LANGUAGE_IMAGE_URL = "image_url"; // The language icon.

	// Project Images Table Columns
	public static final String TABLE_PROJECT_IMAGE = "ProjectImages";
	public static final String PROJECT_IMAGE_ID = "id";
	public static final String PROJECT_IMAGE_PROJECT_ID = "project_id";
	public static final String PROJECT_IMAGE_IS_MAIN_IMAGE = "is_main_image";
	public static final String PROJECT_IMAGE_URL = "url";
	public static final String PROJECT_IMAGE_META = "meta"; // A short sentence of what the image is about

	// Biographic
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

	// Create projects table
	private static final String CREATE_PROJECTS_TABLE = "create table "
			+ TABLE_PROJECTS + " ( " + PROJECT_ID + " INTEGER primary key autoincrement " 
			+ ", " + PROJECT_NAME + " NVARCHAR(500) not null " 
			+ ", " + PROJECT_INTRODUCTION + " NVARCHAR(200) NOT NULL " 
			+ ", " + PROJECT_DATE + " DATE "
			+ " );";

	// Create Project details table
	public static final String CREATE_PROJECT_DETAILS_TABLE = "create table "
			+ TABLE_PROJECT_DETAILS + " ( " 
			+ PROJECT_DETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " 
			+ ", " + PROJECT_DETAILS_PROJECT_ID + " INTEGER NOT NULL " 
			+ ", " + PROJECT_DETAILS_DESCRIPTION + " NVARCHAR(1000) " 
			+ ", " + PROJECT_DETAILS_DIFFICULTY + " NVARCHAR(15) DEFAULT 'Easy' "
			+ ", FOREIGN KEY (" + PROJECT_DETAILS_PROJECT_ID + ") REFERENCES Projects(" + PROJECT_ID + ")" 
			+ " );";
	
	//Create project features table
	public static final String CREATE_PROJECT_FEATURE_TABLE = "create table "
			+ TABLE_PROJECT_FEATURES + " ( "
			+ PROJECT_FEATURES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
			+ ", " + PROJECT_FEATURES_PROJECT_ID + " INTEGER NOT NULL "
			+ ", " + PROJECT_FEATURES_PROJECT_FEATURE + " NVARCHAR(300) NOT NULL "
			+ ", " + PROJECT_FEATURES_PROJECT_TYPE + " NVARCHAR(20) NULL"
			+ ", FOREIGN KEY (" + PROJECT_FEATURES_PROJECT_ID + ") REFERENCES Projects(" + PROJECT_ID + ")"
			+ " );";
			
	// Create Language table
	public static final String CREATE_LANGUAGE_TABLE = "create table "
			+ TABLE_LANGUAGE + " ( "  
			+ LANGUAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " 
			+ ", " + LANGUAGE_PROJECT_ID + " INTEGER NOT NULL " 
			+ ", " + LANGUAGE_NAME + " NVARCHAR(20) NOT NULL" 
			+ ", " + LANGUAGE_REFERENCE + " NVARCHAR(30) NULL DEFAULT '' " 
			+ ", " + LANGUAGE_IMAGE_URL + " NVARCHAR(50) NULL DEFAULT 'programming_language.png' "
			+ ", FOREIGN KEY (" + LANGUAGE_PROJECT_ID + ") REFERENCES Projects(" + PROJECT_ID + ")"
			+ " );";

	// Create Image table
	public static final String CREATE_IMAGE_TABLE = "create table "
			+ TABLE_PROJECT_IMAGE + " ( " 
			+ PROJECT_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " 
			+ ", " + PROJECT_IMAGE_PROJECT_ID + " INTEGER NOT NULL " 
			+ ", " + PROJECT_IMAGE_IS_MAIN_IMAGE + " Boolean DEFAULT 0 " 
			+ ", " + PROJECT_IMAGE_URL + " NVARCHAR(30) NULL DEFAULT 'default.png' "
			+ ", " + PROJECT_IMAGE_META + " NVARCHAR(100) NULL DEFAULT '' "
			+ ", FOREIGN KEY (" + PROJECT_IMAGE_PROJECT_ID + ") REFERENCES Projects(" + PROJECT_ID + ")" 
			+ " );";

	//Create biographic table
	public static final String CREATE_BIOGRAPHIC_TABLE = "create table "
			+ TABLE_BIOGRAPHIC + " ( "
			+ BIOGRAPHIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
			+ ", " + BIOGRAPHIC_PROJECT_ID + " INTEGER NOT NULL "
			+ ", " + BIOGRAPHIC_TYPE + " NVARCHAR(40) NOT NULL"
			+ ", " + BIOGRAPHIC_DETAILS + " NVARCHAR(500) NULL DEFAULT '' "
			+ ", " + BIOGRAPHIC_CATEGORY + " NVARCHAR(20) NOT NULL"
			+ ", FOREIGN KEY (" + BIOGRAPHIC_PROJECT_ID + ") REFERENCES Projects("+ PROJECT_ID +")"
			+ " );";

	
	public ProjectsSQLite(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Creates the list of table
	 */
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_PROJECTS_TABLE);
		database.execSQL(CREATE_PROJECT_DETAILS_TABLE);
		database.execSQL(CREATE_PROJECT_FEATURE_TABLE);
		database.execSQL(CREATE_IMAGE_TABLE);
		database.execSQL(CREATE_LANGUAGE_TABLE);
		database.execSQL(CREATE_BIOGRAPHIC_TABLE);
		
		Log.w("SQLiteDatabase", "creating tables complete");
	}

	/**
	 * Used if you want to upgrade your database version
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(ProjectsSQLite.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT_DETAILS);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT_FEATURES);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT_IMAGE);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_LANGUAGE);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_BIOGRAPHIC);

		onCreate(database);
	}

	@Override
	public void onOpen(SQLiteDatabase database) {
		Log.w(ProjectsSQLite.class.getName(), "Opening database "
				+ DATABASE_NAME);
	}

}