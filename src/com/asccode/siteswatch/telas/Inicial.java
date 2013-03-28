package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.asccode.siteswatch.dao.LoginDao;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 19/03/13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class Inicial extends Activity {

    private ListView listView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.inicial);

        this.listView  = (ListView) findViewById(R.id.listSites);

        ArrayAdapter<String> sites = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"Tem Estilo", "Carrapeta", "Jackie", "Asccode", "Olhar Criativo"});

        this.listView.setAdapter(sites);

        Toast.makeText(this, new LoginDao(this).getLogged().getEmail(), Toast.LENGTH_LONG).show();

    }

}