package com.sperkins.myprofile.models;

public class Language {
	private long id;
	private long project_id; //reference the project id
	private String language_name;
	private String language_reference;
	private String language_image_url;
	
	
	public Language(){
		
	}
	public Language(long id, long project_id, String language_name, String langugage_reference, String language_image_url){
		this.id = id;
		this.project_id = project_id;
		this.language_name = language_name;
		this.language_image_url = language_image_url;
		this.language_reference = langugage_reference;
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
	 * @return the language_name
	 */
	public String getLanguage_name() {
		return language_name;
	}
	/**
	 * @param language_name the language_name to set
	 */
	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}
	/**
	 * @return the language_reference
	 */
	public String getLanguage_reference() {
		return language_reference;
	}
	/**
	 * @param language_reference the language_reference to set
	 */
	public void setLanguage_reference(String language_reference) {
		this.language_reference = language_reference;
	}
	/**
	 * @return the language_image_url
	 */
	public String getLanguage_image_url() {
		return language_image_url;
	}
	/**
	 * @param language_image_url the language_image_url to set
	 */
	public void setLanguage_image_url(String language_image_url) {
		this.language_image_url = language_image_url;
	}
	
	

}
