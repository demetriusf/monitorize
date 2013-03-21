package com.asccode.siteswatch.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.asccode.siteswatch.models.User;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 21/03/13
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
public class CreateAccount extends Activity {

    private User user;
    private EditText editTextEmail;
    private EditText editTextPwd;
    private EditText editTextRePwd;
    private Button btnRegister;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_account);

        // Recovery views
        this.editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        this.editTextPwd = (EditText) findViewById(R.id.editTextPwd);
        this.editTextRePwd = (EditText) findViewById(R.id.editTextRePwd);
        this.btnRegister = (Button) findViewById(R.id.btnRegister);

        this.btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String email = editTextEmail.getEditableText().toString();
                String pwd = editTextPwd.getEditableText().toString();

                if(validFields()){

                    user = new User();
                    user.setEmail(email);
                    user.setPwd(pwd);

                    // Faz conexão e cadastrar
                    if( true ){

                        Intent inicialIntent = new Intent(CreateAccount.this, Inicial.class);
                        startActivity(inicialIntent);

                    }

                }

            }

        });

    }

    private boolean validFields(){

        if( this.editTextEmail.getEditableText().toString().isEmpty() || !Pattern.compile("(\\w)+@(\\w)+[.](\\w)+([.](\\w)*)?$").matcher(this.editTextEmail.getEditableText().toString()).find()){

            Toast.makeText(this, getString(R.string.fbEmptyEmail), Toast.LENGTH_LONG).show();
            return false;

        }else if( this.editTextPwd.getEditableText().toString().isEmpty() ){

            Toast.makeText(this, getString(R.string.fbEmptyPwd), Toast.LENGTH_LONG).show();
            return false;

        }else if( !this.editTextPwd.getEditableText().toString().equals(this.editTextRePwd.getEditableText().toString()) ){

            Toast.makeText(this, getString(R.string.fbNotEqualPwd), Toast.LENGTH_LONG).show();
            return false;

        }

        return true;

    }

}