package com.example.myprofile.models;

import java.sql.Date;


/**
 * This the model which will be used to interact with the project table (database) and show in the user interface
 * @author alexforce
 * 
 */
public class Project {
	private long id;
	private String project_name;
	private String project_introduction;
	private Date project_date;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProjectName() {
		return project_name;
	}
	public void setProjectName(String project_name) {
		this.project_name = project_name;
	}
	public String getProjectIntroduction() {
		return project_introduction;
	}
	public void setProjectIntroduction(String project_introduction) {
		this.project_introduction = project_introduction;
	}
	public Date getProjectDate() {
		return project_date;
	}
	
	public void setProjectDate(Date project_date) {
		this.project_date = project_date;
	}
	
	
}
