package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {
    private Context context;

    StatisticsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.statistics, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull StatisticsAdapter.ViewHolder holder, final int position) {
        switch(position)
        {
            case 0:
                holder.statistics.setText("Started games");
                break;
            case 1:
                holder.statistics.setText("Completed games");
                break;
            case 2:
                holder.statistics.setText("Win rate");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView statistics;

        ViewHolder(View itemView) {
            super(itemView);

            statistics = itemView.findViewById(R.id.statistics);
        }
    }
}
