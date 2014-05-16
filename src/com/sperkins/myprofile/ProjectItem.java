package com.sperkins.myprofile;

import java.text.SimpleDateFormat;

import android.net.Uri;

public class ProjectItem {
	
	private int projectId;
	private String projectName;
	private String projectDescription;
	private int programmingLanguageResourceId;
	private String projectDate;
	

	public ProjectItem(int projectId, String projectName,String projectDate, String projectDescription, int programmingLanguageResourceId){
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.programmingLanguageResourceId = programmingLanguageResourceId;
		this.projectDate = projectDate;
	
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public int getProgrammingLanguageResourceId() {
		return programmingLanguageResourceId;
	}

	public void setProgrammingLanguageResourceId(int programmingLanguageResourceId) {
		this.programmingLanguageResourceId = programmingLanguageResourceId;
	}
	
	public String getProjectDate() {
		return projectDate;
	}

	public void setProjectDate(String projectDate) {
		this.projectDate = projectDate;
	}
	
}
