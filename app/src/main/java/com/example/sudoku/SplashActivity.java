package com.example.sudoku;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RecyclerView recyclerView = findViewById(R.id.matrixRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        ArrayList<Integer> matrixList = new ArrayList<>();
        matrixList.add(1);
        matrixList.add(2);
        matrixList.add(3);
        matrixList.add(4);
        matrixList.add(5);
        matrixList.add(6);
        matrixList.add(7);
        matrixList.add(8);
        matrixList.add(9);
        Collections.shuffle(matrixList);
        recyclerView.setAdapter(new SplashAdapter(matrixList, this));

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