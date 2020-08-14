package com.example.sudoku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class SudokuPageFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int mPosition;

    public static SudokuPageFragment newInstance(int position) {
        SudokuPageFragment fragment = new SudokuPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_complexity_page, container, false);
        ImageView mSudokuImageView = v.findViewById(R.id.sudokuImageView);

        if (mPosition == 0)
            mSudokuImageView.setImageResource(R.drawable.complexity1);
        else if (mPosition == 1)
            mSudokuImageView.setImageResource(R.drawable.complexity2);
        else if (mPosition == 2)
            mSudokuImageView.setImageResource(R.drawable.complexity3);
        else if (mPosition == 3)
            mSudokuImageView.setImageResource(R.drawable.complexity4);
        else
            mSudokuImageView.setImageResource(R.drawable.complexity5);

        return v;
    }
}