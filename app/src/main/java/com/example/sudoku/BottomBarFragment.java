package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sudoku.database.Database;
import com.example.sudoku.database.DatabaseContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static com.example.sudoku.Utils.FIVE;
import static com.example.sudoku.Utils.FOUR;
import static com.example.sudoku.Utils.ONE;
import static com.example.sudoku.Utils.THREE;
import static com.example.sudoku.Utils.TWO;

public class BottomBarFragment extends Fragment {

    private GameCreateInterface mGameCreateInterface;
    private NewGameDialogCreateInterface mNewGameDialogCreateInterface;

    static BottomBarFragment newInstance() {
        BottomBarFragment fragment = new BottomBarFragment();

        return fragment;
    }

    void bindNewGameInterface(NewGameDialogCreateInterface newGameDialogCreateInterface) {
        mNewGameDialogCreateInterface = newGameDialogCreateInterface;
    }

    void bindComplexityChangeInterface(GameCreateInterface gameCreateInterface) {
        mGameCreateInterface = gameCreateInterface;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottombar, container, false);

        final Spinner complexitySpinner = v.findViewById(R.id.complexitySpinner);
        final ArrayList<Integer> stars = new ArrayList<>();
        stars.add(ONE);
        stars.add(TWO);
        stars.add(THREE);
        stars.add(FOUR);
        stars.add(FIVE);
        complexitySpinner.setAdapter(new ComplexityAdapter(getContext(), stars));
        complexitySpinner.setSelection(FIVE);
        complexitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mGameCreateInterface.createGame((FIVE - 1) - position);
//                switch (position)
//                {
//                    default:
//                    case 0:
//                        complexitySpinner.setBackgroundResource(R.drawable.complexity1);
//                    case 1:
//                        complexitySpinner.setBackgroundResource(R.drawable.complexity2);
//                    case 2:
//                        complexitySpinner.setBackgroundResource(R.drawable.complexity3);
//                    case 3:
//                        complexitySpinner.setBackgroundResource(R.drawable.complexity4);
//                    case 4:
//                        complexitySpinner.setBackgroundResource(R.drawable.complexity5);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button newGameButton = v.findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewGameDialogCreateInterface.createNewGameDialog();
            }
        });

        Button saveButton = v.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();

                Date date = Calendar.getInstance().getTime();
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = dateFormat.format(date);

                values.put(DatabaseContract.Entry.COLUMN_NAME_TITLE, strDate);
                values.put(DatabaseContract.Entry.COLUMN_NAME_SUBTITLE, Sudoku.getSudoku().toString());

                // Insert the new row, returning the primary key value of the new row
                Database.get(getContext()).getWriteDatabase().insert(DatabaseContract.Entry.TABLE_NAME, null, values);
            }
        });

        return v;
    }
}
