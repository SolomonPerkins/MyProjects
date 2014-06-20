package com.sperkins.myprofile.models;

import java.util.List;

public class ProjectDetails {
	private long id;
	private long project_id;
	private String project_name;
	private String project_introduction;
	private String project_description;
	private String project_difficulty;
	private List<ProjectFeature> featuresList;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the project_id
	 */
	public long getProjectId() {
		return project_id;
	}
	/**
	 * @param project_id the project_id to set
	 */
	public void setProjectId(long project_id) {
		this.project_id = project_id;
	}
	
	public String getProjectName() {
		return project_name;
	}
	/**
	 * @param project_id the project_id to set
	 */
	public void setProjectName(String name) {
		this.project_name = name;
	}
	
	public String getProjectIntroduction() {
		return project_introduction;
	}
	/**
	 * @param project_id the project_id to set
	 */
	public void setProjectIntroduction(String introduction) {
		this.project_introduction = introduction;
	}

	
	/**
	 * @return the project_description
	 */
	public String getProjectDescription() {
		return project_description;
	}
	/**
	 * @param project_description the project_description to set
	 */
	public void setProjectDescription(String project_description) {
		this.project_description = project_description;
	}
	/**
	 * @return the project_difficulty
	 */
	public String getProjectDifficulty() {
		return project_difficulty;
	}
	/**
	 * @param project_difficulty the project_difficulty to set
	 */
	public void setProjectDifficulty(String project_difficulty) {
		this.project_difficulty = project_difficulty;
	}
	
	public void setProjectFeaturesList(List<ProjectFeature> features){
		this.featuresList = features;
	}
	
	public List<ProjectFeature> getProjectFeaturesList(){
		return this.featuresList;
	}
	
}
