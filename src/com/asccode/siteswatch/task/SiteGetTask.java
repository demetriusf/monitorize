package com.asccode.siteswatch.task;

import android.content.Context;
import android.os.AsyncTask;
import com.asccode.siteswatch.models.Site;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 17/04/13
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class SiteGetTask extends AsyncTask<Site, Object, Boolean> implements Runnable {

    private boolean requestSuccessfully = false;
    private Context context;

    public SiteGetTask(Context context) {

        this.context = context;

    }

    @Override
    public void onPreExecute(){

        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    protected Boolean doInBackground(Site... sites) {

        Site site = sites[0];

        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPostExecute( Boolean result ){

        this.requestSuccessfully = result;
        this.enableFieds();

        if(result){

            ((com.asccode.siteswatch.telas.Site)this.context).updateFieldsWithSiteValues();

        }

    }

    @Override
    public void run() {

        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        if(!this.requestSuccessfully){

            this.enableFieds();
            this.cancel(true);

        }


    }

    private void enableFieds(){

        ((com.asccode.siteswatch.telas.Site)this.context).enableFields();

    }
}
