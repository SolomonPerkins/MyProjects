package com.example.myprofile;

public class MyDetailsItem {
	private int contactId;
	private String myTitle;
	private String myContent;
	
	public MyDetailsItem(int contactId, String myTitle, String myContent){
		this.contactId = contactId;
		this.myTitle = myTitle;
		this.myContent = myContent;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getMyTitle() {
		return myTitle;
	}

	public void setMyTitle(String myTitle) {
		this.myTitle = myTitle;
	}

	public String getMyContent() {
		return myContent;
	}

	public void setMyContent(String myContent) {
		this.myContent = myContent;
	}
}
