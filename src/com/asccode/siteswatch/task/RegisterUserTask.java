package com.asccode.siteswatch.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import com.asccode.siteswatch.models.User;
import com.asccode.siteswatch.support.WebServiceOperations;
import com.asccode.siteswatch.telas.CreateAccount;

/**
 * Created with IntelliJ IDEA.
 * User: demetrius
 * Date: 21/03/13
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public class RegisterUserTask extends AsyncTask<Object, Object, String> {

    private User user;
    private CreateAccount context;
    private ProgressDialog progressDialog;

    public RegisterUserTask( User user, CreateAccount context ){

        this.user = user;
        this.context = context;

    }

    @Override
    protected void onPreExecute(){

        this.progressDialog = ProgressDialog.show(this.context, "Aguarde...", "Seu cadastro está sendo concluído", true, true);

    }

    @Override
    protected String doInBackground(Object... objects) {
        return new WebServiceOperations().registerUser(this.user);
    }

    @Override
    protected void onPostExecute(String result){

        this.progressDialog.dismiss();

        Toast.makeText(this.context, result, Toast.LENGTH_LONG).show();

        this.context.registerSuccess();


    }
}
