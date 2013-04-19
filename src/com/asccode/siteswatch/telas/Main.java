package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.asccode.siteswatch.R;
import com.asccode.siteswatch.dao.LoginDao;
import com.asccode.siteswatch.gcm.GCMDeviceManager;
import com.asccode.siteswatch.models.Site;
import com.asccode.siteswatch.task.SiteDeleteTask;
import com.asccode.siteswatch.task.SiteListTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 19/03/13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class Main extends Activity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<Site> sites = new ArrayList<Site>();
    private Site selectedSite;
    private static final String TAG_DEBUG = "MAIN";
    private static final int REQUEST_CODE_ACTIVITY_SITE = 1;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if( new com.asccode.siteswatch.support.Login(this).isUserLogged()){

            setContentView(R.layout.inicial);

            this.listView  = (ListView) findViewById(R.id.listSites);

            this.adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.getSites() );

            this.listView.setAdapter(adapter);

            this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    selectedSite = (Site) adapterView.getItemAtPosition(i);

                    registerForContextMenu(Main.this.listView);

                    return false;  //To change body of implemented methods use File | Settings | File Templates.

                }


            });


            this.recoverySites();

        }

    }

    public List<Site> getSites() {
        return sites;
    }

    public void refreshList(){

        this.adapter.notifyDataSetChanged();

    }

    private void recoverySites(){

        new SiteListTask(new LoginDao(this).getTokenUserLogged(), this).execute();

    }

    @Override
    public void onResume(){

        super.onResume();

        com.asccode.siteswatch.support.Login supportLogin = new com.asccode.siteswatch.support.Login(this);

        if( !supportLogin.isUserLogged() ){

            supportLogin.redirectNotLoggedUser();

        }

        GCMDeviceManager.manager(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;   //To change body of overridden methods use File | Settings | File Templates.

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch( item.getItemId() ){

            case R.id.menuItemLogout:
                new com.asccode.siteswatch.support.Login(this).logout();
                break;

            case R.id.menuItemSiteAdd:
                startActivityForResult(new Intent(this, com.asccode.siteswatch.telas.Site.class), Main.REQUEST_CODE_ACTIVITY_SITE);
                break;

            case R.id.menuItemRefreshList:
                this.recoverySites();
                break;

            default:
                Log.d(Main.TAG_DEBUG, "ANY EVENT IS ASSOCIATED WITH THE MENU ITEM: "+item.getTitle().toString());
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.main_context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch( item.getItemId() ){

            case R.id.menuItemMainContextEdit:

                if( this.selectedSite != null ){

                    Intent intent = new Intent(this, com.asccode.siteswatch.telas.Site.class);
                    intent.putExtra("site", this.selectedSite);

                    startActivityForResult(intent, Main.REQUEST_CODE_ACTIVITY_SITE);
                    break;

                }
                break;

            case R.id.menuItemMainContextRemove:

                if( this.selectedSite != null ){

                    new SiteDeleteTask(this.selectedSite, this).execute();

                }
                break;

            default:
                Log.d(Main.TAG_DEBUG, "ANY EVENT IS ASSOCIATED WITH THE CONTEXT MENU ITEM: "+item.getTitle().toString());
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if( resultCode == RESULT_OK ){

            if( requestCode == Main.REQUEST_CODE_ACTIVITY_SITE ){

                this.recoverySites();

            }

        }


    }
}