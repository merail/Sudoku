package com.example.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopBarFragment extends Fragment {
    private OpenSavedInterface mOpenSavedInterface;

    static TopBarFragment newInstance() {
        TopBarFragment fragment = new TopBarFragment();

        return fragment;
    }

    public void bindOpenSavedInterface(OpenSavedInterface openSavedInterface)
    {
        mOpenSavedInterface = openSavedInterface;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_topbar, container, false);

        Button savedButton = v.findViewById(R.id.savedButton);
        savedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mOpenSavedInterface.openSavedActivity();

                //startActivity(new Intent(getContext(), SavedActivity.class));
            }
        });

        return v;
    }
}
