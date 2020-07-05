package com.example.sudoku;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class SplashAdapter extends RecyclerView.Adapter<SplashAdapter.ViewHolder>  {
    private ArrayList<Integer> list;
    private Context context;

    SplashAdapter(ArrayList<Integer> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SplashAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.splash, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SplashAdapter.ViewHolder holder, int position) {
        Log.d("position", String.valueOf(position));
        holder.splash.setAnimation(list.get(position) + ".json");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private LottieAnimationView splash;

        ViewHolder(View itemView) {
            super(itemView);
            splash = itemView.findViewById(R.id.splash_element);
        }
    }
}
