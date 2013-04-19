package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.*;
import com.asccode.siteswatch.R;
import com.asccode.siteswatch.dao.LoginDao;
import com.asccode.siteswatch.task.SiteAddTask;
import com.asccode.siteswatch.task.SiteRefreshDataEditTask;
import com.asccode.siteswatch.task.SiteUpdateTask;
import org.apache.http.conn.util.InetAddressUtils;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 08/04/13
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class Site extends Activity {

    private EditText editTextNomeSite;
    private EditText editTextEndereco;
    private CheckBox checkBoxReceiveAndroidNotification;
    private CheckBox checkBoxOptPing;
    private Button button;
    private com.asccode.siteswatch.models.Site site;
    private boolean opAdd = true;
    private Timer timer = new Timer();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.site);

        this.editTextNomeSite = (EditText) findViewById(R.id.editTextNomeSite);
        this.editTextEndereco = (EditText) findViewById(R.id.editTextEndereco);
        this.checkBoxReceiveAndroidNotification = (CheckBox) findViewById(R.id.checkBoxReceiveAndroidNotification);
        this.checkBoxOptPing = (CheckBox) findViewById(R.id.checkBoxOptPing);
        this.button = (Button) findViewById(R.id.button);

        this.setSite( (com.asccode.siteswatch.models.Site) getIntent().getSerializableExtra("site") );

        if(this.getSite() != null){

            this.opAdd = false;

            this.button.setText(getString(R.string.btnSiteUpdate));

            this.updateFieldsWithSiteValues();

            final boolean requestSuccessfully = false;

            final SiteRefreshDataEditTask siteRefreshDataEditTask = new SiteRefreshDataEditTask(new LoginDao(this).getTokenUserLogged(), this, requestSuccessfully);
            siteRefreshDataEditTask.execute();

            timer.schedule(new TimerTask() {

                @Override
                public void run() {

                    if( !requestSuccessfully ){

                        siteRefreshDataEditTask.cancel(true);
                        siteRefreshDataEditTask.getProgressDialog().dismiss();

                    }

                }

            }, 6000);

        }else{

            this.setSite(new com.asccode.siteswatch.models.Site());

        }

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSite().setName(editTextNomeSite.getEditableText().toString());
                getSite().setEndereco(editTextEndereco.getEditableText().toString());
                getSite().setReceiveAndroidNotification(checkBoxReceiveAndroidNotification.isChecked());
                getSite().setOptPing(checkBoxOptPing.isChecked());

                if( isValidSite( site ) ){

                    if( !opAdd ){ // Update

                        new SiteUpdateTask(site, new LoginDao(Site.this).getTokenUserLogged(), Site.this).execute();

                    }else{ //Save

                        new SiteAddTask(site, new LoginDao(Site.this).getTokenUserLogged(), Site.this).execute();

                    }

                }

            }
        });

    }


    public com.asccode.siteswatch.models.Site getSite() {

        return this.site;

    }

    public void setSite(com.asccode.siteswatch.models.Site site) {
        this.site = site;
    }

    public void updateFieldsWithSiteValues(){

        this.editTextNomeSite.setText(this.getSite().getName());
        this.editTextEndereco.setText(this.getSite().getEndereco());
        this.checkBoxReceiveAndroidNotification.setChecked(this.getSite().getReceiveAndroidNotification());
        this.checkBoxOptPing.setChecked(this.getSite().getOptPing());

    }

    private boolean isValidSite( com.asccode.siteswatch.models.Site site ){

        if( site.getName().isEmpty() ){

            Toast.makeText(this, getString(R.string.fbAlertEmptySiteName), Toast.LENGTH_LONG).show();

            return false;

        }else if( !URLUtil.isValidUrl( site.getEndereco() ) && !InetAddressUtils.isIPv4Address(site.getEndereco()) ){

            Toast.makeText(this, getString(R.string.fbAlertEmptySiteEndereco), Toast.LENGTH_LONG).show();

            return false;

        }

        return true;

    }

}