package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;

import com.example.sudoku.database.Database;
import com.example.sudoku.database.DatabaseContract;

import java.util.ArrayList;
import java.util.List;

public class SavedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        if (Utils.currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(Utils.systemUiFlags);
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(Utils.systemUiFlags);
                    }
                }
            });
        }

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DatabaseContract.Entry.COLUMN_NAME_TITLE,
                DatabaseContract.Entry.COLUMN_NAME_SUBTITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DatabaseContract.Entry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DatabaseContract.Entry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = Database.get(this).getReadDatabase().query(
                DatabaseContract.Entry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseContract.Entry.COLUMN_NAME_TITLE));
            itemIds.add(itemId);
        }
        cursor.close();
        for(Object i: itemIds)
            Log.d("itemIds", (String) i);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(Utils.currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(Utils.systemUiFlags);
        }
    }
}
