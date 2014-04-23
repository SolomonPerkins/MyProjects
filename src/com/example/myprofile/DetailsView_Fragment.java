package com.example.myprofile;

import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.TextView;
import android.widget.ViewFlipper;

public class DetailsView_Fragment extends Fragment{
		
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
	
	private String tag;
	private String projectName;
	private String introductionMessage;
	private String descriptionMessage;
	private int selectedProjectId;	//the project user click
	
	//Fragment components
	TextView projectNameElement;
	TextView projectIntroductionElement;
	TextView projectDescriptionElement;
	ListView projectFeaturesElement;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
					ViewGroup container, Bundle savedInstanceState){
		
		
		return inflater.inflate(R.layout.fragment_project_details_2
				, container, false);
	}
	
	/**
	 * TODO: should slide down the fragment in the future
	 */
	public void hideDetailsView(){
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.hide(this);
		
		fragmentTransaction.commit();
	}
	
	/**
	 * TODO: should slide up the fragment in the future
	 */
	public void showDetailsView(){
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.show(this);
		
		fragmentTransaction.commit();
		
	}
	
	/**
	 * TODO: Create function which will be used to call the service. The service should return all the details relating to the product
	 */
	public void setupDetails(String tag, String project_name, String project_introduction, String project_description){
		this.tag = tag;
		this.setProjectName(project_name);
		this.setIntroductionMessage(project_introduction);
		this.setDescriptionMessage(project_description);

		this.addDetailsToFragment();
	}
	public void addDetailsToFragment(){
		//get each element from the fragment
		projectNameElement = (TextView) this.getView().findViewById(R.id.project_details_name_content);	//Project name should be at the top of the action bar
		projectIntroductionElement = (TextView) this.getView().findViewById(R.id.project_details_introduction_content);
		projectFeaturesElement = (ListView) this.getView().findViewById(R.id.project_details_feature_list);
		projectDescriptionElement = (TextView) this.getView().findViewById(R.id.project_details_description_content);
		
		projectNameElement.setText(this.getProjectName());
		projectIntroductionElement.setText(this.getIntroductionMessage());
		//TODO: set the features list for all featured element.
		projectDescriptionElement.setText(this.getDescriptionMessage());
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

	/*protected ViewFlipper getSlideShow() {
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
	 */
	
	protected int getSelectedProjectId() {
		return selectedProjectId;
	}

	protected void setSelectedProjectId(int selectedProjectId) {
		this.selectedProjectId = selectedProjectId;
	}

	protected String getProjectName() {
		return projectName;
	}

	protected void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
}
