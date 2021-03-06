package com.asccode.monitorize.gcm;

import android.content.Context;
import com.asccode.monitorize.dao.LoginDao;
import com.asccode.monitorize.task.GCMRegisterOnServerTask;
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
