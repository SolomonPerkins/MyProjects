package com.sperkins.myprofile.models;

/**
 * This model will hold data for the project listview of the application
 * Project List view ontains the following model Project, Language and ProjectImages
 * @author alexforce
 *
 */
public class ProjectListView {
	
	private Project aProject;
	private Language aLanguage;
	private ProjectImage aProjectImage;	//only one image to be shown on the list view
	
	
	/**
	 * @return the aProject
	 */
	public Project getProject() {
		return aProject;
	}
	/**
	 * @param aProject the aProject to set
	 */
	public void setProject(Project aProject) {
		this.aProject = aProject;
	}
	/**
	 * @return the aLanguage
	 */
	public Language getLanguage() {
		return aLanguage;
	}
	/**
	 * @param aLanguage the aLanguage to set
	 */
	public void setLanguage(Language aLanguage) {
		this.aLanguage = aLanguage;
	}
	/**
	 * @return the aProjectImage
	 */
	public ProjectImage getProjectImage() {
		return aProjectImage;
	}
	/**
	 * @param aProjectImage the aProjectImage to set
	 */
	public void setProjectImage(ProjectImage aProjectImage) {
		this.aProjectImage = aProjectImage;
	}
	
	
}
