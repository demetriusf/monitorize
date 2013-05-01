package com.asccode.siteswatch.task;

import com.asccode.siteswatch.support.WebServiceOperations;

import android.os.AsyncTask;

public class GCMUnregisterOnServerTask extends AsyncTask<String, Object, Object>{

	@Override
	protected Object doInBackground(String... regId) {
		
		new WebServiceOperations().unregisterUserGCM(regId[0]);
		
		return null;
		
	}

}
