package com.sperkins.myprofile;

import com.example.myprofile.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SlideShowArrayAdapterItem extends ArrayAdapter<SlideShowItem>{
	
	public Context mContext;
	int layoutResourceId;
	SlideShowItem slideShowItems[] = null;
	
	public SlideShowArrayAdapterItem(Context mContext, int layoutResourceId, SlideShowItem[] slideShowItems){
		super(mContext, layoutResourceId, slideShowItems);
		
		this.mContext = mContext;
		this.layoutResourceId = layoutResourceId;
		this.slideShowItems = slideShowItems;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent,false);
		}
		
		SlideShowItem slideshowItem = slideShowItems[position];
		
		//Get the details (currently jus the title bar
		TextView slideshowTitle = (TextView ) convertView.findViewById(R.id.slideshow_item_title);
		
		//Set the id of this slideshow
		slideshowTitle.setTag(slideshowItem.slideshowId);
		
		return convertView;
	}

}
