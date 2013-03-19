package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 19/03/13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class Inicial extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String email = getIntent().getStringExtra("email");



        Toast.makeText(this, getIntent().getStringExtra("email"), Toast.LENGTH_LONG).show();

    }
}