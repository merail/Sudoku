package com.example.sudoku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.sudoku.Utils.ARG_COMPLEXITY;

public class StatisticsPageFragment extends Fragment {
    private int mComplexity;

    public static StatisticsPageFragment newInstance(int position) {
        StatisticsPageFragment fragment = new StatisticsPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COMPLEXITY, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mComplexity = getArguments().getInt(ARG_COMPLEXITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statistics_page, container, false);

        TextView statistics = v.findViewById(R.id.complexity);
        switch (mComplexity) {
            case 0:
                statistics.setText(R.string.beginner_complexity);
                break;
            case 1:
                statistics.setText(R.string.easy_complexity);
                break;
            case 2:
                statistics.setText(R.string.normal_complexity);
                break;
            case 3:
                statistics.setText(R.string.hard_complexity);
                break;
            default:
                statistics.setText(R.string.expert_complexity);
                break;

        }

        RecyclerView statisticsRecycler = v.findViewById(R.id.statisticsRecyclerView);
        statisticsRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        statisticsRecycler.setLayoutManager(manager);

        StatisticsAdapter statisticsAdapter = new StatisticsAdapter(getContext());
        statisticsRecycler.setAdapter(statisticsAdapter);

        return v;
    }
}