package com.asccode.siteswatch.models;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 21/03/13
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public class User implements Serializable {

    private String email;
    private String pwd;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        if(!Pattern.compile("^(\\w)+@(\\w)+[.](\\w)+([.](\\w)*)?$").matcher(email).find()){

            throw new IllegalArgumentException("The email needs to be valid.");

        }

        this.email = email;

    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
