package com.example.sudoku;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
    public static final int currentApiVersion = android.os.Build.VERSION.SDK_INT;
    public static final int systemUiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    public static final ArrayList<Integer> DIGITS_LIST = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    public static final ArrayList<String> NAMES_OF_DIGITS_LIST = new ArrayList<>(Arrays.asList("one", "two", "three",
            "four", "five", "six",
            "seven", "eight", "nine"));

    public static final int LEVEL_ONE = 0;
    public static final int LEVEL_TWO = 1;
    public static final int LEVEL_THREE = 2;
    public static final int LEVEL_FOUR = 3;
    public static final int LEVEL_FIVE = 4;

    public static final int NUMBER_OF_LEVELS = 5;

    public static final String ARG_COMPLEXITY = "Complexity";
}
