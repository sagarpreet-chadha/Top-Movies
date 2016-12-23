package com.example.sagarpreetchadha.topmovies;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by sagarpreet chadha on 28-06-2016.
 */
public class imagedownloader extends AsyncTask<String,Void,Bitmap> {

    ImageView imageView;
    String path ;

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */


    public imagedownloader(ImageView imageView , String path) {
        this.imageView=imageView ;
        this.path = path ;
    }

    protected Bitmap doInBackground(String...urls){
        String urlOfImage = urls[0];
        Bitmap logo = null;
        String completepath="http://image.tmdb.org/t/p/w342/"+path ;
        try{

            InputStream is = new URL(completepath).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
            logo = BitmapFactory.decodeStream(is);
        }catch(Exception e){ // Catch the download exception
            e.printStackTrace();
        }
        return logo;
    }

    /*
        onPostExecute(Result result)
            Runs on the UI thread after doInBackground(Params...).
     */
    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }
}


