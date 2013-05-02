package com.asccode.siteswatch.task;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.asccode.siteswatch.R;
import com.asccode.siteswatch.models.Site;
import com.asccode.siteswatch.support.WebServiceOperations;
import com.asccode.siteswatch.telas.Main;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 12/04/13
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class SiteListTask extends AsyncTask<Object, Object, List<Site>> {

    private String loginUserToken;
    private Main context;
    private ProgressDialog progressDialog;

    public SiteListTask(String loginUserToken, Main context) {

        this.loginUserToken = loginUserToken;
        this.context = context;

    }

    @Override
    protected void onPreExecute(){

        this.progressDialog = ProgressDialog.show(this.context, this.context.getString(R.string.dialogTitleSiteList), this.context.getString(R.string.dialogBodySiteList), true, true);

    }

    @Override
    protected List<Site> doInBackground(Object... objects) {

        return new WebServiceOperations().siteList(this.loginUserToken);

    }

    @Override
    protected void onPostExecute( List<Site> sites ){

        this.progressDialog.dismiss();

        if( sites.size() > 0 ){

            context.getSites().clear();
            context.getSites().addAll(sites);
            context.refreshList();

        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setCancelable(false);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle(this.context.getString(R.string.fbDialogErrorTitleSiteList));
            builder.setMessage(this.context.getString(R.string.fbDialogErrorBodySiteList));
            builder.setPositiveButton(this.context.getString(R.string.fbDialogErrorPositiveButtonSiteList), null);

            builder.show();

        }

    }

}
