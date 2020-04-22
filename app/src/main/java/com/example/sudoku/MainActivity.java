package com.example.sudoku;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private int mComplexity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        final FragmentManager fragmentManager = getSupportFragmentManager();

        TopBarFragment topBarFragment = TopBarFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.topLayout, topBarFragment)
                .addToBackStack(null)
                .commit();

        final MatrixFragment matrixFragment = MatrixFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.centerLayout, matrixFragment)
                .addToBackStack(null)
                .commit();

        GameCreateInterface gameCreateInterface = new GameCreateInterface() {
            @Override
            public void createGame(int complexity) {
                mComplexity = complexity;
                matrixFragment.changeComplexity(complexity);
            }
        };
        final NewGameSetInterface newGameSetInterface = new NewGameSetInterface() {
            @Override
            public void setNewGame() {
                matrixFragment.changeComplexity(mComplexity);
            }
        };
        NewGameDialogCreateInterface newGameDialogCreateInterface = new NewGameDialogCreateInterface() {
            @Override
            public void createNewGameDialog() {
                NewGameDialogFragment newGameDialogFragment = new NewGameDialogFragment();
                newGameDialogFragment.show(fragmentManager, "newGameDialog");

                newGameDialogFragment.bindNewGameSetInterface(newGameSetInterface);
            }
        };
        BottomBarFragment bottomBarFragment = BottomBarFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.bottomLayout, bottomBarFragment)
                .addToBackStack(null)
                .commit();
        bottomBarFragment.bindComplexityChangeInterface(gameCreateInterface);
        bottomBarFragment.bindNewGameInterface(newGameDialogCreateInterface);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(Utils.currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(Utils.systemUiFlags);
        }
    }
}
