package com.example.myprofile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyDetailsArrayAdapterItem extends ArrayAdapter<MyDetailsItem>{
	
		Context mContext;
		int layoutResourceId;
		MyDetailsItem myDetails[] = null;
		
		public MyDetailsArrayAdapterItem(Context mContext, int layoutResourceId, MyDetailsItem[] myDetails){
			super(mContext, layoutResourceId, myDetails);
			
			this.mContext = mContext;
			this.layoutResourceId = layoutResourceId;
			this.myDetails = myDetails;
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			if(convertView == null){
				//inflate the layout
				LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
				convertView = inflater.inflate(layoutResourceId, parent, false);
				
			}
			MyDetailsItem myDetailsItem = myDetails[position];
		
			//Get the title and the 
			TextView myTitle = (TextView) convertView.findViewById(R.id.me_title);
			TextView myContent =(TextView) convertView.findViewById(R.id.me_content);
			myTitle.setTag(myDetailsItem.contactId);
			
			return convertView;
		}
		
}
