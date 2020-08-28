package com.example.sudoku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import static com.example.sudoku.Utils.ARG_COMPLEXITY;

public class SudokuPageFragment extends Fragment {
    private int mComplexity;

    public static SudokuPageFragment newInstance(int position) {
        SudokuPageFragment fragment = new SudokuPageFragment();
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
        View v = null;
        //inflater.inflate(R.layout.fragment_complexity_page, container, false);
        //ImageView mSudokuImageView = v.findViewById(R.id.sudokuImageView);


        return v;
    }
}