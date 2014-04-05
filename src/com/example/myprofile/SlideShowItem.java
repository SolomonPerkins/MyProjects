package com.example.myprofile;


public class SlideShowItem {
	
	public int slideshowId;
	public String slideshowTitle;
	
	//Set the images
	public String imageUrl;
	
	public SlideShowItem(int slideshowId, String slideshowTitle){
		this.slideshowId = slideshowId;
		this.slideshowTitle = slideshowTitle;
		this.imageUrl = "";
	}
}
