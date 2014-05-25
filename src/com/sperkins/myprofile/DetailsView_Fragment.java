package com.sperkins.myprofile;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.sperkins.myprofile.R;
import com.sperkins.myprofile.models.ProjectFeature;
import com.sperkins.myprofile.services.localDbService;

/**
 * Manages all the basic functionality relating to the details view.
 * @author alexforce
 *
 */
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
	
	private ViewFlipper slideShowFlipper;
	
	private localDbService localdb;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
					ViewGroup container, Bundle savedInstanceState){
		
		View iFlaterView = inflater.inflate(R.layout.fragment_project_details_2
				, container, false);
		
		slideShowFlipper = (ViewFlipper) iFlaterView.findViewById(R.id.detail_slideshow);
		
		localdb = new localDbService(container.getContext());
		
		return iFlaterView;
	}
	
	/**
	 * TODO: should slide down the fragment in the future
	 */
	public void hideDetailsView(){
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.hide(this);
		slideShowFlipper.stopFlipping();
		
		fragmentTransaction.commit();
	}
	
	/**
	 * TODO: should slide up the fragment in the future
	 */
	public void showDetailsView(){
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.show(this);
		slideShowFlipper.startFlipping();
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
	
	/**
	 * After details get set retrieved then add them to the fragment elements
	 */
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
	
	public void setupProjectFeatures(){
//		List<ProjectFeature> featureList = localdb.getProjectDetails(selectedProjectId);
		
	}
	
	
}
