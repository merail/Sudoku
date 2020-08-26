package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DigitsAdapter extends RecyclerView.Adapter<DigitsAdapter.ViewHolder> {

    private ArrayList<Integer> list;
    private Context context;

    private OnSetValueListener onSetValueListener;

    DigitsAdapter(ArrayList<Integer> list, Context context, OnSetValueListener onSetValueListener) {
        this.list = list;
        this.context = context;
        this.onSetValueListener = onSetValueListener;
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
        switch (list.get(position))
        {
            case 1:
                holder.cell.setImageResource(R.drawable.one);
                break;
            case 2:
                holder.cell.setImageResource(R.drawable.two);
                break;
            case 3:
                holder.cell.setImageResource(R.drawable.three);
                break;
            case 4:
                holder.cell.setImageResource(R.drawable.four);
                break;
            case 5:
                holder.cell.setImageResource(R.drawable.five);
                break;
            case 6:
                holder.cell.setImageResource(R.drawable.six);
                break;
            case 7:
                holder.cell.setImageResource(R.drawable.seven);
                break;
            case 8:
                holder.cell.setImageResource(R.drawable.eight);
                break;
            default:
                holder.cell.setImageResource(R.drawable.nine);
                break;
        }

        holder.cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSetValueListener.setValue(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cell;

        ViewHolder(View itemView) {
            super(itemView);
            cell = itemView.findViewById(R.id.cell);
        }
    }
}