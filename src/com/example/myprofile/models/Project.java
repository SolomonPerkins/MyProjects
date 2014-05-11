package com.example.myprofile.models;


/**
 * This the model which will be used to interact with the project table (database) and show in the user interface
 * @author alexforce
 * 
 */
public class Project {
	private long id;
	private String project_name;
	private String project_introduction;
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
	public String getProject_introduction() {
		return project_introduction;
	}
	public void setProject_introduction(String project_introduction) {
		this.project_introduction = project_introduction;
	}
	public String getProject_date() {
		return project_date;
	}
	public void setProject_date(String project_date) {
		this.project_date = project_date;
	}
	
	
}
