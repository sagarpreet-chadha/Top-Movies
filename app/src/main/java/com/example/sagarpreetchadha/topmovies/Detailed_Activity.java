package com.example.sagarpreetchadha.topmovies;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Detailed_Activity extends AppCompatActivity {

    ArrayList<reviewsdata> arraylist= new ArrayList<reviewsdata>() ;
    ImageView movieid ;
    TextView moviename ;
    ImageView youtube ;
    ListView listview ;
    reviews_customadapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        movieid=(ImageView)findViewById(R.id.movieposter) ;
        moviename=(TextView)findViewById(R.id.moviename) ;
        String id=getIntent().getStringExtra("id") ;
        String url="https://api.themoviedb.org/3/movie/"+id+"?api_key=4a350fc6944dd3e7db84f4b96b79ce94" ;
        posterandname_Downloader task = new posterandname_Downloader(this) ;
        task.execute(url) ;

        youtube=(ImageView)findViewById(R.id.youtube) ;
        String uurl = "https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=4a350fc6944dd3e7db84f4b96b79ce94" ;
        //task
        youtube_downloader utask=new youtube_downloader(this) ;
        utask.execute(uurl) ;

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent() ;
                i.setAction(Intent.ACTION_VIEW) ;
                i.setData(Uri.parse("http://www.youtube.com")) ;
                if(i.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(i);
                }
            }
        });

        String u="https://api.themoviedb.org/3/movie/"+id+"/reviews?api_key=4a350fc6944dd3e7db84f4b96b79ce94" ;
        reviews_downloader t=new reviews_downloader(this) ;
        t.execute(u) ;

        //listview = (ListView)findViewById(R.id.xlistview) ;
        //adapter=new reviews_customadapter(this , arraylist) ;
       // listview.setAdapter(adapter);

    }

    public void processresult(Detailed_data_content datastorageclasses) {
        Picasso.with(this).load("http://image.tmdb.org/t/p/w342/"+datastorageclasses.getMovieid()).into(movieid);
        moviename.setText(datastorageclasses.getMoviename());
    }

    public void processresult2(String datastorageclasses) {
        Picasso.with(this).load("http://img.youtube.com/vi/"+datastorageclasses+"/hqdefault.jpg").into(youtube); ;
    }

    public void processresult3(ArrayList<reviewsdata> reviewsdatas) {
        if(reviewsdatas==null){
            return ;
        }
        arraylist=reviewsdatas ;
        if(arraylist.size()>0) {
            TextView author = (TextView) findViewById(R.id.author);
            TextView review = (TextView) findViewById(R.id.review);
            author.setText(arraylist.get(0).getAuthor());
            review.setText(arraylist.get(0).getReview());
            //adapter.notifyDataSetChanged();
        }
    }
}
