package com.asccode.siteswatch;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.asccode.siteswatch.dao.LoginDao;
import com.asccode.siteswatch.gcm.GCMUtils;
import com.asccode.siteswatch.support.NotificationSupport;
import com.asccode.siteswatch.task.GCMRegisterOnServerTask;
import com.asccode.siteswatch.task.GCMUnregisterOnServerTask;
import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;

public class GCMIntentService extends GCMBaseIntentService {

    public GCMIntentService() {

        super(GCMUtils.SENDER_ID);

    }

    @Override
    protected void onMessage(Context context, Intent intent) {

        NotificationSupport.showNotification(intent.getStringExtra("message"), this);

    }

    @Override
    protected void onError(Context context, String s) {

    }

    @Override
    protected void onRegistered(Context context, String regId) {

    	new GCMRegisterOnServerTask(regId, new LoginDao(context).getTokenUserLogged(), context).execute();
    	
    }

    @Override
    protected void onUnregistered(Context context, String regId) { 

    	// Try remove the device on the server
    	new GCMUnregisterOnServerTask().execute(regId);
    	
    	// Anyway, the device will be unregistered, so if other user log in, We will insert it again.
        GCMRegistrar.setRegisteredOnServer(context, false);
        
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {

        return super.onRecoverableError(context, errorId); 

    }
}
