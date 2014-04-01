package com.example.myprofile;

public class ProjectItem {
	
	public int projectId;
	public String projectName;
	public String projectDescription;
	
	public ProjectItem(int projectId, String projectName, String projectDescription){
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
	}
}
