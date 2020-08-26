package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DigitsAdapter extends RecyclerView.Adapter<DigitsAdapter.ViewHolder> {

    private ArrayList<Integer> list;
    private Context context;

    DigitsAdapter(ArrayList<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.cell.setText(String.valueOf(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private EditText cell;

        ViewHolder(View itemView) {
            super(itemView);
            cell = itemView.findViewById(R.id.cell);
        }
    }
}