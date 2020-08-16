package com.example.sudoku;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.sudoku.database.Database;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.sudoku.Utils.ARG_COMPLEXITY;
import static com.example.sudoku.database.DatabaseContract.Entry.COLUMN_NAME_SUBTITLE;
import static com.example.sudoku.database.DatabaseContract.Entry.COLUMN_NAME_TITLE;
import static com.example.sudoku.database.DatabaseContract.Entry.TABLE_NAME;

public class GameActivity extends AppCompatActivity {
    ArrayList<Integer> mMatrixList;
    private RecyclerView mMatrixRecyclerView;
    private LottieAnimationView mTips;
    private LottieAnimationView mCancel;

    public static Intent newIntent(Context packageContext, int complexity) {
        Intent intent = new Intent(packageContext, GameActivity.class);

        intent.putExtra(ARG_COMPLEXITY, complexity);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mMatrixRecyclerView = findViewById(R.id.matrixRecyclerView);
        mMatrixRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 9, GridLayoutManager.VERTICAL, false);
        mMatrixRecyclerView.setLayoutManager(manager);

        int complexity = (int) Objects.requireNonNull(getIntent()).getSerializableExtra(ARG_COMPLEXITY);

        if (complexity != -1) {
            createGame(complexity);
        } else {
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

        mCancel = findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCancel.setRepeatMode(LottieDrawable.RESTART);
                mCancel.setRepeatCount(1);
                mCancel.playAnimation();
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
        mMatrixList = Sudoku.getSudoku();
        MatrixAdapter matrixAdapter = new MatrixAdapter(mMatrixList, this);
        mMatrixRecyclerView.setAdapter(matrixAdapter);
    }

    void loadGame() {
        String[] projection = {
                BaseColumns._ID,
                COLUMN_NAME_TITLE,
                COLUMN_NAME_SUBTITLE
        };

        String sortOrder = COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = Database.get(this).getReadDatabase().query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        String matrix = "";
        while (cursor.moveToNext()) {
            matrix = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME_SUBTITLE));
        }
        cursor.close();
        matrix = matrix.replace("[", "");
        matrix = matrix.replace("]", "");
        matrix = matrix.replace(",", "");
        matrix = matrix.replace(" ", "");

        mMatrixList = new ArrayList<>();
        for (int i = 0; i < matrix.length(); i++) {
            mMatrixList.add(Integer.parseInt(String.valueOf(matrix.charAt(i))));
        }
        MatrixAdapter matrixAdapter = new MatrixAdapter(mMatrixList, this);
        mMatrixRecyclerView.setAdapter(matrixAdapter);
    }

    @Override
    public void onBackPressed() {
        Database.get(this).getReadDatabase().delete(TABLE_NAME,
                COLUMN_NAME_TITLE + "='lastGame'", null);

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_TITLE, "lastGame");
        values.put(COLUMN_NAME_SUBTITLE, mMatrixList.toString());

        Database.get(getApplicationContext()).getWriteDatabase().insert(TABLE_NAME, null, values);

        startActivity(new Intent(GameActivity.this, MenuActivity.class));
    }
}
