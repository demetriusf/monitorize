package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.*;
import com.asccode.siteswatch.dao.LoginDao;
import com.asccode.siteswatch.task.SiteAddTask;
import com.asccode.siteswatch.task.SiteGetTask;
import com.asccode.siteswatch.task.SiteUpdateTask;
import org.apache.http.conn.util.InetAddressUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 08/04/13
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class Site extends Activity {

    private TextView editTextsiteTextLoading;
    private EditText editTextNomeSite;
    private EditText editTextEndereco;
    private CheckBox checkBoxReceiveAndroidNotification;
    private CheckBox checkBoxOptPing;
    private Button button;
    private com.asccode.siteswatch.models.Site site;
    private boolean opAdd = true;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.site);

        this.editTextsiteTextLoading = (TextView) findViewById(R.id.editTextsiteTextLoading);
        this.editTextNomeSite = (EditText) findViewById(R.id.editTextNomeSite);
        this.editTextEndereco = (EditText) findViewById(R.id.editTextEndereco);
        this.checkBoxReceiveAndroidNotification = (CheckBox) findViewById(R.id.checkBoxReceiveAndroidNotification);
        this.checkBoxOptPing = (CheckBox) findViewById(R.id.checkBoxOptPing);
        this.button = (Button) findViewById(R.id.button);

        this.site = (com.asccode.siteswatch.models.Site) getIntent().getSerializableExtra("site");

        if(this.site != null){

            this.opAdd = false;

            this.button.setText(getString(R.string.btnSiteUpdate));

            this.updateFieldsWithSiteValues();

            this.disableFields();

            new SiteGetTask(this).execute(this.site);

        }else{

            this.site = new com.asccode.siteswatch.models.Site();

        }

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                site.setName(editTextNomeSite.getEditableText().toString());
                site.setEndereco(editTextEndereco.getEditableText().toString());
                site.setReceiveAndroidNotification(checkBoxReceiveAndroidNotification.isChecked());
                site.setOptPing(checkBoxOptPing.isChecked());

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

    public void updateFieldsWithSiteValues(){

        this.editTextNomeSite.setText(this.site.getName());
        this.editTextEndereco.setText(this.site.getEndereco());
        this.checkBoxReceiveAndroidNotification.setChecked(this.site.getReceiveAndroidNotification());
        this.checkBoxOptPing.setChecked(this.site.getOptPing());

    }

    public void disableFields(){

        this.editTextNomeSite.setEnabled(false);
        this.editTextEndereco.setEnabled(false);
        this.checkBoxReceiveAndroidNotification.setEnabled(false);
        this.checkBoxOptPing.setEnabled(false);
        this.button.setEnabled(false);

        this.editTextsiteTextLoading.setVisibility(1); // Show load

    }

    public void enableFields(){

        this.editTextNomeSite.setEnabled(true);
        this.editTextEndereco.setEnabled(true);
        this.checkBoxReceiveAndroidNotification.setEnabled(true);
        this.checkBoxOptPing.setEnabled(true);
        this.button.setEnabled(true);

        this.editTextsiteTextLoading.setVisibility(0); // Hide load

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