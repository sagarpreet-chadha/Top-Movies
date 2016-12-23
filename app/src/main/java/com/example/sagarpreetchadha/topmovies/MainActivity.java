package com.example.sagarpreetchadha.topmovies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    customgridadapter adapter ;
    GridView gridview ;
    ArrayList<posterAnd_ID_data> data =new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridview=(GridView)findViewById(R.id.gridView) ;

//        String  url="http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=4a350fc6944dd3e7db84f4b96b79ce94" ;
//        posterandIDDowanloadTask task=new posterandIDDowanloadTask(this);
//        task.execute(url) ;


        CNOpenHelper helper = new CNOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
//        String[] columns = {CNOpenHelper.BATCH_TABLE_ID, CNOpenHelper.BATCH_TABLE_NAME,
//                CNOpenHelper.BATCH_INSTRUCTOR_NAME, CNOpenHelper.BATCH_STRENGTH};

        Cursor c = db.query(CNOpenHelper.BATCH_TABLE, null , null, null, null, null, null);

        while (c.moveToNext()) {
            String moviename = c.getString(c.getColumnIndex(CNOpenHelper.BATCH_TABLE_NAME));
          //  String votecount = c.getString(c.getColumnIndex(CNOpenHelper.BATCH_VOTE_COUNT));
            String id = String.valueOf(c.getInt(c.getColumnIndex(CNOpenHelper.BATCH_TABLE_ID)));
            int vote = c.getInt(c.getColumnIndex(CNOpenHelper.BATCH_VOTE_COUNT));

            //Batch b = new Batch(name,instuctorName,strength,id);
            //data.add(b);
            posterAnd_ID_data x=new posterAnd_ID_data(moviename , id , vote) ;
            data.add(x);
        }



      //  data=new ArrayList<posterAnd_ID_data>() ;
            adapter = new customgridadapter(this, data);
            gridview.setAdapter(adapter);

        APIInterface apiservice = new Retrofit.Builder().baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build().create(APIInterface.class) ;

        Call<MovieResponse> call=apiservice.getMovies() ;
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful())
                {
                    ArrayList<posterAnd_ID_data> x=response.body().getResults() ;
                    if(x==null)
                    {
                        return ;
                    }
                    data.clear();
                    for(int i=0 ; i<x.size() ; i++)
                    {
                        data.add(x.get(i));
                    }
                    CNOpenHelper helper = new CNOpenHelper(MainActivity.this);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    db.delete(CNOpenHelper.BATCH_TABLE, null, null);
                    for (int i=0 ; i<data.size() ; i++) {
                        //data.add(b);
                        ContentValues cv = new ContentValues();
                        cv.put(CNOpenHelper.BATCH_TABLE_ID, data.get(i).getId());
                        cv.put(CNOpenHelper.BATCH_TABLE_NAME, data.get(i).getPosterpath());
                        //cv.put(CNOpenHelper.BATCH_INSTRUCTOR_NAME, b.getInstructorName());
                        cv.put(CNOpenHelper.BATCH_VOTE_COUNT, data.get(i).getVote());

                        db.insert(CNOpenHelper.BATCH_TABLE, null, cv);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i= new Intent() ;
                i.putExtra("id" , data.get(position).getId()) ;
                i.setClass(MainActivity.this , Detailed_Activity.class) ;
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sortby) {
            return true;

        }
        else if(id==R.id.votecount)
        {
            CNOpenHelper helper = new CNOpenHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor c = db.query(CNOpenHelper.BATCH_TABLE, null , null , null , null , null , CNOpenHelper.BATCH_VOTE_COUNT );
            data.clear();
            while (c.moveToNext()) {
                String moviename = c.getString(c.getColumnIndex(CNOpenHelper.BATCH_TABLE_NAME));
                //  String votecount = c.getString(c.getColumnIndex(CNOpenHelper.BATCH_VOTE_COUNT));
                String xid = String.valueOf(c.getInt(c.getColumnIndex(CNOpenHelper.BATCH_TABLE_ID)));
                int vote = c.getInt(c.getColumnIndex(CNOpenHelper.BATCH_VOTE_COUNT));

                //Batch b = new Batch(name,instuctorName,strength,id);
                //data.add(b);
                posterAnd_ID_data x=new posterAnd_ID_data(moviename , xid , vote) ;
                data.add(x);
            }
            adapter.notifyDataSetChanged();
        }


        return super.onOptionsItemSelected(item);
    }

    public void processresult(ArrayList<posterAnd_ID_data> data) {

        if(data==null)
        {
            return;
        }
        this.data.clear();
        for(int i=0 ; i<data.size() ; i++)
        {
            this.data.add(data.get(i));
        }

        CNOpenHelper helper = new CNOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(CNOpenHelper.BATCH_TABLE, null, null);
        for (int i=0 ; i<data.size() ; i++) {
            //data.add(b);
            ContentValues cv = new ContentValues();
            cv.put(CNOpenHelper.BATCH_TABLE_ID, data.get(i).getId());
            cv.put(CNOpenHelper.BATCH_TABLE_NAME, data.get(i).getPosterpath());
            //cv.put(CNOpenHelper.BATCH_INSTRUCTOR_NAME, b.getInstructorName());
            cv.put(CNOpenHelper.BATCH_VOTE_COUNT, data.get(i).getVote());

            db.insert(CNOpenHelper.BATCH_TABLE, null, cv);
       }

        adapter.notifyDataSetChanged();
    }
}
