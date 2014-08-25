package com.is3av.drawpin;

import android.app.Application;
import com.parse.*;

public class PushParse extends Application {
	@Override
	public void onCreate()
	{

	    super.onCreate();
	    
	        Parse.initialize(getApplicationContext(), "aFRdl5kX03QM4zlZM9xWovcUXlk8Zz9tmWjryQPy", "aGeVln3IMlDPXgqEoAzB687xRW3hhYBw7NBf0epI");
	        PushService.setDefaultPushCallback(this, LoginScreen.class);
	        ParseInstallation.getCurrentInstallation().saveInBackground();
	    

	}
}
