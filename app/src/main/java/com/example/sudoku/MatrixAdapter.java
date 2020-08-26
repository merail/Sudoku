package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatrixAdapter extends RecyclerView.Adapter<MatrixAdapter.ViewHolder> implements OnSetValueListener {

    private ArrayList<Integer> list;
    private Context context;

    private int mPosition;

    MatrixAdapter(ArrayList<Integer> list, Context context) {
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

        if (list.get(position) != 0) {
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
        }

        holder.cell.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (list.get(position) == 0) {
                    v.setFocusable(true);
                    v.setFocusableInTouchMode(true);
                }

                holder.cell.setBackgroundResource(R.drawable.choosen_cell);

                mPosition = position;

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void setValue(int value) {
        list.set(mPosition, value);
        this.notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cell;

        ViewHolder(View itemView) {
            super(itemView);
            cell = itemView.findViewById(R.id.cell);
        }
    }
}