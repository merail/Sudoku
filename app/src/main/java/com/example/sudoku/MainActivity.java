package com.example.sudoku;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.sudoku.database.Database;
import com.example.sudoku.database.DatabaseContract;
import com.example.sudoku.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int mComplexity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String[] projection = {
//                BaseColumns._ID,
//                DatabaseContract.Entry.COLUMN_NAME_TITLE,
//                DatabaseContract.Entry.COLUMN_NAME_SUBTITLE
//        };
//
//        // Filter results WHERE "title" = 'My Title'
//        String selection = DatabaseContract.Entry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = { "title" };
//
//        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                DatabaseContract.Entry.COLUMN_NAME_SUBTITLE + " DESC";
//
//        Cursor cursor = Database.get(this).getReadDatabase().query(
//                DatabaseContract.Entry.TABLE_NAME,   // The table to query
//                projection,             // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                sortOrder               // The sort order
//        );
//
//        List itemIds = new ArrayList<>();
//        while(cursor.moveToNext()) {
//            String itemId = cursor.getString(
//                    cursor.getColumnIndexOrThrow(DatabaseContract.Entry.COLUMN_NAME_SUBTITLE));
//            itemIds.add(itemId);
//        }
//        cursor.close();
//        Log.d("itemIds", String.valueOf(itemIds.get(0)));

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

        final FragmentManager fragmentManager = getSupportFragmentManager();

        TopBarFragment topBarFragment = TopBarFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.topLayout, topBarFragment)
                .addToBackStack(null)
                .commit();

        final MatrixFragment matrixFragment = MatrixFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.centerLayout, matrixFragment)
                .addToBackStack(null)
                .commit();

        GameCreateInterface gameCreateInterface = new GameCreateInterface() {
            @Override
            public void createGame(int complexity) {
                mComplexity = complexity;
                matrixFragment.changeComplexity(complexity);
            }
        };
        final NewGameSetInterface newGameSetInterface = new NewGameSetInterface() {
            @Override
            public void setNewGame() {
                matrixFragment.changeComplexity(mComplexity);
            }
        };
        NewGameDialogCreateInterface newGameDialogCreateInterface = new NewGameDialogCreateInterface() {
            @Override
            public void createNewGameDialog() {
                NewGameDialogFragment newGameDialogFragment = new NewGameDialogFragment();
                newGameDialogFragment.show(fragmentManager, "newGameDialog");

                newGameDialogFragment.bindNewGameSetInterface(newGameSetInterface);
            }
        };
        BottomBarFragment bottomBarFragment = BottomBarFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.bottomLayout, bottomBarFragment)
                .addToBackStack(null)
                .commit();
        bottomBarFragment.bindComplexityChangeInterface(gameCreateInterface);
        bottomBarFragment.bindNewGameInterface(newGameDialogCreateInterface);
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
