package com.asccode.siteswatch.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import com.asccode.siteswatch.dao.LoginDao;
import com.asccode.siteswatch.task.GCMRegisterOnServerTask;
import com.google.android.gcm.GCMBroadcastReceiver;
import com.google.android.gcm.GCMRegistrar;

public class GCMDeviceManager {

    public static void manager( Context context ){

       GCMRegistrar.checkDevice(context);
       //GCMRegistrar.checkManifest(context);

       String regId = GCMRegistrar.getRegistrationId(context);        
        
        if( regId.equals("") ){

            GCMRegistrar.register(context, GCMUtils.SENDER_ID);

        }else{
        	
        	if( !GCMRegistrar.isRegisteredOnServer(context) ){
        		
        		new GCMRegisterOnServerTask(regId, new LoginDao(context).getTokenUserLogged(), context).execute();
        		
        	}

        }

    }

}
