package com.asccode.siteswatch.gcm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.asccode.siteswatch.dao.LoginDao;
import com.asccode.siteswatch.support.WebServiceOperations;
import com.google.android.gcm.GCMBaseIntentService;

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

        //Boolean resultRegister = new WebServiceOperations().registerUserGCM(regId, new LoginDao(context).getTokenUserLogged() );
        Toast.makeText(context, "onRegistered", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onUnregistered(Context context, String regId) {  /* REMOVE THE ID TO THE SERVER AND DISABLE THE PREFERENCES ANDROID NOTIFICATION */

        Toast.makeText(context, "onUnregistered", Toast.LENGTH_LONG).show();

    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {

        Toast.makeText(context, "onRecoverableError", Toast.LENGTH_LONG).show();

        return super.onRecoverableError(context, errorId);    //To change body of overridden methods use File | Settings | File Templates.

    }
}
