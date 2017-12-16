package com.example.samuel.lab_3;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by samuel on 12/16/17.
 */


public class GetJson extends AsyncTask<String, String, String> {

    public interface AsyncResponse{
        void processFinish(String result);
    }
    private AsyncResponse asyncResponse = null;
    public GetJson(AsyncResponse asyncResponse){
        this.asyncResponse = asyncResponse;
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];

        Integer id = 1;
        id.toString();

        String name = "Emm";
        HttpURLConnection connection = null;
        JSONObject json;
        BufferedReader bufferedReader = null;
        try {
            connection =  (HttpURLConnection) new URL(url).openConnection();
            connection.connect();

            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            json = new JSONObject( bufferedReader.readLine());

            return json.toString();


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
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (asyncResponse != null){
            asyncResponse.processFinish(result);
        }

    }


}

