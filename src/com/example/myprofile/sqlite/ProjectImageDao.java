package com.example.myprofile.sqlite;

import android.database.sqlite.SQLiteDatabase;

public class ProjectImageDao {

	private SQLiteDatabase database;
	private ProjectDetailsSQLite dbHelper;
	private String[] allColumns = {
			ProjectDetailsSQLite.PROJECT_DETAILS_ID
		,	ProjectDetailsSQLite.PROJECT_DETAILS_PROJECT_ID
		,	ProjectDetailsSQLite.PROJECT_DETAILS_DESCRIPTION
		,	ProjectDetailsSQLite.PROJECT_DETAILS_DIFFICULTY
	};
	
	
	
}
