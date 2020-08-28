package com.example.sudoku.database;

import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract() {
    }

    public static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "saved_sudoku";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}