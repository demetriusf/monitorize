package com.asccode.siteswatch.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.asccode.siteswatch.models.Site;
import com.asccode.siteswatch.support.WebServiceOperations;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 08/04/13
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
public class SiteUpdateTask extends AsyncTask<Object, Object, Boolean> {

    private Site site;
    private String loginUserToken;
    private Context context;
    private ProgressDialog progressDialog;

    public SiteUpdateTask(Site site, String loginUserToken, Context context) {

        this.site = site;
        this.loginUserToken = loginUserToken;
        this.context = context;

    }

    @Override
    protected void onPreExecute(){

        this.progressDialog = new ProgressDialog(this.context);
        this.progressDialog.show();

    }

    @Override
    protected Boolean doInBackground(Object... objects) {

        return new WebServiceOperations().siteUpdate(this.site);

    }

    @Override
    protected void onPostExecute(Boolean result){

        this.progressDialog.dismiss();

        if( result ){

        }

    }

}
