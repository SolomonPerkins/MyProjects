package com.example.myprofile.utils;

import android.database.Cursor;

public class DatabaseUtils {
	private  String database_name;
	private int database_version;
	
	public DatabaseUtils(){
		this.database_name = "projects.db";
		this.database_version = 1;
	}

	/**
	 * @return the database_name
	 */
	public String getDatabase_name() {
		return database_name;
	}

	/**
	 * @param database_name the database_name to set
	 */
	public void setDatabase_name(String database_name) {
		this.database_name = database_name;
	}

	/**
	 * @return the database_version
	 */
	public int getDatabase_version() {
		return database_version;
	}

	/**
	 * @param database_version the database_version to set
	 */
	public void setDatabase_version(int database_version) {
		this.database_version = database_version;
	}
	
	
	/**
	 * Return the boolean form a cursor list. 
	 * @param cursor
	 * @param columnIndex
	 * @return
	 */
	public Boolean getBoolean(Cursor cursor,int columnIndex){
		if(cursor.isNull(columnIndex) || cursor.getInt(columnIndex) == 0){
			return false;
		} else{
			return true;
		}
	}
	
}
