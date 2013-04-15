package com.asccode.siteswatch.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.asccode.siteswatch.models.Site;
import com.asccode.siteswatch.support.WebServiceOperations;
import com.asccode.siteswatch.telas.R;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 08/04/13
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */
public class SiteAddTask extends AsyncTask<Object, Object, Boolean> {

    private Site site;
    private String loginUserToken;
    private Context context;
    private ProgressDialog progressDialog;

    public SiteAddTask(Site site, String loginUserToken, Context context) {

        this.site = site;
        this.loginUserToken = loginUserToken;
        this.context = context;

    }

    @Override
    protected void onPreExecute(){

        this.progressDialog = ProgressDialog.show(this.context, this.context.getString(R.string.dialogTitleSiteAdd), this.context.getString(R.string.dialogBodySiteAdd), true, true);

    }

    @Override
    protected Boolean doInBackground(Object... objects) {

        return new WebServiceOperations().siteAdd(this.site, this.loginUserToken);

    }

    @Override
    protected void onPostExecute(Boolean result){

        this.progressDialog.dismiss();

        if(result){

            //Toast.makeText(this.context, this.context.getString(R.string.fbAlertSiteAddedSuccessfully), Toast.LENGTH_LONG).show();

            ((com.asccode.siteswatch.telas.Site) this.context).setResult(Activity.RESULT_OK);
            ((com.asccode.siteswatch.telas.Site) this.context).finish();

        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setCancelable(true);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle(this.context.getString(R.string.fbDialogErrorTitleSiteAdd));
            builder.setMessage(this.context.getString(R.string.fbDialogErrorBodySiteAdd));
            builder.setPositiveButton(this.context.getString(R.string.fbDialogErrorPositiveButtonSiteAdd), null);
            builder.show();

        }

    }
}
