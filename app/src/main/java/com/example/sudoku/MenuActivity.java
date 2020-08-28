package com.example.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ComplexityActivity.class));
            }
        });

        Button lastGameButton = findViewById(R.id.lastGameButton);
        lastGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), -1));
            }
        });

        Button statisticsButton = findViewById(R.id.statisticsButton);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, StatisticsActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finishAffinity();
    }
}