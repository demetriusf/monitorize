package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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

        //Isn't user logged?
        LoginDao loginDao = new LoginDao(this);

        User userLogged = loginDao.getLogged();

        if( userLogged == null ){  // Redirect to Login

            Intent intentLogin = new Intent(this, Login.class);
            startActivity(intentLogin);


        }else{

            setContentView(R.layout.inicial);

            this.listView  = (ListView) findViewById(R.id.listSites);

            ArrayAdapter<String> sites = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"Tem Estilo", "Carrapeta", "Jackie", "Asccode", "Olhar Criativo"});

            this.listView.setAdapter(sites);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;   //To change body of overridden methods use File | Settings | File Templates.

    }
}