package com.example.myprofile.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthorizationService extends Service{
	
	@Override
	public IBinder onBind(Intent intent){
		return null;
	}
}
