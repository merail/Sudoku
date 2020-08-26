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

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private ArrayList<Integer> list;
    private Context context;

    private OnSetValueListener onSetValueListener;

    NotesAdapter(ArrayList<Integer> list, Context context, OnSetValueListener onSetValueListener) {
        this.list = list;
        this.context = context;
        this.onSetValueListener = onSetValueListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        switch (list.get(position))
        {
            case 1:
                holder.note.setImageResource(R.drawable.one);
                break;
            case 2:
                holder.note.setImageResource(R.drawable.two);
                break;
            case 3:
                holder.note.setImageResource(R.drawable.three);
                break;
            case 4:
                holder.note.setImageResource(R.drawable.four);
                break;
            case 5:
                holder.note.setImageResource(R.drawable.five);
                break;
            case 6:
                holder.note.setImageResource(R.drawable.six);
                break;
            case 7:
                holder.note.setImageResource(R.drawable.seven);
                break;
            case 8:
                holder.note.setImageResource(R.drawable.eight);
                break;
            default:
                holder.note.setImageResource(R.drawable.nine);
                break;
        }

        holder.note.setOnClickListener(new View.OnClickListener() {
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
        private ImageView note;

        ViewHolder(View itemView) {
            super(itemView);
            note = itemView.findViewById(R.id.note);
        }
    }
}