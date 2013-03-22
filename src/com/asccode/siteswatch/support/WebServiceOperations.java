package com.asccode.siteswatch.support;

import com.asccode.siteswatch.models.User;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: demetrius
 * Date: 21/03/13
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceOperations {

    private final static String URL_REGISTER = "";


    public String registerUser(User user){

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost();

        // Set Header
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try {

            Gson gson = new Gson();
            httpPost.setEntity( new StringEntity( gson.toJson(user) ));
            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

        return "";

    }

}
