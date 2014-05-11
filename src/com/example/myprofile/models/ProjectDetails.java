package com.example.myprofile.models;

public class ProjectDetails {
	private long id;
	private long project_id;
	private String project_description;
	private String project_difficulty;
	
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
	public long getProject_id() {
		return project_id;
	}
	/**
	 * @param project_id the project_id to set
	 */
	public void setProject_id(long project_id) {
		this.project_id = project_id;
	}
	/**
	 * @return the project_description
	 */
	public String getProject_description() {
		return project_description;
	}
	/**
	 * @param project_description the project_description to set
	 */
	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}
	/**
	 * @return the project_difficulty
	 */
	public String getProject_difficulty() {
		return project_difficulty;
	}
	/**
	 * @param project_difficulty the project_difficulty to set
	 */
	public void setProject_difficulty(String project_difficulty) {
		this.project_difficulty = project_difficulty;
	}
	
	
}
