package com.asccode.monitorize;

import android.content.Context;
import android.content.Intent;
import com.asccode.monitorize.dao.LoginDao;
import com.asccode.monitorize.gcm.GCMUtils;
import com.asccode.monitorize.support.NotificationSupport;
import com.asccode.monitorize.task.GCMRegisterOnServerTask;
import com.asccode.monitorize.task.GCMUnregisterOnServerTask;
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
