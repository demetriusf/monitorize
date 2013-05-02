package com.asccode.siteswatch.support;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.asccode.siteswatch.dao.LoginDao;
import com.asccode.siteswatch.models.User;
import com.asccode.siteswatch.task.AuthenticationUserTask;
import com.asccode.siteswatch.telas.Main;
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

        GCMRegistrar.unregister(this.context);
        
        redirectNotLoggedUser();

    }

    public void redirectLoggedUser(){

        Intent loginSuccess = new Intent(this.context, Main.class);
        loginSuccess.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.context.startActivity(loginSuccess);
        ((Activity)this.context).finish();

    }

    public void redirectNotLoggedUser(){


        Intent intentLogin = new Intent(this.context, com.asccode.siteswatch.telas.Login.class);
        intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.context.startActivity(intentLogin);
        ((Activity)this.context).finish();

    }

    public boolean isUserLogged(){

        LoginDao loginDao = new LoginDao(this.context);

        return loginDao.getTokenUserLogged() != null;

    }

}
