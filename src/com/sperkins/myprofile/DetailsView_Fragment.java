package com.sperkins.myprofile;

import java.util.List;
import java.util.logging.LoggingPermission;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.sperkins.myprofile.R;
import com.sperkins.myprofile.models.ProjectDetails;
import com.sperkins.myprofile.models.ProjectFeature;
import com.sperkins.myprofile.services.localDbService;

/**
 * Manages all the basic functionality relating to the details view.
 * @author alexforce
 *
 */
public class DetailsView_Fragment extends Fragment{
		
	public Log log;
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
	
	private ProjectDetails projectDetails;	//Contain all the information to be show in project details
		
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

	public void setupDetails(long project_id, String project_name, String project_introduction){
		this.getDetailsData(project_id);
		
		projectDetails.setProjectName(project_name);
		projectDetails.setProjectIntroduction(project_introduction);
		
		this.setDetails(projectDetails);		
	}

	
	/**
	 * TODO: Create function which will be used to call the service. The service should return all the details relating to the product
	 */
	public void setDetails(ProjectDetails projectDetails){
		
		this.addDetailsToFragment();
	}
	
	
	
	/**
	 * After details get set add them to the fragment elements
	 */
	public void addDetailsToFragment(){
		//get each element from the fragment
		projectNameElement = (TextView) this.getView().findViewById(R.id.project_details_name_content);	//Project name should be at the top of the action bar
		projectIntroductionElement = (TextView) this.getView().findViewById(R.id.project_details_introduction_content);
		projectFeaturesElement = (ListView) this.getView().findViewById(R.id.project_details_feature_list);
		projectDescriptionElement = (TextView) this.getView().findViewById(R.id.project_details_description_content);
		
		
		projectNameElement.setText(projectDetails.getProjectName());
		projectIntroductionElement.setText(projectDetails.getProjectIntroduction());
		
		//TODO: set the features list for all featured element.
		
		projectDescriptionElement.setText(projectDetails.getProjectDescription());
	}
	
	/**
	 * Load details data from localStorage
	 * @param project_id
	 */
	public void getDetailsData(long project_id){
		
		try{
			if(project_id <= 0){
				log.e("Get Details Data", "unable to extract data for project id "+ project_id);
			}else{
				log.w("Get Details Data", "get data from project id "+ project_id);
				projectDetails = localdb.getProjectDetails(project_id);
				
				if(projectDetails == null){
					log.e("Get Details Data", "no details returned for project id "+ project_id);			
					throw new Exception("no details returned for project id "+ project_id);
				}
			}
		}catch(Exception e){
			log.e("Exception gets thrown", e.getMessage());
			e.printStackTrace();
		}
	}	
}
