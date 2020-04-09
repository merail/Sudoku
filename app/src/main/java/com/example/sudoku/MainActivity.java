package com.example.sudoku;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        final int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        RecyclerView recycler = findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 9, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);

        int K = 60;
        Sudoku sudoku = new Sudoku(K);
        sudoku.fillValues();
        sudoku.printSudoku();
        ArrayList<Integer> list = sudoku.getSudoku();
        Adapter adapter = new Adapter(list, this);
        recycler.setAdapter(adapter);
    }
}
