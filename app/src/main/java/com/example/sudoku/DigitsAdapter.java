package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View view = LayoutInflater.from(context).inflate(R.layout.digit, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        switch (list.get(position))
        {
            case 1:
                holder.digit.setImageResource(R.drawable.one);
                break;
            case 2:
                holder.digit.setImageResource(R.drawable.two);
                break;
            case 3:
                holder.digit.setImageResource(R.drawable.three);
                break;
            case 4:
                holder.digit.setImageResource(R.drawable.four);
                break;
            case 5:
                holder.digit.setImageResource(R.drawable.five);
                break;
            case 6:
                holder.digit.setImageResource(R.drawable.six);
                break;
            case 7:
                holder.digit.setImageResource(R.drawable.seven);
                break;
            case 8:
                holder.digit.setImageResource(R.drawable.eight);
                break;
            default:
                holder.digit.setImageResource(R.drawable.nine);
                break;
        }

        holder.digit.setOnClickListener(new View.OnClickListener() {
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
        private ImageView digit;

        ViewHolder(View itemView) {
            super(itemView);
            digit = itemView.findViewById(R.id.digit);
        }
    }
}