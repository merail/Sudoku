package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComplexityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexity);

        Button beginnerButton = findViewById(R.id.beginnerButton);
        beginnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), 0));
            }
        });

        Button easyButton = findViewById(R.id.easyButton);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), 1));
            }
        });

        Button normalButton = findViewById(R.id.normalButton);
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), 2));
            }
        });

        Button hardButton = findViewById(R.id.hardButton);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), 3));
            }
        });

        Button expertButton = findViewById(R.id.expertButton);
        expertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), 4));
            }
        });
    }
}