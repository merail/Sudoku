package com.example.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.sudoku.Utils.DIGITS_LIST;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RecyclerView recyclerView = findViewById(R.id.matrixRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(new SplashAdapter(DIGITS_LIST, this));

//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MenuActivity.class));
            }
        }, 2150);
    }
}