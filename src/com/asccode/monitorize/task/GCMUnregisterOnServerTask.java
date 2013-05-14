package com.asccode.monitorize.task;

import android.os.AsyncTask;
import com.asccode.monitorize.support.WebServiceOperations;

public class GCMUnregisterOnServerTask extends AsyncTask<String, Object, Object>{

	@Override
	protected Object doInBackground(String... regId) {
		
		new WebServiceOperations().unregisterUserGCM(regId[0]);
		
		return null;
		
	}

}
