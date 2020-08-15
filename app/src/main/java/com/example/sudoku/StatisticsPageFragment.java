package com.example.sudoku;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatisticsPageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private int mPosition;

    private RecyclerView mRecycler;

    private TextView mTextView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Parameter 1.
     * @return A new instance of fragment StatisticsPageFragment.
     */
    public static StatisticsPageFragment newInstance(int position) {
        StatisticsPageFragment fragment = new StatisticsPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statistics_page, container, false);

        mTextView = v.findViewById(R.id.level);
        Log.d("aaaaaaaaa", String.valueOf(mPosition));
        switch (mPosition)
        {
            case 0:
                mTextView.setText("Beginner");
                break;
            case 1:
                mTextView.setText("Easy");
                break;
            case 2:
                mTextView.setText("Normal");
                break;
            case 3:
                mTextView.setText("Hard");
                break;
            default:
                mTextView.setText("Expert");
                break;

        }

        mRecycler = v.findViewById(R.id.statistics_recycler_view);
        mRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(manager);

        StatisticsAdapter statisticsAdapter = new StatisticsAdapter(getContext());
        mRecycler.setAdapter(statisticsAdapter);

        return v;
    }
}