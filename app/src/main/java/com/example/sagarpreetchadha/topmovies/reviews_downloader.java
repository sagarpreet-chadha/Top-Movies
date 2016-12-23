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
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sagarpreet chadha on 02-07-2016.
 */
public class reviews_downloader extends AsyncTask<String , Void , ArrayList<reviewsdata> > {
    Detailed_Activity  mactivity ;
    ProgressDialog mProgress ;

    public reviews_downloader(Detailed_Activity mactivity) {
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

    protected ArrayList<reviewsdata> doInBackground(String... params) {
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
        return parseJson(buffer.toString());
    }

    private ArrayList<reviewsdata> parseJson(String s) {
        JSONObject obj= null;
        ArrayList<reviewsdata> arrayList = new ArrayList<reviewsdata>();
        try {
            obj = new JSONObject(s);
            JSONArray arr=obj.getJSONArray("results") ;
            if(arr.length()>0)
            {
                reviewsdata x ;
                String review  , author ;
                for(int i=0 ; i<arr.length() ; i++)
                {
                    JSONObject temp=arr.getJSONObject(i) ;
                    review=temp.getString("content") ;
                    author=temp.getString("author") ;
                    x=new reviewsdata(review , author) ;
                    arrayList.add(x);

                }
            }
            return  arrayList ;
        } catch (JSONException e) {
            return null ;
        }



    }

    @Override
    protected void onPostExecute(ArrayList<reviewsdata> reviewsdatas) {
        mProgress.dismiss();
        mactivity.processresult3(reviewsdatas);
    }
}
