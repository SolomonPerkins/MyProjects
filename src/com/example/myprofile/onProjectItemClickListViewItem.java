package com.example.myprofile;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


public class onProjectItemClickListViewItem implements OnItemClickListener{
	
	//Even though you are overriding this function.. it was implemented, hence no override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		
		//Get the context from the view
		Context context = view.getContext();
		
		//get the project name
		String project_name = ((TextView) view.findViewById(R.id.project_name)).getText().toString();
	
		//get the tag
		String project_description = ((TextView) view.findViewById(R.id.project_description)).getText().toString();
		
		Toast.makeText(context, "Click view: "+ project_name+" Project_id: "+ project_description, Toast.LENGTH_LONG)
			.show();
		
		
		DetailsView_Fragment detailsFrameLayout = new DetailsView_Fragment();

		
//		FrameLayout displayFrameLayout = (FrameLayout) parent.findViewById(R.layout.fragment_project_details_2);
		//View detailsView = (View) displayFrameLayout.findViewById(R.id.details_fragment);
//		LayoutParams params = (LayoutParams) displayFrameLayout.getLayoutParams();

//		displayFrameLayout.setMinimumHeight(200);
//		displayFrameLayout.bringToFront();
//		android.widget.FrameLayout.LayoutParams params = (LayoutParams) displayFrameLayout.getLayoutParams();
		
//		params.height = 140;
	}
	
	
	
}
