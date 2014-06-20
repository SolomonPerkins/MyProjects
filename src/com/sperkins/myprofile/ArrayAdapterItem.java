package com.sperkins.myprofile;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sperkins.myprofile.models.ProjectListView;

/**
 * Used to populate the main page containing the list of projects
 */
public class ArrayAdapterItem extends ArrayAdapter<ProjectListView>{

	Context mContext;
	int layoutResourceId;
	List<ProjectListView> projects = null;
	
	public ArrayAdapterItem(Context mContext, int layoutResourceId, List<ProjectListView> projects){
		//For the arrayAdapter class
		super(mContext, layoutResourceId, projects);
	
		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.projects = projects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		if(convertView == null){
			//Inflate the layout
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent, false);
		}
		
		ProjectListView projectItem = projects.get(position);
		
		if(projectItem != null){
			
			//Get the  view elements in fragment
			TextView projectName = (TextView) convertView.findViewById(R.id.project_name);
			TextView projectIntroduction = (TextView) convertView.findViewById(R.id.project_introduction);
			TextView projectdate = (TextView) convertView.findViewById(R.id.project_date);
			ImageView programmingLanguage = (ImageView) convertView.findViewById(R.id.project_programming_language);

			//Set project id
			projectName.setTag(projectItem.getProject().getId());

			//Set projectname
			projectName.setText(
						projectItem
							.getProject()
							.getProjectName());
			
			//Set introduction
			projectIntroduction.setText(
					projectItem
						.getProject()
						.getProjectIntroduction());
			
			//Set date
			projectdate.setText(
					(projectItem
							.getProject()
							.getProjectDate()
					).toString());
			
			//Set programming language icon
			//TODO: Should reflect the correct image url
//			programmingLanguage.setImageResource(
//					projectItem
//						.getProgrammingLanguageResourceId());
			programmingLanguage.setImageResource(R.drawable.programming_language);
			

		}
		
		return convertView;
	}
}
