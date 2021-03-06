package com.sperkins.myprofile.services;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.widget.ListView;

import com.sperkins.myprofile.models.ProjectDetails;
import com.sperkins.myprofile.models.ProjectListView;
import com.sperkins.myprofile.sqlite.ProjectDetailsDao;
import com.sperkins.myprofile.sqlite.ProjectsDao;
import com.sperkins.myprofile.sqlite.ProjectsSQLite;

public class localDbService {
	
	private ProjectDetailsDao projectDetailsDao;
	private ProjectsDao projectsDao;
	
	public localDbService(Context context){
		projectsDao = new ProjectsDao(context);
		projectDetailsDao = new ProjectDetailsDao(context);
		
	}
	
	public List<ProjectListView> getProjects(){
		projectsDao.open();
		List<ProjectListView> projects = projectsDao.getAllProject("date", "DESC");
		projectsDao.close();
		
		return projects;
	}
	
	public ProjectDetails getProjectDetails(long project_id){
		projectDetailsDao.open();
		ProjectDetails projectDetails = projectDetailsDao.getProjectDetails(project_id);
		projectDetailsDao.close();
		
		return projectDetails;
	}
	
	
	public void syncLocalDatabase(){
		
	}
}
