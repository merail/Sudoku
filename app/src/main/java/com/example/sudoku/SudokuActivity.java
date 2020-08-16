package com.example.sudoku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import static com.example.sudoku.Utils.NUMBER_OF_LEVELS;

public class SudokuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        ViewPager2 mViewPager = findViewById(R.id.sudokuPager);
        FragmentStateAdapter pagerAdapter = new SudokuPagerAdapter(this);
        mViewPager.setAdapter(pagerAdapter);
    }

    private static class SudokuPagerAdapter extends FragmentStateAdapter {
        public SudokuPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return SudokuPageFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return NUMBER_OF_LEVELS;
        }
    }
}