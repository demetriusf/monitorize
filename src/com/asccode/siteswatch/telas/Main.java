package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.asccode.siteswatch.dao.LoginDao;
import com.asccode.siteswatch.models.User;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 19/03/13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class Main extends Activity {

    private ListView listView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if( this.isUserLogged() ){

            setContentView(R.layout.inicial);

            this.listView  = (ListView) findViewById(R.id.listSites);

            ArrayAdapter<String> sites = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"Tem Estilo", "Carrapeta", "Jackie", "Asccode", "Olhar Criativo"});

            this.listView.setAdapter(sites);

        }



    }

    @Override
    public void onResume(){

        super.onResume();

        this.redirectUserNotLogged();

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

            default: Toast.makeText(this, "Opção não encontrada", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private void redirectUserNotLogged(){

        if( !this.isUserLogged() ){

            Intent intentLogin = new Intent(this, Login.class);
            startActivity(intentLogin);

        }

    }

    private boolean isUserLogged(){

        LoginDao loginDao = new LoginDao(this);

        User userLogged = loginDao.getLogged();

        return userLogged != null;

    }

}