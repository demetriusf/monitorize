package com.asccode.siteswatch.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.asccode.siteswatch.models.User;
import com.asccode.siteswatch.support.WebServiceOperations;
import com.asccode.siteswatch.telas.Login;
import com.asccode.siteswatch.telas.R;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 25/03/13
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationUserTask extends AsyncTask<Object, Object, Boolean> {

    private User user;
    private Login loginContext;
    private ProgressDialog progressDialog;

    public AuthenticationUserTask(User user, Login loginContext){

        this.user = user;
        this.loginContext = loginContext;

    }

    @Override
    protected void onPreExecute(){

        this.progressDialog = ProgressDialog.show(this.loginContext, this.loginContext.getString(R.string.dialogTitleAuthUser), this.loginContext.getString(R.string.dialogBodyAuthUser), true, true);

    }

    @Override
    protected Boolean doInBackground(Object... objects) {

        return new WebServiceOperations().userAuthentication(this.user);

    }

    @Override
    protected void onPostExecute( Boolean result ){

        this.progressDialog.dismiss();

        if(result){

            this.loginContext.authenticationSuccess();

        }else{

            ProgressDialog.Builder dialog = new ProgressDialog.Builder(this.loginContext);
            dialog.setCancelable(false);
            dialog.setIcon(android.R.drawable.ic_dialog_alert);
            dialog.setTitle(this.loginContext.getString(R.string.fbDialogErrorTitleUserAuthentication));
            dialog.setMessage(this.loginContext.getString(R.string.fbDialogErrorBodyUserAuthentication));
            dialog.setPositiveButton(this.loginContext.getString(R.string.fbDialogErrorPositiveButtonUserAuthentication), null);
            dialog.show();

        }

    }

}
