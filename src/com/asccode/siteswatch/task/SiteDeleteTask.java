package com.asccode.siteswatch.task;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.asccode.siteswatch.R;
import com.asccode.siteswatch.models.Site;
import com.asccode.siteswatch.support.WebServiceOperations;
import com.asccode.siteswatch.telas.Main;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 15/04/13
 * Time: 17:01
 * To change this template use File | Settings | File Templates.
 */
public class SiteDeleteTask extends AsyncTask<Object, Object, Boolean>{

    private Site site;
    private Context context;
    private ProgressDialog progressDialog;

    public SiteDeleteTask( Site site, Context context ){

        this.site = site;
        this.context = context;

    }

    @Override
    protected void onPreExecute(){

        this.progressDialog = ProgressDialog.show(this.context, this.context.getString(R.string.dialogTitleSiteDelete), this.context.getString(R.string.dialogBodySiteDelete), true, true);

    }

    @Override
    protected Boolean doInBackground(Object... objects) {

        return new WebServiceOperations().siteDelete(this.site);

    }

    @Override
    protected void onPostExecute(Boolean result){

        this.progressDialog.dismiss();

        if(result){

            ((Main) this.context).getSites().remove(this.site);
            ((Main) this.context).refreshList();

            Toast.makeText(this.context, this.context.getString(R.string.fbAlertSiteDeletedSuccessfully), Toast.LENGTH_LONG).show();

        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setCancelable(true);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle(this.context.getString(R.string.fbDialogErrorTitleSiteDelete));
            builder.setMessage(this.context.getString(R.string.fbDialogErrorBodySiteDelete));
            builder.setPositiveButton(this.context.getString(R.string.fbDialogErrorPositiveButtonSiteDelete), null);
            builder.show();

        }



    }

}
