package com.example.sagarpreetchadha.topmovies;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by sagarpreet chadha on 30-06-2016.
 */
public class youtube_downloader extends AsyncTask<String , Void , String> {
    Detailed_Activity  mactivity ;
    ProgressDialog mProgress ;

    public youtube_downloader(Detailed_Activity mactivity) {
        this.mactivity = mactivity;
    }

    @Override
    protected void onPreExecute() {
        mProgress = new ProgressDialog(mactivity);
        mProgress.setTitle("Loading");
        mProgress.setMessage("Wait while loading...");
        mProgress.show();
        //mProgress.dismiss()
    }

    @Override

    protected String doInBackground(String... params) {
        if(params.length==0){
            return  null ;
        }
        StringBuffer buffer=new StringBuffer() ;
        URL url = null;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            return  null ;
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            return  null ;
        }
        try {
            urlConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            return  null ;
        }
        try {
            urlConnection.connect();
        } catch (IOException e) {
            return  null ;
        }
        //StringBuffer buffer = new StringBuffer();
        InputStream inputStream=null ;
        try {
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            return  null ;
        }
        if (inputStream == null) {    return null; }
        Scanner s = new Scanner(inputStream);
        while (s.hasNext())
        {     buffer.append(s.nextLine()); }
        Log. i ("jsondata", buffer.toString());
        return String.valueOf(parseJson(buffer.toString()));
    }

    private String parseJson(String s) {
        JSONObject obj= null;
        try {
            obj = new JSONObject(s);
        } catch (JSONException e) {
            return null ;
        }

        try {
            JSONArray x=obj.getJSONArray("results") ;
            JSONObject y = x.getJSONObject(0) ;
            String ss=y.getString("key") ;
            return  ss ;
        } catch (JSONException e) {
            return null ;
        }


    }

    @Override
    protected void onPostExecute(String datastorageclasses) {
        mProgress.dismiss();
        mactivity.processresult2(datastorageclasses);

    }
}
