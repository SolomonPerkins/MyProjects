package com.example.myprofile;

import android.app.Fragment;
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
import android.widget.ViewFlipper;

public class DetailsView_Fragment extends Fragment{
		
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
	
	@Override
	public View onCreateView(LayoutInflater inflater,
					ViewGroup container, Bundle savedInstanceState){
		
		
		return inflater.inflate(R.layout.fragment_project_details_2
				, container, false);
	}
	
	public void hideDetailsView(){
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.hide(this);
		
		fragmentTransaction.commit();
	}
	
	public void showDetailsView(){
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.show(this);
		
		fragmentTransaction.commit();
		
	}
	
}
