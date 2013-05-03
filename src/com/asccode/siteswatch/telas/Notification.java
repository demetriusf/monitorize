package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 03/05/13
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
public class Notification extends Activity {

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String message = this.getIntent().getStringExtra("message");

        if( !message.isEmpty() ){

            TextView textView = new TextView(this);

            textView.setText(message);

            setContentView(textView);

        }

    }
}