package com.example.sudoku;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatrixFragment extends Fragment {

    public static Bitmap bitScroll;
    private RecyclerView mRecycler;
    private ScrollView scrollView;

    static MatrixFragment newInstance(String list) {
        Bundle args = new Bundle();
        args.putSerializable("list", list);

        MatrixFragment fragment = new MatrixFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_matrix, container, false);

        //String stringList;
        //stringList = (String) getArguments().getSerializable("list");
        //if(stringList != null)
        //    Log.d("list", Objects.requireNonNull(stringList));
        //for(String s : stringList) intList.add(Integer.valueOf(s));

        mRecycler = v.findViewById(R.id.recyclerView);
        mRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 9, GridLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);

        return v;
    }

    void changeComplexity(int complexity) {
        Sudoku sudoku = new Sudoku(complexity);
        sudoku.fillValues();
        ArrayList<Integer> list = Sudoku.getSudoku();
        Adapter adapter = new Adapter(list, getContext());
        mRecycler.setAdapter(adapter);
    }

    void setList(ArrayList<Integer> list, Context context) {
        if (getContext() != null) {
            Log.d("listtttt", String.valueOf(list));
            Adapter adapter = new Adapter(list, context);
            mRecycler.setAdapter(adapter);
        }
    }
}
