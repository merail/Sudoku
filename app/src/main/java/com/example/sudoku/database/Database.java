package com.example.sudoku.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Database {
    private static Database sDatabase;
    private static SQLiteDatabase readDatabase;
    private static SQLiteDatabase writeDatabase;
    private DatabaseHelper helper;

    private Database(Context context)
    {
        helper = new DatabaseHelper(context);
        readDatabase = helper.getWritableDatabase();
        writeDatabase = helper.getWritableDatabase();
    }

    public static Database get(Context context) {
        if (sDatabase == null)
            sDatabase = new Database(context);

        return sDatabase;
    }

    public SQLiteDatabase getWriteDatabase()
    {
        return writeDatabase;
    }

    public SQLiteDatabase getReadDatabase()
    {
        return readDatabase;
    }
}
