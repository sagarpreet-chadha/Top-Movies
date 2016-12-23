package com.example.sagarpreetchadha.topmovies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sagarpreet chadha on 02-07-2016.
 */
public class CNOpenHelper extends SQLiteOpenHelper {

    public static final String BATCH_TABLE = "movies";
    public static final String BATCH_TABLE_ID = "_ID";
    public static final String BATCH_TABLE_NAME = "movie_name";
    //public static final String BATCH_INSTRUCTOR_NAME = "instructor_name";
    public static final String BATCH_VOTE_COUNT = "vote_count";

    public CNOpenHelper(Context context) {
        super(context, "CN",  null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +  BATCH_TABLE + " ( " + BATCH_TABLE_ID + " TEXT ," +
                BATCH_TABLE_NAME  + " TEXT, " + BATCH_VOTE_COUNT + " INTEGER);");
       // db.execSQL("create table students ( _ID INTEGER PRIMARY KEY ," +
      //          " batch_id INTEGER, name TEXT, college TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

