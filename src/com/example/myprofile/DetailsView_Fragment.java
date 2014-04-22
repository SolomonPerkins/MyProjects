package com.example.myprofile;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

public class DetailsView_Fragment extends Fragment{
		
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
	
	private String introductionMessage;
	private String descriptionMessage;
	private ViewFlipper slideShow;
	private ListView features;	
	private int selectedProjectId;	//the project user click
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
					ViewGroup container, Bundle savedInstanceState){
		
		
		return inflater.inflate(R.layout.fragment_project_details_2
				, container, false);
	}
	
	public void hideDetailsView(){
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.hide(this);
		
		fragmentTransaction.commit();
	}
	
	public void showDetailsView(){
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.show(this);
		
		fragmentTransaction.commit();
		
	}
	
	/**
	 * TODO: Create function which will be used to call the service. The service should return all the details relating to the product
	 */
	
	/**
	 * Calls all the other child methods to set each data
	 */
	public void setDetailsData(){
		
	}

	/**
	 * GETTERS AND SETTERS: all are protected
	 * 
	 */
	protected String getIntroductionMessage() {
		return introductionMessage;
	}

	protected void setIntroductionMessage(String introductionMessage) {
		this.introductionMessage = introductionMessage;
	}

	protected String getDescriptionMessage() {
		return descriptionMessage;
	}

	protected void setDescriptionMessage(String descriptionMessage) {
		this.descriptionMessage = descriptionMessage;
	}

	protected ViewFlipper getSlideShow() {
		return slideShow;
	}

	protected void setSlideShow(ViewFlipper slideShow) {
		this.slideShow = slideShow;
	}

	protected ListView getFeatures() {
		return features;
	}

	protected void setFeatures(ListView features) {
		this.features = features;
	}

	protected int getSelectedProjectId() {
		return selectedProjectId;
	}

	protected void setSelectedProjectId(int selectedProjectId) {
		this.selectedProjectId = selectedProjectId;
	}
}
