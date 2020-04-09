package com.example.sudoku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Integer> list;
    private Context context;

    Adapter(ArrayList<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position % 27 == 0 || (position - 3) % 27 == 0 || (position - 6) % 27 == 0)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_top_left));
        else if(((position - 19) % 27 == 0 || (position - 22) % 27 == 0 || (position - 25) % 27 == 0) && position < 71)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_bottom_center_center));
        else if(((position - 20) % 27 == 0 || (position - 23) % 27 == 0) && position < 71)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_bottom_center_center_right));
        else if((position - 8) % 27 == 0)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_top_right));
        else if((position - 9) % 27 == 0 || (position - 12) % 27 == 0 || (position - 15) % 27 == 0)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_left));
        else if((position - 17) % 27 == 0)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_right));
        else if(((position - 18) % 27 == 0 || (position - 21) % 27 == 0 || (position - 24) % 27 == 0) && position < 71)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_left_near));
        else if((position - 26) % 27 == 0 && position < 72)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_right_near));
        else if(((position - 18) % 27 == 0 || (position - 21) % 27 == 0 || (position - 24) % 27 == 0) && position > 71)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_bottom_left));
        else if(((position - 1) % 27 == 0 || (position - 4) % 27 == 0 || (position - 7) % 27 == 0) && position < 71)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_top_center));
        else if(((position - 2) % 27 == 0 || (position - 5) % 27 == 0 || (position - 8) % 27 == 0) && position < 71)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_top_center_right));
        else if(((position - 19) % 27 == 0 || (position - 22) % 27 == 0 || (position - 25) % 27 == 0) && position > 71)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_bottom_center));
        else if(((position - 20) % 27 == 0 || (position - 23) % 27 == 0 || (position - 26) % 27 == 0) && position > 71 && position != 80)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_bottom_center_right));
        else if(((position - 10) % 27 == 0 || (position - 13) % 27 == 0 || (position - 16) % 27 == 0))
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_center));
        else if((position - 11) % 27 == 0 || (position - 14) % 27 == 0)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_center_right));
        else if((position - 26) % 27 == 0 && position > 71)
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.border_bottom_right));

        if(list.get(position) != 0)
            holder.textView.setText(String.valueOf(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}