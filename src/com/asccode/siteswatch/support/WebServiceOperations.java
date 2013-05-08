package com.asccode.siteswatch.support;

import android.util.Log;
import com.asccode.siteswatch.models.Site;
import com.asccode.siteswatch.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: demetrius
 * Date: 21/03/13
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceOperations {

    private static final String TAG_DEBUG = "WEBSERVICE";
    private static final String URL_USER_WEB_SERVICE = "http://10.0.2.2/sites-watch-server/webservice/user";
    private static final String URL_AUTHENTICATION_USER_WEB_SERVICE = "http://10.0.2.2/sites-watch-server/webservice/auth/user";
    private static final String URL_SITE_WEB_SERVICE = "http://10.0.2.2/sites-watch-server/webservice/site";
    private static final String URL_GCM_WEB_SERVICE = "http://10.0.2.2/sites-watch-server/webservice/gcm";
    private static final String TOKEN_MAGIC_KEY = "5I735_W47CH~";

    public Boolean registerUser(User user){

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(WebServiceOperations.URL_USER_WEB_SERVICE);

        // Set Header
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json; charset=UTF-8");

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

    public String userAuthentication(User user){

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(WebServiceOperations.URL_AUTHENTICATION_USER_WEB_SERVICE);

        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json; charset=UTF-8");

        String result = "";

        try{

            Gson gson = new Gson();

            String email = Security.md5( user.getEmail() );
            String pwd = Security.md5( user.getPwd() );

            String json = String.format("{\"email\":\"%s\",\"pwd\":\"%s\"}", email, pwd);

            httpPost.setEntity(new StringEntity(json));
            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);

            Map<String, String> jsonResponse = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), Map.class);

            String feedback = jsonResponse.get("feedback");

            if(feedback != "" && feedback.startsWith(Security.md5(WebServiceOperations.TOKEN_MAGIC_KEY))){

                result = feedback;

            }

            Log.d(WebServiceOperations.TAG_DEBUG, "Authentication = "+feedback );

        }catch (Exception exception){

            Log.e(WebServiceOperations.TAG_DEBUG, exception.getMessage());

        }

        return result;

    }

    public List<Site> siteList(String loginUserToken){

        String queryString = String.format("/loginUserToken/%s", loginUserToken);

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(WebServiceOperations.URL_SITE_WEB_SERVICE+queryString);

        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json; charset=UTF-8");

        ArrayList<Site> sites = new ArrayList<Site>();

        try{
            Gson gson = new Gson();

            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);

            String jsonResponse = EntityUtils.toString(httpResponse.getEntity());

            sites = gson.fromJson(jsonResponse, new TypeToken<ArrayList<Site>>(){}.getType() );

        }catch(Exception exception){

            exception.printStackTrace();
            Log.e(WebServiceOperations.TAG_DEBUG, exception.getMessage());

        }

        return sites;

    }

    public Site siteGet(Site site, String loginUserToken) {

        String queryString = String.format("/identifier/%s/loginUserToken/%s", Security.md5( site.getId() ), loginUserToken );

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(WebServiceOperations.URL_SITE_WEB_SERVICE+queryString);

        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json; charset=UTF-8");

        Site siteReturn = null;

        try {

            Gson gson = new Gson();

            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);

            String jsonResponse = EntityUtils.toString(httpResponse.getEntity());

            siteReturn = gson.fromJson(jsonResponse, Site.class);

        } catch (Exception exception) {

            Log.e(WebServiceOperations.TAG_DEBUG, exception.getMessage());

        }

        return siteReturn;

    }

    public Boolean siteAdd(Site site, String loginUserToken){

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(WebServiceOperations.URL_SITE_WEB_SERVICE);

        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json; charset=UTF-8");

        Boolean result = false;

        try {

            Gson gson = new Gson();

            String jsonSite = gson.toJson(site);

            String stringF = String.format("{\"site\":%s,\"loginUserToken\":\"%s\"}", jsonSite, loginUserToken);

            httpPut.setEntity(new StringEntity(stringF));
            HttpResponse httpResponse = defaultHttpClient.execute(httpPut);

            String responseEntity = EntityUtils.toString(httpResponse.getEntity());

            Map<String, String> jsonResponse = gson.fromJson(responseEntity, Map.class);

            result = Boolean.parseBoolean(jsonResponse.get("feedback"));

            Log.d(WebServiceOperations.TAG_DEBUG, String.valueOf(jsonResponse.get("feedback")) );

        } catch (Exception exception) {

            Log.e(WebServiceOperations.TAG_DEBUG, exception.getMessage());

        }

        return result;

    }

    public Boolean siteUpdate(Site site, String loginUserToken) {


        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(WebServiceOperations.URL_SITE_WEB_SERVICE);

        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json; charset=UTF-8");

        Boolean result = false;

        try {

            Gson gson = new Gson();

            String jsonSite = gson.toJson(site);

            String stringF = String.format("{\"site\":%s,\"loginUserToken\":\"%s\"}", jsonSite, loginUserToken);

            httpPost.setEntity(new StringEntity(stringF));

            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);

            String responseJson = EntityUtils.toString(httpResponse.getEntity());

            Map<String, String> jsonResponse = gson.fromJson(responseJson, Map.class) ;

            result = Boolean.parseBoolean(jsonResponse.get("feedback"));

        } catch (Exception e) {

            Log.e(WebServiceOperations.TAG_DEBUG, e.getMessage());

        }

        return result;

    }

    public Boolean siteDelete( Site site ){

        String queryString = String.format("/identifier/%s", Security.md5( site.getId() ));

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(WebServiceOperations.URL_SITE_WEB_SERVICE+queryString);

        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Content-type", "application/json; charset=UTF-8");

        Boolean result = false;

        try {

            Gson gson = new Gson();

            HttpResponse httpResponse = defaultHttpClient.execute(httpDelete);

            String responseEntity = EntityUtils.toString(httpResponse.getEntity());

            Map<String, String> jsonResponse = gson.fromJson(responseEntity, Map.class);

            result = Boolean.parseBoolean(jsonResponse.get("feedback"));

        } catch (Exception exception) {

            Log.e(WebServiceOperations.TAG_DEBUG, exception.getMessage());

        }

        return result;

    }

    public Boolean registerUserGCM(String regId, String loginUserToken){

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(WebServiceOperations.URL_GCM_WEB_SERVICE);

        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json; charset=UTF-8");

        Boolean result = false;

        try {

            Gson gson = new Gson();

            String stringF = String.format("{\"regId\":\"%s\",\"loginUserToken\":\"%s\"}", regId, loginUserToken);

            httpPut.setEntity(new StringEntity(stringF));
            HttpResponse httpResponse = defaultHttpClient.execute(httpPut);

            String responseEntity = EntityUtils.toString(httpResponse.getEntity());

            Map<String, String> jsonResponse = gson.fromJson(responseEntity, Map.class);

            result = Boolean.parseBoolean(jsonResponse.get("feedback"));

            Log.d(WebServiceOperations.TAG_DEBUG, responseEntity);

        } catch (Exception exception) {

            Log.e(WebServiceOperations.TAG_DEBUG, exception.getMessage());

        }

        return result;

    }
    
    public Boolean unregisterUserGCM(String regId){

        String queryString = String.format("/regId/%s", regId);
        
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(WebServiceOperations.URL_GCM_WEB_SERVICE+queryString);

        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Content-type", "application/json; charset=UTF-8");

        Boolean result = false;

        try {

            Gson gson = new Gson();
            
            HttpResponse httpResponse = defaultHttpClient.execute(httpDelete);

            String responseEntity = EntityUtils.toString(httpResponse.getEntity());
            
            Map<String, String> jsonResponse = gson.fromJson(responseEntity, Map.class);

            result = Boolean.parseBoolean(jsonResponse.get("feedback"));


        } catch (Exception exception) {

            Log.e(WebServiceOperations.TAG_DEBUG, exception.getMessage());

        }

        return result;

    }

}
