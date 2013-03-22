package com.asccode.siteswatch.support;

import android.util.Log;
import android.widget.Toast;
import com.asccode.siteswatch.models.User;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: demetrius
 * Date: 21/03/13
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceOperations {

    private final static String TAG_DEBUG = "WEBSERVICE";
    private final static String URL_WEB_SERVICE = "http://10.0.2.2/sites-watch-server/webservice/user";


    public Boolean registerUser(User user){

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(WebServiceOperations.URL_WEB_SERVICE);

        // Set Header
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");

        //Return
        Boolean response = false;

        try {

            Gson gson = new Gson();
            httpPut.setEntity(new StringEntity(gson.toJson(user)));
            HttpResponse httpResponse = defaultHttpClient.execute(httpPut);
            Map<String, String> jsonResponse = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), Map.class) ;

            response = Boolean.parseBoolean(jsonResponse.get("feedback"));

            Log.d(WebServiceOperations.TAG_DEBUG, String.valueOf(jsonResponse.get("feedback")) );

        } catch (Exception e) {

            Log.e(WebServiceOperations.TAG_DEBUG, e.getMessage());

        }

        return response;

    }

}
