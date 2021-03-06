package com.asccode.monitorize.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.asccode.monitorize.R;
import com.asccode.monitorize.models.User;

import java.util.regex.Pattern;

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

            String email = editTextEmail.getEditableText().toString();
            String pwd = editTextPwd.getEditableText().toString();

                if( validLogin() ){

                    User user = new User();
                    user.setEmail(email);
                    user.setPwd(pwd);

                    new com.asccode.monitorize.support.Login(Login.this).login(user);

                }

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

    private Boolean validLogin(){

        if( !Pattern.compile(Patterns.EMAIL_ADDRESS.pattern()).matcher(this.editTextEmail.getEditableText().toString()).find() ){

            Toast.makeText(this, getString(R.string.fbAlertEmptyEmail), Toast.LENGTH_LONG).show();
            return false;

        }else if( this.editTextPwd.getEditableText().toString().isEmpty() ){

            Toast.makeText(this, getString(R.string.fbAlertEmptyPwd), Toast.LENGTH_LONG).show();
            return false;

        }

        return true;
    }

}