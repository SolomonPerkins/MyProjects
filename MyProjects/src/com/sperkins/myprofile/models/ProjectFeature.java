package com.sperkins.myprofile.models;

public class ProjectFeature {
	private long id;
	private long project_id;
	private String feature;
	private String type;	//Not really important at the moment
	
	public ProjectFeature(){
		
	}
	
	public ProjectFeature(long id, long project_id, String feature, String type){
		this.id = id;
		this.project_id = project_id;
		this.feature = feature;
		this.type = type;
	
	}
	
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
	/**
	 * @return the feature
	 */
	public String getFeature() {
		return feature;
	}
	/**
	 * @param feature the feature to set
	 */
	public void setFeature(String feature) {
		this.feature = feature;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	

}
