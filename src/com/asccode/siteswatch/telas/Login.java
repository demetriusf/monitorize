package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 19/03/13
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */
public class Login extends Activity {

    private EditText editTextEmail;
    private EditText editTextPwd;
    private Button btnLogar;
    private Button btnCriarConta;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        // Recovery views
        this.editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        this.editTextPwd = (EditText) findViewById(R.id.editTextPwd);
        this.btnLogar = (Button) findViewById(R.id.btnLogar);
        this.btnCriarConta = (Button) findViewById(R.id.btnCriarConta);

        this.btnLogar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(Login.this, "Botao logar clicado", Toast.LENGTH_SHORT).show();

            }

        });

        this.btnCriarConta.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick( View view ){

                Intent intent = new Intent(Login.this, CreateAccount.class);

                startActivity(intent);

            }

        });

    }

}