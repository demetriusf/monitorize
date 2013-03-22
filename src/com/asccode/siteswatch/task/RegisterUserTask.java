package com.asccode.siteswatch.task;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import com.asccode.siteswatch.models.User;
import com.asccode.siteswatch.support.WebServiceOperations;
import com.asccode.siteswatch.telas.CreateAccount;
import com.asccode.siteswatch.telas.R;

/**
 * Created with IntelliJ IDEA.
 * User: demetrius
 * Date: 21/03/13
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public class RegisterUserTask extends AsyncTask<Object, Object, Boolean> {

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
    protected Boolean doInBackground(Object... objects) {

        return new WebServiceOperations().registerUser(this.user);

    }

    @Override
    protected void onPostExecute(Boolean result){

        this.progressDialog.dismiss();

        if(result){

            Toast.makeText(this.context, "Registered successfully", Toast.LENGTH_LONG).show();

            this.context.registerSuccess();

        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setCancelable(true);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("Error");
            builder.setMessage("An error occurred. Check the internet connection.");
            builder.setPositiveButton("OK", null);
            builder.show();

        }



    }
}
