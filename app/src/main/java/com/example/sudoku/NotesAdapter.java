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

import static com.example.sudoku.Utils.NAMES_OF_DIGITS_LIST;

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
        holder.note.setImageResource(context.getResources().getIdentifier(NAMES_OF_DIGITS_LIST.get(list.get(position) - 1),
                "drawable",
                context.getPackageName()));

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