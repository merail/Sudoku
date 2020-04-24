package com.example.sudoku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatrixFragment extends Fragment {

    private RecyclerView mRecycler;

    static MatrixFragment newInstance()
    {
        MatrixFragment fragment = new MatrixFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_matrix, container, false);

        mRecycler = v.findViewById(R.id.recyclerView);
        mRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 9, GridLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);

        return v;
    }

    void changeComplexity(int complexity)
    {
        Sudoku sudoku = new Sudoku(complexity);
        sudoku.fillValues();
        ArrayList<Integer> list = Sudoku.getSudoku();
        Adapter adapter = new Adapter(list, getContext());
        mRecycler.setAdapter(adapter);
    }
}
