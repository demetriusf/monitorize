package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 19/03/13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class Main extends Activity {

    private ListView listView;
    private static final String TAG_DEBUG = "MAIN";

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if( new com.asccode.siteswatch.support.Login(this).isUserLogged()){

            setContentView(R.layout.inicial);

            this.listView  = (ListView) findViewById(R.id.listSites);

            ArrayAdapter<String> sites = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"Tem Estilo", "Carrapeta", "Jackie", "Asccode", "Olhar Criativo"});

            this.listView.setAdapter(sites);

            registerForContextMenu(this.listView);

        }

    }

    @Override
    public void onResume(){

        super.onResume();

        com.asccode.siteswatch.support.Login supportLogin = new com.asccode.siteswatch.support.Login(this);

        if( !supportLogin.isUserLogged() ){

            supportLogin.redirectNotLoggedUser();

        }

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
                startActivity(new Intent(this, Site.class));
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
                Toast.makeText( this, "Editar", Toast.LENGTH_LONG ).show();
                break;
            default:
                Log.d(Main.TAG_DEBUG, "ANY EVENT IS ASSOCIATED WITH THE CONTEXT MENU ITEM: "+item.getTitle().toString());
        }

        return true;
    }
}