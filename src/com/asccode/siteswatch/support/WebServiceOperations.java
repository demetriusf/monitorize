package com.asccode.siteswatch.support;

import android.util.Log;
import com.asccode.siteswatch.models.User;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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
    private final static String URL_USER_WEB_SERVICE = "http://10.0.2.2/sites-watch-server/webservice/user";
    private final static String URL_AUTHENTICATION__USER_WEB_SERVICE = "http://10.0.2.2/sites-watch-server/webservice/auth/user";


    public Boolean registerUser(User user){

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(WebServiceOperations.URL_USER_WEB_SERVICE);

        // Set Header
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");

        //Return
        Boolean result = false;

        try {

            Gson gson = new Gson();
            httpPut.setEntity(new StringEntity(gson.toJson(user)));
            HttpResponse httpResponse = defaultHttpClient.execute(httpPut);
            Map<String, String> jsonResponse = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), Map.class) ;

            result = Boolean.parseBoolean(jsonResponse.get("feedback"));

            Log.d(WebServiceOperations.TAG_DEBUG, String.valueOf(jsonResponse.get("feedback")) );

        } catch (Exception e) {

            Log.e(WebServiceOperations.TAG_DEBUG, e.getMessage());

        }

        return result;

    }

    public Boolean userAuthentication(User user){

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(WebServiceOperations.URL_AUTHENTICATION__USER_WEB_SERVICE);

        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        Boolean result = false;

        try{

            Gson gson = new Gson();

            httpPost.setEntity(new StringEntity(gson.toJson(user)));
            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);

            Map<String, String> jsonResponse = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), Map.class);

            result = Boolean.parseBoolean(jsonResponse.get("feedback"));

            Log.d(WebServiceOperations.TAG_DEBUG, String.valueOf(jsonResponse.get("feedback")) );

        }catch (Exception exception){

            Log.e(WebServiceOperations.TAG_DEBUG, exception.getMessage());

        }

        return result;
    }

}
