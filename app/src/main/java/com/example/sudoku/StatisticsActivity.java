package com.example.sudoku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class StatisticsActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ViewPager2 mViewPager = findViewById(R.id.statisticsPager);
        FragmentStateAdapter pagerAdapter = new StatisticsPagerAdapter(this);
        mViewPager.setAdapter(pagerAdapter);
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private static class StatisticsPagerAdapter extends FragmentStateAdapter {
        public StatisticsPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return StatisticsPageFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}