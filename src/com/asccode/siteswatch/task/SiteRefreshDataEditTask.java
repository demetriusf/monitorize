package com.asccode.siteswatch.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.asccode.siteswatch.models.Site;
import com.asccode.siteswatch.support.WebServiceOperations;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 17/04/13
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class SiteRefreshDataEditTask extends AsyncTask<Object, Object, Boolean>{

    private String loginUserToken;
    private boolean requestSuccessfully = false;
    private Context context;
    private ProgressDialog progressDialog;

    public SiteRefreshDataEditTask(String loginUserToken, Context context, boolean requestSuccessfully) {

        this.loginUserToken = loginUserToken;
        this.context = context;
        this.requestSuccessfully = requestSuccessfully;

    }

    @Override
    public void onPreExecute(){

        this.progressDialog = ProgressDialog.show(this.context, "Aguarde...", "Atualizando", true, true);

    }

    @Override
    protected Boolean doInBackground(Object... objects) {

        Site returnedSite = new WebServiceOperations().siteGet(((com.asccode.siteswatch.telas.Site)this.context).getSite(), this.loginUserToken);

        if( returnedSite != null ){

            ((com.asccode.siteswatch.telas.Site)this.context).setSite(returnedSite);

            return true;

        }

        return false;

    }

    @Override
    public void onPostExecute( Boolean result ){

        this.requestSuccessfully = result;

        this.getProgressDialog().dismiss();

        if(result){

            ((com.asccode.siteswatch.telas.Site)this.context).updateFieldsWithSiteValues();

        }

    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }
}
