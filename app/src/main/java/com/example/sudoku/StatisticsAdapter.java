package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sudoku.database.Database;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {
    private Context context;
    private int mComplexity;

    StatisticsAdapter(Context context, int complexity) {
        this.context = context;
        mComplexity = complexity;
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
            case 0:
                holder.statistics.setText(R.string.started_games);
                String startedGames = Database.get(context).readData(mComplexity + "startedGames");
                if (startedGames.isEmpty())
                    holder.value.setText("0");
                else
                    holder.value.setText(startedGames);
                break;
            case 1:
                holder.statistics.setText(R.string.completed_games);
                break;
            case 2:
                holder.statistics.setText(R.string.win_rate);
                break;
            case 3:
                holder.statistics.setText(R.string.best_time);
                break;
            case 4:
                holder.statistics.setText(R.string.average_time);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView statistics;
        private TextView value;

        ViewHolder(View itemView) {
            super(itemView);

            statistics = itemView.findViewById(R.id.statistics);
            value = itemView.findViewById(R.id.value);
        }
    }
}
