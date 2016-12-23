package com.example.sagarpreetchadha.topmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sagarpreet chadha on 02-07-2016.
 */
public class reviews_customadapter extends ArrayAdapter<reviewsdata> {
    ArrayList<reviewsdata> data;
    Context context;

    public reviews_customadapter(Context context, ArrayList<reviewsdata> mdata) {
        super(context, 0);
        data = mdata;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView ;
        TextView author = null , review=null ;
        if(v==null)
        {
            v= LayoutInflater.from(context).inflate(R.layout.reviews_layout , null) ;

        }
        review=(TextView)v.findViewById(R.id.review) ;
        author=(TextView)v.findViewById(R.id.author) ;

        reviewsdata temp=data.get(position) ;

        author.setText(temp.getAuthor());
        review.setText(temp.getReview());


        return  v ;
    }
}
