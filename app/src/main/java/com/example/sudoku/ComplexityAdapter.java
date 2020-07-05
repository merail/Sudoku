package com.example.sudoku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import static com.example.sudoku.Utils.FIVE;
import static com.example.sudoku.Utils.FOUR;
import static com.example.sudoku.Utils.ONE;
import static com.example.sudoku.Utils.THREE;
import static com.example.sudoku.Utils.TWO;


/**
 * Адаптер для спиннера с выбором источников
 */
public class ComplexityAdapter extends ArrayAdapter<Integer> {
    /**
     * Контекст
     */
    private Context mContext;
    /**
     * ImageView для начального элемента Spinner'а
     */
    private ImageView mInitImageView;

    /**
     * Конструктор класса
     *
     * @param context контекст
     */
    public ComplexityAdapter(Context context, ArrayList<Integer> list) {
        super(context, 0, list);

        mContext = context;
    }

    /**
     * Задание списка
     *
     * @param position    начальная позиция
     * @param convertView старое View
     * @param parent      ViewGroup
     * @return метод для инициализации
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initSpinnerButtonView(position, convertView, parent);
    }

    /**
     * Задание popup-списка
     *
     * @param position    позиция
     * @param convertView старое View
     * @param parent      ViewGroup
     * @return метод для инициализации
     */
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initDropDownView(position, convertView, parent);
    }

    /**
     * Метод для инициализации popup-списка
     *
     * @param position позиция
     * @param view     View
     * @param parent   ViewGroup
     * @return view
     */
    private View initDropDownView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.complexity, parent, false);
        }
        mInitImageView = view.findViewById(R.id.stars);

        getItemForInit(position);

        return view;
    }

    /**
     * Метод для задания первого элемента списка
     *
     * @param position позиция
     * @param view     view
     * @param parent   ViewGroup
     * @return view
     */
    private View initSpinnerButtonView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.complexity, parent, false);
        }
        mInitImageView = view.findViewById(R.id.stars);

        getItemForButton(position);


        return view;
    }

    public void getItemForInit(int position) {
        switch (position) {
            case ONE:
                mInitImageView.setBackgroundResource(R.drawable.complexity5);
                break;
            case TWO:
                mInitImageView.setBackgroundResource(R.drawable.complexity4);
                break;
            case THREE:
                mInitImageView.setBackgroundResource(R.drawable.complexity3);
                break;
            case FOUR:
                mInitImageView.setBackgroundResource(R.drawable.complexity2);
                break;
            case FIVE:
                mInitImageView.setBackgroundResource(R.drawable.complexity1);
                break;
        }
    }

    public void getItemForButton(int position) {
        switch (position) {
            case ONE:
                mInitImageView.setBackgroundResource(R.drawable.complexity5);
                break;
            case TWO:
                mInitImageView.setBackgroundResource(R.drawable.complexity4);
                break;
            case THREE:
                mInitImageView.setBackgroundResource(R.drawable.complexity3);
                break;
            case FOUR:
                mInitImageView.setBackgroundResource(R.drawable.complexity2);
                break;
            case FIVE:
                mInitImageView.setBackgroundResource(R.drawable.complexity1);
                break;
        }
    }
}
