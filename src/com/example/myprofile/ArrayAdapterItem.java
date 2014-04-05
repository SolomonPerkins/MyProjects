package com.example.myprofile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrayAdapterItem extends ArrayAdapter<ProjectItem>{

	Context mContext;
	int layoutResourceId;
	ProjectItem projects[] = null;
	
	public ArrayAdapterItem(Context mContext, int layoutResourceId, ProjectItem[] projects){
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
		
		ProjectItem projectItem = projects[position];
		
		if(projectItem != null){
			
			//Get the  descript and project name and it
			TextView projectName = (TextView) convertView.findViewById(R.id.project_name);
			TextView projectDescription = (TextView) convertView.findViewById(R.id.project_description);
			TextView projectdate = (TextView) convertView.findViewById(R.id.project_date);
			ImageView programmingLanguage = (ImageView) convertView.findViewById(R.id.project_programming_language);

			
			
			projectName.setText(projectItem.getProjectName());
			projectDescription.setText(projectItem.getProjectDescription());
			projectdate.setText(projectItem.getProjectDate());
			programmingLanguage.setImageResource(projectItem.getProgrammingLanguageResourceId());
			
			
			projectName.setTag(projectItem.getProjectId());

		}
		
		return convertView;
	}
}
