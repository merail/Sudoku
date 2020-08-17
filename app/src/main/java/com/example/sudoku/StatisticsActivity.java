package com.example.sudoku;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import static com.example.sudoku.Utils.NUMBER_OF_LEVELS;

public class StatisticsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ViewPager2 mViewPager = findViewById(R.id.statisticsPager);
        FragmentStateAdapter pagerAdapter = new StatisticsPagerAdapter(this);
        mViewPager.setAdapter(pagerAdapter);
    }

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
            return NUMBER_OF_LEVELS;
        }
    }
}