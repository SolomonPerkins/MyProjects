package com.sperkins.myprofile;

import com.example.myprofile.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ViewFlipper;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


public class onProjectItemClickListViewItem implements OnItemClickListener{
	
	private DetailsView_Fragment myDetailsFragment;
	
	public onProjectItemClickListViewItem(DetailsView_Fragment myDetailsFragment){
		this.myDetailsFragment = myDetailsFragment;
	}
	
	//Even though you are overriding this function.. it was implemented, hence no override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		
		//Get the context from the view
		Context context = view.getContext();
		
		//get the tag
		String tag = ((TextView) view.findViewById(R.id.project_name)).getTag().toString();
		
		//get the project name
		String project_name = ((TextView) view.findViewById(R.id.project_name)).getText().toString();
	
		//set the introduction from the details view
		String project_introduction = ((TextView) view.findViewById(R.id.project_introduction)).getText().toString();
		
		Toast.makeText(context, "Click view: "+ project_name+" Project_id: "+ project_introduction+" Tag "+tag, Toast.LENGTH_LONG)
			.show();
		
		
		//setup projects details
		// the description is empty as it is not stored on the list view. Service call will be used to extract these data from external sources 
		//and store them into liteSql for future references.
		myDetailsFragment.setupDetails(tag,project_name, project_introduction, "");
		myDetailsFragment.showDetailsView();
		
	}
	
	
	
}