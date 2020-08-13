package com.example.sudoku;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.ArrayList;

import static com.example.sudoku.Utils.FIVE;
import static com.example.sudoku.Utils.FOUR;
import static com.example.sudoku.Utils.ONE;
import static com.example.sudoku.Utils.THREE;
import static com.example.sudoku.Utils.TWO;

public class GameActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private LottieAnimationView mTips;

    private int mComplexity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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

        mRecycler = findViewById(R.id.recyclerView);
        mRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 9, GridLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);

        changeComplexity(0);

        final FragmentManager fragmentManager = getSupportFragmentManager();

//        TopBarFragment topBarFragment = TopBarFragment.newInstance();
//        fragmentManager.beginTransaction()
//                .add(R.id.topLayout, topBarFragment)
//                .addToBackStack(null)
//                .commit();
//
//        final MatrixFragment matrixFragment = new MatrixFragment();
//        fragmentManager.beginTransaction()
//                .add(R.id.centerLayout, matrixFragment)
//                .addToBackStack(null)
//                .commit();
//
//        GameCreateInterface gameCreateInterface = new GameCreateInterface() {
//            @Override
//            public void createGame(int complexity) {
//                mComplexity = complexity;
//                matrixFragment.changeComplexity(complexity);
//            }
//        };
//        final NewGameSetInterface newGameSetInterface = new NewGameSetInterface() {
//            @Override
//            public void setNewGame() {
//                matrixFragment.changeComplexity(mComplexity);
//            }
//        };
//        NewGameDialogCreateInterface newGameDialogCreateInterface = new NewGameDialogCreateInterface() {
//            @Override
//            public void createNewGameDialog() {
//                NewGameDialogFragment newGameDialogFragment = new NewGameDialogFragment();
//                newGameDialogFragment.show(fragmentManager, "newGameDialog");
//
//                newGameDialogFragment.bindNewGameSetInterface(newGameSetInterface);
//            }
//        };
//        BottomBarFragment bottomBarFragment = BottomBarFragment.newInstance();
//        fragmentManager.beginTransaction()
//                .add(R.id.bottomLayout, bottomBarFragment)
//                .addToBackStack(null)
//                .commit();
//        bottomBarFragment.bindComplexityChangeInterface(gameCreateInterface);
//        bottomBarFragment.bindNewGameInterface(newGameDialogCreateInterface);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Utils.currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(Utils.systemUiFlags);
        }
    }

    void changeComplexity(int complexity) {
        Sudoku sudoku = new Sudoku(complexity);
        sudoku.fillValues();
        ArrayList<Integer> list = Sudoku.getSudoku();
        MatrixAdapter matrixAdapter = new MatrixAdapter(list, this);
        mRecycler.setAdapter(matrixAdapter);
    }
}
