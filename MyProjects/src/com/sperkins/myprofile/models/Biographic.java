package com.sperkins.myprofile.models;

import java.sql.Date;

public class Biographic {
	private long id;
	private String bio_type;	//this will be the name of the bio details eg: Contacts
	private String bio_details;	
	private Date bio_date;
	private String bio_category;	//The category will determine the order they are presented in (email, achievenemts, tele, education, social  etc)
	
	
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
	 * @return the bio_type
	 */
	public String getBio_type() {
		return bio_type;
	}
	/**
	 * @param bio_type the bio_type to set
	 */
	public void setBio_type(String bio_type) {
		this.bio_type = bio_type;
	}
	/**
	 * @return the bio_details
	 */
	public String getBio_details() {
		return bio_details;
	}
	/**
	 * @param bio_details the bio_details to set
	 */
	public void setBio_details(String bio_details) {
		this.bio_details = bio_details;
	}
	/**
	 * @return the bio_date
	 */
	public Date getBio_date() {
		return bio_date;
	}
	/**
	 * @param bio_date the bio_date to set
	 */
	public void setBio_date(Date bio_date) {
		this.bio_date = bio_date;
	}
	/**
	 * @return the bio_category
	 */
	public String getBio_category() {
		return bio_category;
	}
	/**
	 * @param bio_category the bio_category to set
	 */
	public void setBio_category(String bio_category) {
		this.bio_category = bio_category;
	}
}
