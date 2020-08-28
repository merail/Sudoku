package com.example.sudoku;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sudoku.database.Database;

import java.util.ArrayList;
import java.util.Objects;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static com.example.sudoku.Utils.ARG_COMPLEXITY;
import static com.example.sudoku.Utils.DIGITS_LIST;

public class GameActivity extends AppCompatActivity {
    ArrayList<Integer> mMatrixList;
    int mComplexity;
    private RecyclerView mNotesRecyclerView;
    private RecyclerView mMatrixRecyclerView;
    private RecyclerView mDigitsRecyclerView;

    public static Intent newIntent(Context packageContext, int complexity) {
        Intent intent = new Intent(packageContext, GameActivity.class);

        intent.putExtra(ARG_COMPLEXITY, complexity);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mNotesRecyclerView = findViewById(R.id.notesRecyclerView);
        mNotesRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager notesManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mNotesRecyclerView.setLayoutManager(notesManager);

        mMatrixRecyclerView = findViewById(R.id.matrixRecyclerView);
        mMatrixRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager matrixManager = new GridLayoutManager(this, 9, GridLayoutManager.VERTICAL, false);
        mMatrixRecyclerView.setLayoutManager(matrixManager);

        mDigitsRecyclerView = findViewById(R.id.digitsRecyclerView);
        mDigitsRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager digitsManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mDigitsRecyclerView.setLayoutManager(digitsManager);

        mComplexity = (int) Objects.requireNonNull(getIntent()).getSerializableExtra(ARG_COMPLEXITY);

        if (mComplexity != -1) {
            createGame();
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

        final KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                konfettiView.build()
                        .addColors(getResources().getColor(R.color.konfettiShade1), getResources().getColor(R.color.mainTheme), getResources().getColor(R.color.konfettiShade2))
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(3000L)
                        .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                        .addSizes(new Size(12, 5f))
                        .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                        .streamFor(300, 2000L);
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

    void createGame() {
        Sudoku sudoku = new Sudoku(mComplexity);
        sudoku.fillValues();
        mMatrixList = Sudoku.getSudoku();
        MatrixAdapter matrixAdapter = new MatrixAdapter(mMatrixList, this);
        mMatrixRecyclerView.setAdapter(matrixAdapter);

        NotesAdapter notesAdapter = new NotesAdapter(DIGITS_LIST, this, matrixAdapter);
        mNotesRecyclerView.setAdapter(notesAdapter);

        DigitsAdapter digitsAdapter = new DigitsAdapter(DIGITS_LIST, this, matrixAdapter);
        mDigitsRecyclerView.setAdapter(digitsAdapter);

        String readData = Database.get(getApplicationContext()).readData(mComplexity + "startedGames");
        int startedGames;
        if (readData.isEmpty())
            startedGames = 0;
        else
            startedGames = Integer.parseInt(readData);

        Database.get(this).deleteData(mComplexity + "startedGames");

        Database.get(this).writeData(mComplexity + "startedGames", String.valueOf((startedGames + 1)));
    }

    void loadGame() {
        String matrix = Database.get(getApplicationContext()).readData("lastGame");

        mComplexity = Integer.parseInt(matrix.substring(0, 1));
        matrix = matrix.substring(2);
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

        NotesAdapter notesAdapter = new NotesAdapter(DIGITS_LIST, this, matrixAdapter);
        mNotesRecyclerView.setAdapter(notesAdapter);

        DigitsAdapter digitsAdapter = new DigitsAdapter(DIGITS_LIST, this, matrixAdapter);
        mDigitsRecyclerView.setAdapter(digitsAdapter);
    }

    @Override
    public void onBackPressed() {
        Database.get(this).deleteData("lastGame");

        Database.get(getApplicationContext()).writeData("lastGame", mComplexity + ":" + mMatrixList.toString());

        startActivity(new Intent(GameActivity.this, MenuActivity.class));
    }
}
