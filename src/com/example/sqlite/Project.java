package com.example.sqlite;

import java.util.Date;

/**
 * This the model which will be used to interact with the project table (database) and show in the user interface
 * @author alexforce
 * 
 */
public class Project {
	private long id;
	private String project_name;
	private String image_url;
	private String language;
	private String project_description;
	private String project_date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getProject_description() {
		return project_description;
	}
	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}
	public String getProject_date() {
		return project_date;
	}
	public void setProject_date(String project_date) {
		this.project_date = project_date;
	}	
	
}
