package com.asccode.monitorize.support;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.asccode.monitorize.dao.LoginDao;
import com.asccode.monitorize.models.User;
import com.asccode.monitorize.task.AuthenticationUserTask;
import com.asccode.monitorize.task.GCMUnregisterOnServerTask;
import com.asccode.monitorize.telas.Main;
import com.google.android.gcm.GCMRegistrar;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 01/04/13
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class Login {

    private Context context;

    public Login(Context context){

        this.context = context;

    }

    public void login( User user ){

        new AuthenticationUserTask(user, this.context).execute();

    }

    public void logout(){

        new LoginDao(this.context).logout();

        if( !GCMRegistrar.getRegistrationId(this.context).isEmpty() ){

            // Try remove the device on the server
            new GCMUnregisterOnServerTask().execute(GCMRegistrar.getRegistrationId(this.context));

            GCMRegistrar.setRegisteredOnServer(context, false);

        }

        redirectNotLoggedUser();

    }

    public void redirectLoggedUser(){

        Intent loginSuccess = new Intent(this.context, Main.class);
        loginSuccess.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.context.startActivity(loginSuccess);
        ((Activity)this.context).finish();

    }

    public void redirectNotLoggedUser(){


        Intent intentLogin = new Intent(this.context, com.asccode.monitorize.telas.Login.class);
        intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.context.startActivity(intentLogin);
        ((Activity)this.context).finish();

    }

    public boolean isUserLogged(){

        LoginDao loginDao = new LoginDao(this.context);

        return loginDao.getTokenUserLogged() != null;

    }

}
