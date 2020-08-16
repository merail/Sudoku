package com.example.sudoku;
/* Java program for Sudoku generator  */

import java.util.ArrayList;

import static com.example.sudoku.Utils.LEVEL_FIVE;
import static com.example.sudoku.Utils.LEVEL_FOUR;
import static com.example.sudoku.Utils.LEVEL_ONE;
import static com.example.sudoku.Utils.LEVEL_THREE;
import static com.example.sudoku.Utils.LEVEL_TWO;

class Sudoku {
    private static int[][] mSudokuMatrix;
    private static ArrayList<Integer> mSudokuList;
    private final int N;
    private final int SRN;
    private int K;

    Sudoku(int complexity) {
        this.N = 9;
        switch (complexity) {
            case LEVEL_ONE:
                this.K = 37;
                break;
            case LEVEL_TWO:
                this.K = 41;
                break;
            case LEVEL_THREE:
                this.K = 45;
                break;
            case LEVEL_FOUR:
                this.K = 49;
                break;
            case LEVEL_FIVE:
                this.K = 53;
                break;
        }

        SRN = 3;

        mSudokuMatrix = new int[N][N];
        mSudokuList = new ArrayList<>();
    }

    static ArrayList<Integer> getSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mSudokuList.add(mSudokuMatrix[i][j]);
            }
        }

        return mSudokuList;
    }

    void fillValues() {
        fillDiagonal();

        fillRemaining(0, SRN);

        removeKDigits();
    }

    private void fillDiagonal() {

        for (int i = 0; i < N; i = i + SRN)

            fillBox(i, i);
    }

    private boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < SRN; i++)
            for (int j = 0; j < SRN; j++)
                if (mSudokuMatrix[rowStart + i][colStart + j] == num)
                    return false;

        return true;
    }

    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < SRN; i++) {
            for (int j = 0; j < SRN; j++) {
                do {
                    num = randomGenerator(N);
                }
                while (!unUsedInBox(row, col, num));

                mSudokuMatrix[row + i][col + j] = num;
            }
        }
    }

    private int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    private boolean CheckIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % SRN, j - j % SRN, num));
    }

    private boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < N; j++)
            if (mSudokuMatrix[i][j] == num)
                return false;
        return true;
    }

    private boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < N; i++)
            if (mSudokuMatrix[i][j] == num)
                return false;
        return true;
    }

    private boolean fillRemaining(int i, int j) {
        if (j >= N && i < N - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= N && j >= N)
            return true;

        if (i < SRN) {
            if (j < SRN)
                j = SRN;
        } else if (i < N - SRN) {
            if (j == (i / SRN) * SRN)
                j = j + SRN;
        } else {
            if (j == N - SRN) {
                i = i + 1;
                j = 0;
                if (i >= N)
                    return true;
            }
        }

        for (int num = 1; num <= N; num++) {
            if (CheckIfSafe(i, j, num)) {
                mSudokuMatrix[i][j] = num;
                if (fillRemaining(i, j + 1))
                    return true;

                mSudokuMatrix[i][j] = 0;
            }
        }
        return false;
    }

    private void removeKDigits() {
        int count = K;
        while (count != 0) {
            int cellId = randomGenerator(N * N - 1);

            int i = (cellId / N);
            int j = cellId % 9 + 1;
            if (j != 0)
                j = j - 1;

            if (mSudokuMatrix[i][j] != 0) {
                count--;
                mSudokuMatrix[i][j] = 0;
            }
        }
    }
}