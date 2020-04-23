package com.example.sudoku.database;

import android.provider.BaseColumns;

public final class DatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseContract() {}

    /* Inner class that defines the table contents */
    public static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "saved_sudokus";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}