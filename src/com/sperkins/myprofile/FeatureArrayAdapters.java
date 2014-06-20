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

import com.sperkins.myprofile.models.ProjectFeature;

public class FeatureArrayAdapters extends ArrayAdapter<ProjectFeature>{
	Context context;
	int layoutResourceId;
	List<ProjectFeature> features = null;
	
	public FeatureArrayAdapters(Context context, int layoutResourceID, List<ProjectFeature> features){
		super(context, layoutResourceID, features);
		
		this.layoutResourceId = layoutResourceID;
		this.context = context;
		this.features = features;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		if(convertView == null){
			//inflate the layout
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent, false);
		}
		
		ProjectFeature featureItem = features.get(position);
		
		if(featureItem != null){
			//Get view elements
			ImageView featureIcon = (ImageView) convertView.findViewById(R.id.feature_image);
			TextView feature = (TextView) convertView.findViewById(R.id.feature);
			
			//Set feature id
			feature.setTag(featureItem.getId());
			
			//Set feature image
			feature.setText(featureItem.getFeature());

			//Set feature icon
			//TODO: get resource id from string url
			featureIcon.setImageResource(R.drawable.ic_launcher);
		}
		
		return convertView;
	}
}
