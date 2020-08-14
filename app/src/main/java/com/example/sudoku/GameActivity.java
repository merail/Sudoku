package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.sudoku.database.Database;
import com.example.sudoku.database.DatabaseContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.example.sudoku.database.DatabaseContract.Entry.COLUMN_NAME_SUBTITLE;
import static com.example.sudoku.database.DatabaseContract.Entry.COLUMN_NAME_TITLE;
import static com.example.sudoku.database.DatabaseContract.Entry.TABLE_NAME;

public class GameActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private LottieAnimationView mTips;

    ArrayList<Integer> mList;

    public static Intent newIntent(Context packageContext, int complexity) {
        Intent intent = new Intent(packageContext, GameActivity.class);

        intent.putExtra("aaa", complexity);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mRecycler = findViewById(R.id.recyclerView);
        mRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 9, GridLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);

        int mComplexity = (int) Objects.requireNonNull(getIntent()).getSerializableExtra("aaa");

        if(mComplexity != -1)
        {
            createGame(mComplexity);
        }
        else
        {
            loadGame();
        }

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

        mTips = findViewById(R.id.tips);
        mTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTips.setRepeatMode(LottieDrawable.RESTART);
                mTips.setRepeatCount(1);
                mTips.playAnimation();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Utils.currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(Utils.systemUiFlags);
        }
    }

    void createGame(int complexity) {
        Sudoku sudoku = new Sudoku(complexity);
        sudoku.fillValues();
        mList = Sudoku.getSudoku();
        MatrixAdapter matrixAdapter = new MatrixAdapter(mList, this);
        mRecycler.setAdapter(matrixAdapter);
    }

    void loadGame()
    {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                COLUMN_NAME_TITLE,
                COLUMN_NAME_SUBTITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = {"title"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = Database.get(this).getReadDatabase().query(
                TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        String itemId = "";
        while (cursor.moveToNext()) {
            itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME_SUBTITLE));
        }
        cursor.close();
        itemId = itemId.replace("[", "");
        itemId = itemId.replace("]", "");
        itemId = itemId.replace(",", "");
        itemId = itemId.replace(" ", "");

        mList = new ArrayList<>();
        for(int i = 0;i < itemId.length();i++)
        {
            mList.add(Integer.parseInt(String.valueOf(itemId.charAt(i))));
        }
        MatrixAdapter matrixAdapter = new MatrixAdapter(mList, this);
        mRecycler.setAdapter(matrixAdapter);
    }

    @Override
    public void onBackPressed() {
        Database.get(this).getReadDatabase().delete(TABLE_NAME,
                COLUMN_NAME_TITLE + "='lastGame'", null);

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_TITLE, "lastGame");
        values.put(COLUMN_NAME_SUBTITLE, mList.toString());

        Database.get(getApplicationContext()).getWriteDatabase().insert(TABLE_NAME, null, values);

        startActivity(new Intent(GameActivity.this, MenuActivity.class));
    }
}
