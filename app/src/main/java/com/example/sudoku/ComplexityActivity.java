package com.example.sudoku;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.sudoku.Utils.LEVEL_FIVE;
import static com.example.sudoku.Utils.LEVEL_FOUR;
import static com.example.sudoku.Utils.LEVEL_ONE;
import static com.example.sudoku.Utils.LEVEL_THREE;
import static com.example.sudoku.Utils.LEVEL_TWO;

public class ComplexityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexity);

        Button beginnerButton = findViewById(R.id.beginnerButton);
        beginnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), LEVEL_ONE));
            }
        });

        Button easyButton = findViewById(R.id.easyButton);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), LEVEL_TWO));
            }
        });

        Button normalButton = findViewById(R.id.normalButton);
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), LEVEL_THREE));
            }
        });

        Button hardButton = findViewById(R.id.hardButton);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), LEVEL_FOUR));
            }
        });

        Button expertButton = findViewById(R.id.expertButton);
        expertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.newIntent(getApplicationContext(), LEVEL_FIVE));
            }
        });
    }
}