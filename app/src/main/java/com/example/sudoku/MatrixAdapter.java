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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.sudoku.Utils.NAMES_OF_DIGITS_LIST;

public class MatrixAdapter extends RecyclerView.Adapter<MatrixAdapter.ViewHolder> implements OnSetValueListener {

    private ArrayList<Integer> list;
    private Boolean[] constantsList ;
    private Context context;

    private int mPosition;

    MatrixAdapter(ArrayList<Integer> list, Context context) {
        this.list = list;
        constantsList = new Boolean[81];
        for(int i = 0;i < list.size();i++)
        {
            constantsList[i] = list.get(i) < 1;
        }

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
        if(list.get(position) == 0)
        {
            if(position != mPosition)
            {
                holder.cell.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            }
            else
            {
                holder.cell.setBackgroundColor(ContextCompat.getColor(context, R.color.choosenCell));
            }
        }
        else {
            holder.cell.setImageResource(context.getResources().getIdentifier(NAMES_OF_DIGITS_LIST.get(list.get(position) - 1), "drawable", context.getPackageName()));
        }

        holder.cell.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mPosition = position;

                notifyDataSetChanged();

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
        if(constantsList[mPosition]) {
            list.set(mPosition, value);
            notifyDataSetChanged();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cell;

        ViewHolder(View itemView) {
            super(itemView);
            cell = itemView.findViewById(R.id.cell);
        }
    }
}