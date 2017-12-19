package com.example.samuel.lab_3;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by samuel on 12/16/17.
 */


public class GetJson extends AsyncTask<String, String, JSONObject> {

    public interface AsyncResponse{
        void processFinish(JSONObject result) throws JSONException;
    }
    private AsyncResponse asyncResponse = null;
    GetJson(AsyncResponse asyncResponse){
        this.asyncResponse = asyncResponse;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        String searchSeq = params[0];
        String id = params[1];



        HttpURLConnection connection = null;
        JSONObject json;
        BufferedReader bufferedReader = null;
        final String url = "http://getnames-getnames.a3c1.starter-us-west-1.openshiftapps.com/getnames/";
        try {
            connection =  (HttpURLConnection) new URL(url + id.toString() + "/" + searchSeq).openConnection();
            connection.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            json = new JSONObject(bufferedReader.readLine());
            return json;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        if (asyncResponse != null){
            try {
                asyncResponse.processFinish(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

