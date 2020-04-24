package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BottomBarFragment extends Fragment {

    private GameCreateInterface mGameCreateInterface;
    private NewGameDialogCreateInterface mNewGameDialogCreateInterface;

    void bindNewGameInterface(NewGameDialogCreateInterface newGameDialogCreateInterface)
    {
        mNewGameDialogCreateInterface = newGameDialogCreateInterface;
    }

    static BottomBarFragment newInstance()
    {
        BottomBarFragment fragment = new BottomBarFragment();

        return fragment;
    }

    void bindComplexityChangeInterface(GameCreateInterface gameCreateInterface)
    {
        mGameCreateInterface = gameCreateInterface;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottombar, container, false);

        Spinner complexitySpinner = v.findViewById(R.id.complexitySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.complexity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        complexitySpinner.setAdapter(adapter);
        complexitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mGameCreateInterface.createGame(position);
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
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);

                values.put(DatabaseContract.Entry.COLUMN_NAME_TITLE, "title");
                values.put(DatabaseContract.Entry.COLUMN_NAME_SUBTITLE, Sudoku.getSudoku().toString());

                // Insert the new row, returning the primary key value of the new row
                Database.get(getContext()).getWriteDatabase().insert(DatabaseContract.Entry.TABLE_NAME, null, values);
            }
        });

        return v;
    }
}
