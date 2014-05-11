package com.example.myprofile.models;

public class ProjectImage {
	private long id;
	private long project_id ; //reference the project
	private Boolean is_main_image;	//the image used on the main list
	private String project_image_url;
	private String project_meta;	//basic details about the project
	
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
	 * @return the project_image_url
	 */
	public String getProjectImage_url() {
		return project_image_url;
	}
	
	public void setMainImage( Boolean is_main){
		this.is_main_image = is_main;
	}
	public Boolean getMainImage(){
		return this.is_main_image;
	}
	
	/**
	 * @param project_image_url the project_image_url to set
	 */
	public void setProjectImageUrl(String project_image_url) {
		this.project_image_url = project_image_url;
	}
	
	/**
	 * @return the project_meta
	 */
	public String getProjectMeta() {
		return project_meta;
	}
	
	/**
	 * @param project_meta the project_meta to set
	 */
	public void setProjectMeta(String project_meta) {
		this.project_meta = project_meta;
	}
	
	
	
}
