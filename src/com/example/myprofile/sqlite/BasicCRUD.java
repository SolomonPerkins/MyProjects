package com.example.myprofile.sqlite;

import java.util.List;

import com.example.myprofile.models.Project;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * All table provides basic CRUD operation.
 * TODO:  A more effecient implementation is to use this to return generic type based on the Dao being used.
 * @author alexforce
 *
 */

public class BasicCRUD {
	private SQLiteDatabase database;
	private String table;
	
	
/*	public BasicCRUD(SQLiteDatabase database, String table){
		this.database = database;
		this.table = table;
	}
*/	
	/**
	 * @return the database
	 */
	public SQLiteDatabase getDatabase() {
		return database;
	}
	/**
	 * @param database the database to set
	 */
	public void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}
	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}
	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}
	
	public Cursor create(ContentValues values, String[] allColumns, String id_column){
		//Insert values
		long insertId = database.insert(this.table, null, values);
		
		//Store results returned in a cursor.
		Cursor cursor = database.query(this.table
				, allColumns
				, id_column + " = " + insertId
				, null, null, null, null);
		
		//Return to the first element in the list
		cursor.moveToFirst();
		
		return cursor;
	}
	
	public List<Object> getList(){
		
		return null;
	}
	
	public void delete(){
		
	}
	
	public Object update(){
		
		return null;
	}
	
}
