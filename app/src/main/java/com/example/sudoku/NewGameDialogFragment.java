package com.example.sudoku;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

public class NewGameDialogFragment extends AppCompatDialogFragment {

    private NewGameSetInterface mNewGameSetInterface;

    public void bindNewGameSetInterface(NewGameSetInterface newGameSetInterface) {
        mNewGameSetInterface = newGameSetInterface;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setMessage("Are you sure?")
                .setCancelable(true)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                mNewGameSetInterface.setNewGame();

                                dialog.cancel();
                            }
                        })
                .setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });

        return builder.create();
    }
}
