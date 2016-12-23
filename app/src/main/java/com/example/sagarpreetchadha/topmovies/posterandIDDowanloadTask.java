package com.example.sagarpreetchadha.topmovies;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sagarpreet chadha on 28-06-2016.
 */
public class posterandIDDowanloadTask extends AsyncTask<String , Void ,ArrayList<posterAnd_ID_data> > {

    MainActivity mactivity ;
    ProgressDialog mProgress ;
    public posterandIDDowanloadTask(MainActivity mactivity) {
        this.mactivity = mactivity;
    }

    @Override

    protected ArrayList<posterAnd_ID_data> doInBackground(String... params) {
        if (params.length == 0)
            return null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConnection =
                    (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream =
                    urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }
            Scanner s = new Scanner(inputStream);
            while (s.hasNext()) {
                buffer.append(s.nextLine());
            }
            Log.i("jsondata", buffer.toString());
        } catch (MalformedURLException e) {
            return null;
        } catch (ProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return parseJson(buffer.toString());


    }


    private ArrayList<posterAnd_ID_data> parseJson(String json) {

        JSONObject obj = null;
        try {
            obj = new JSONObject(json);
            JSONArray arr=obj.getJSONArray("results") ;
            posterAnd_ID_data data ; ;
            ArrayList<posterAnd_ID_data> xdata = new ArrayList<>() ;
            for(int i=0 ; i<15 ; i++)
            {
                JSONObject temp=arr.getJSONObject(i) ;
                int vote=temp.getInt("vote_count") ;
                String posterpath =temp.getString("poster_path") ;
                String id=temp.getString("id") ;
                data=new posterAnd_ID_data(posterpath , id , vote) ;
                xdata.add(data);

            }
            return  xdata ;

        } catch (JSONException e) {
            return  null ;
        }

    }

    @Override
    protected void onPreExecute() {
        mProgress = new ProgressDialog(mactivity);
        mProgress.setTitle("Loading");
        mProgress.setMessage("Wait while loading...");
        mProgress.show();
    }

    @Override
    protected void onPostExecute(ArrayList<posterAnd_ID_data> posterAnd_id_datas) {
        mProgress.dismiss();
        mactivity.processresult(posterAnd_id_datas) ;
    }
}
