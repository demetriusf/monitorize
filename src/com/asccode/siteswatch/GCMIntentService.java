package com.asccode.siteswatch;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.asccode.siteswatch.dao.LoginDao;
import com.asccode.siteswatch.gcm.GCMUtils;
import com.asccode.siteswatch.task.GCMRegisterOnServerTask;
import com.asccode.siteswatch.task.GCMUnregisterOnServerTask;
import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 19/04/13
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */
public class GCMIntentService extends GCMBaseIntentService {


    public GCMIntentService() {

        super(GCMUtils.SENDER_ID);

    }

    @Override
    protected void onMessage(Context context, Intent intent) {  // CREATE NOTIFICATION FOR THE USERS.

        Toast.makeText(context, "onMessage", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onError(Context context, String s) {

        Toast.makeText(context, "onError", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onRegistered(Context context, String regId) { /* SEND THE ID TO THE SERVER AND ABLE THE ANDROID NOTIFICATION PREFERENCES */

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

        Toast.makeText(context, "onRecoverableError", Toast.LENGTH_LONG).show();

        return super.onRecoverableError(context, errorId); 

    }
}
