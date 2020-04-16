package com.example.sudoku;
/* Java program for Sudoku generator  */

import java.util.ArrayList;

class Sudoku {
    private int[][] mSudokuMatrix;
    private ArrayList<Integer> mSudokuList;
    private final int N; // number of columns/rows.
    private final int SRN; // square root of N
    private int K; // No. Of missing digits

    // Constructor
    Sudoku(int complexity) {
        this.N = 9;
        switch (complexity)
        {
            case 0:
                this.K = 37;
                break;
            case 1:
                this.K = 41;
                break;
            case 2:
                this.K = 45;
                break;
            case 3:
                this.K = 49;
                break;
            case 4:
                this.K = 53;
                break;
        }

        SRN = 3;

        mSudokuMatrix = new int[N][N];
        mSudokuList = new ArrayList<>();
    }

    // Sudoku Generator
    void fillValues() {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, SRN);

        // Remove Randomly K digits to make game
        removeKDigits();
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    private void fillDiagonal() {

        for (int i = 0; i < N; i = i + SRN)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    private boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < SRN; i++)
            for (int j = 0; j < SRN; j++)
                if (mSudokuMatrix[rowStart + i][colStart + j] == num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
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

    // Random generator
    private int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // Check if safe to put in cell
    private boolean CheckIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % SRN, j - j % SRN, num));
    }

    // check in the row for existence
    private boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < N; j++)
            if (mSudokuMatrix[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    private boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < N; i++)
            if (mSudokuMatrix[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    private boolean fillRemaining(int i, int j) {
        //  System.out.println(i+" "+j);
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

    // Remove the K no. of digits to
    // complete game
    private void removeKDigits() {
        int count = K;
        while (count != 0) {
            int cellId = randomGenerator(N * N - 1);

            System.out.println(cellId);
            // extract coordinates i  and j
            int i = (cellId / N);
            int j = cellId % 9 + 1;
            if (j != 0)
                j = j - 1;

            System.out.println(i+" "+j);
            if (mSudokuMatrix[i][j] != 0) {
                count--;
                mSudokuMatrix[i][j] = 0;
            }
        }
    }

    // Print sudoku
    void printSudoku() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(mSudokuMatrix[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    ArrayList<Integer> getSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mSudokuList.add(mSudokuMatrix[i][j]);
            }
        }

        return mSudokuList;
    }
}