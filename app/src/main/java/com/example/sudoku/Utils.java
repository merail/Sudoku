package com.example.sudoku;

import android.provider.BaseColumns;
import android.view.View;

import com.example.sudoku.database.DatabaseContract.Entry;

import static com.example.sudoku.database.DatabaseContract.Entry.COLUMN_NAME_SUBTITLE;
import static com.example.sudoku.database.DatabaseContract.Entry.COLUMN_NAME_TITLE;

public class Utils {
    static final int currentApiVersion = android.os.Build.VERSION.SDK_INT;
    static final int systemUiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    public static final int LEVEL_ONE = 0;
    public static final int LEVEL_TWO = 1;
    public static final int LEVEL_THREE = 2;
    public static final int LEVEL_FOUR = 3;
    public static final int LEVEL_FIVE = 4;

    public static final int NUMBER_OF_LEVELS = 5;

    public static final String ARG_COMPLEXITY = "Complexity";
}
