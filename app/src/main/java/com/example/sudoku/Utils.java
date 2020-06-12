package com.example.sudoku;

import android.view.View;

import com.example.sudoku.database.DatabaseContract.Entry;

public class Utils {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                    Entry._ID + " INTEGER PRIMARY KEY," +
                    Entry.COLUMN_NAME_TITLE + " TEXT," +
                    Entry.COLUMN_NAME_SUBTITLE + " TEXT)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;
    static final int currentApiVersion = android.os.Build.VERSION.SDK_INT;
    static final int systemUiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
}
