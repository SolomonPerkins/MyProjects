package com.sperkins.myprofile.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.sperkins.myprofile.models.Language;
import com.sperkins.myprofile.models.Project;
import com.sperkins.myprofile.models.ProjectImage;
import com.sperkins.myprofile.models.ProjectListView;

import android.database.Cursor;
import android.util.Log;

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
	 * eg. db uses 1 or 0 to represent boolean, this converts the value into true or false 
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
	
	public int getBooleanConvert(Boolean bool){
		if(bool == null || bool == false){
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * Map cursor data into the project object
	 * @param cursor
	 * @return {@link Project}
	 */
	public Project cursorToProject(Cursor cursor){
		if(cursor == null){
			return null;
		}
		
		Project project = new Project();
		
		project.setId(cursor.getLong(0));
		project.setProjectName(cursor.getString(1));
		project.setProjectDate(processDate(cursor.getString(2)));
		project.setProjectIntroduction(cursor.getString(3));
		
		return project;
	}
	
	/**
	 * Maps Data to object for {@link Language}
	 * @param cursor
	 * @return
	 */
	public Language cursorToLanguage(Cursor cursor){
		if(cursor == null){
			return null;
		}
		
		Language language = new Language();
		language.setId(cursor.getLong(4));	//language_id;
		language.setLanguage_name(cursor.getString(5));
		language.setLanguage_image_url(cursor.getString(6));
		
		return language;
	}
	
	public ProjectImage cursorToImage(Cursor cursor){
		if(cursor == null){
			return null;
		}
		
		ProjectImage image = new ProjectImage();
		image.setId(cursor.getLong(7));
		image.setMainImage(this.getBoolean(cursor, 8));
		image.setImageUrl(cursor.getString(9));
		image.setImageMeta(cursor.getString(10));
	
		return image;
	}
	
	/**
	 * Will populate all the model data for each component on the list view
	 * @param cursor
	 * @return
	 */
	public ProjectListView cursorToProjectListView(Cursor cursor){
		if(cursor == null){
			return null;
		}
		
		ProjectListView listViewItem = new ProjectListView();
		
		Project project = cursorToProject(cursor);
		Language language = cursorToLanguage(cursor);	//should get the language details
		ProjectImage image = cursorToImage(cursor);	// should get the image details
		
		//populating each model
		listViewItem.setProject(project);
		listViewItem.setLanguage(language);
		listViewItem.setProjectImage(image);
		
		return listViewItem;
		
	}
	
	/**
	 * converts a string date into a Date object 
	 * @param string_date
	 * @return (sql)Date
	 */
	public Date processDate(String string_date){
			
			//TODO: string date should be converted to Date format	
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				
				date = (Date) format.parse(string_date);
				
				Log.w("Convert string to date", "Convert string ("+ string_date + ") to (date "+ date.toString() +")");		
				return date;

			} catch (ParseException e) {
				
				e.printStackTrace();
				return null;
			}
						
		
	}

}
