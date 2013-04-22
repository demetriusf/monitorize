package com.asccode.siteswatch.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gcm.GCMBroadcastReceiver;
import com.google.android.gcm.GCMRegistrar;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 19/04/13
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
public class GCMDeviceManager {

    private final static String TAG = "GCM_DEVICE_MANAGER";

    public static void manager( Context context ){

        try{

            GCMRegistrar.checkDevice(context);
            GCMRegistrar.checkManifest(context);

        }catch (Exception e){

            Log.e("GCM", e.getMessage());

        }


        String regId = GCMRegistrar.getRegistrationId(context);

        if( regId.equals("") ){

            Toast.makeText(context, regId, Toast.LENGTH_LONG).show();

            GCMRegistrar.register(context, GCMUtils.SENDER_ID);

        }else{

            Log.v(TAG, "Already registered");

        }

    }

}
