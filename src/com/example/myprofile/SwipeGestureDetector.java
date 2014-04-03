package com.example.myprofile;

import android.content.Context;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class SwipeGestureDetector extends SimpleOnGestureListener {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper myViewFlipper;
	private boolean autoStart = true;
	private int timeInterval = 4000;
	/**
	 * TODO: Include the appropriate context in this location
	 */
	private Context mContext;
	
	
	
	
	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
		/**
		 * TODO: Create animation xml for right and left swipe. Need to learn how to do that
		 */
		
		try{
			//Right to left swipe
			if((event1.getX() - event2.getX()) > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
				myViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
				myViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
				myViewFlipper.showNext();
				return true;
			}else if((event2.getX() - event1.getX()) > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){	//if you swipe  from left to right
				myViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
				myViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_out));
				myViewFlipper.showPrevious();
				return true;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void startSlideShow(){
		myViewFlipper.setAutoStart(autoStart);
		myViewFlipper.setFlipInterval(timeInterval);
		myViewFlipper.startFlipping();
	}
}
