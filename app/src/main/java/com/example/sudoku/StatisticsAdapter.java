package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.sudoku.Utils.LEVEL_ONE;
import static com.example.sudoku.Utils.LEVEL_THREE;
import static com.example.sudoku.Utils.LEVEL_TWO;

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
        switch (position) {
            case LEVEL_ONE:
                holder.statistics.setText(R.string.started_games);
                break;
            case LEVEL_TWO:
                holder.statistics.setText(R.string.completed_games);
                break;
            case LEVEL_THREE:
                holder.statistics.setText(R.string.win_rate);
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
