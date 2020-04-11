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

    static MatrixFragment newInstance()
    {
        MatrixFragment fragment = new MatrixFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_matrix, container, false);

        RecyclerView recycler = v.findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 9, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);

        int K = 10;
        Sudoku sudoku = new Sudoku(K);
        sudoku.fillValues();
        sudoku.printSudoku();
        ArrayList<Integer> list = sudoku.getSudoku();
        Adapter adapter = new Adapter(list, getContext());
        recycler.setAdapter(adapter);

        return v;
    }
}
