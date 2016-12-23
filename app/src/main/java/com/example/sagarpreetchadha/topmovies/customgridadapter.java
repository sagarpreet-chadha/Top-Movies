package com.example.sagarpreetchadha.topmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sagarpreet chadha on 28-06-2016.
 */
public class customgridadapter extends BaseAdapter
{
     Context mContext;
    ArrayList<posterAnd_ID_data> data ;
    public customgridadapter(Context c , ArrayList<posterAnd_ID_data> data ) {
        mContext = c;
        this.data=data ;
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return 0 ;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView;
            View v ;

            // if it's not recycled, initialize some attributes
          //  imageView = new ImageView(mContext);

            v= LayoutInflater.from(mContext).inflate(R.layout.content_pic_grid, null) ;
            imageView=(ImageView)v.findViewById(R.id.gridimage);
/*
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);  */
           String path = data.get(position).getPosterpath() ;
            //imagedownloader task=new imagedownloader(imageView , path) ;
           // task.execute("http://image.tmdb.org/t/p/w342/"+path);
        TextView t=(TextView)v.findViewById(R.id.textView) ;
        t.setText("Vote Count = " + data.get(position).getVote()+"");

            Picasso.with(mContext).load("http://image.tmdb.org/t/p/w342/"+path).into(imageView) ;

          //imageView.setImageResource(mThumbIds[position]);

        return v ;
    }

}
