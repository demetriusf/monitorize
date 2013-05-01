package com.asccode.siteswatch.task;

import com.asccode.siteswatch.support.WebServiceOperations;
import com.google.android.gcm.GCMRegistrar;

import android.content.Context;
import android.os.AsyncTask;

public class GCMRegisterOnServerTask extends AsyncTask<Object, Object, Boolean>{

	private String regId;
	private String loginUserToken;
	private Context context;
	
	public GCMRegisterOnServerTask(String regId, String loginUserToken, Context context) {
		
		this.regId = regId;
		this.loginUserToken = loginUserToken;
		this.context = context;
		
	}

	@Override
	protected Boolean doInBackground(Object... params) {
		
		return new WebServiceOperations().registerUserGCM(this.regId, this.loginUserToken);
	
	}
	
	@Override
	protected void onPostExecute(Boolean result){
		
		GCMRegistrar.setRegisteredOnServer(this.context, result);
		
	}

}
