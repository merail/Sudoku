package com.example.sudoku;

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

        return v;
    }
}
