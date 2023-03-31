package mvc.model;

import java.util.ArrayList;
import java.util.List;

import mvc.view.SudokuObserver;

public class SudokuModel {
    private static int size = 9;

    private List<SudokuObserver> observers;
    private int[][] board;

    public SudokuModel() {
        this.observers = new ArrayList<>();
        this.board = new int[SudokuModel.size][SudokuModel.size];
        for (int i = 0; i < SudokuModel.size; i++)
            for (int j = 0; j < SudokuModel.size; j++) {
                this.board[i][j] = 0;
            }
    }

    public int getValueAt(int row, int column) {
        return this.board[row][column];
    }

    public boolean isValueValid(int row, int col, int value) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == value) {
                return false;
            }
        }

        // Check region
        int regionSize = (int) Math.sqrt(board.length);
        int rowRegionStart = (row / regionSize) * regionSize;
        int colRegionStart = (col / regionSize) * regionSize;
        for (int i = rowRegionStart; i < rowRegionStart + regionSize; i++) {
            for (int j = colRegionStart; j < colRegionStart + regionSize; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public void setValueAt(int row, int col, int value) {
        board[row - 1][col - 1] = value;
        notifyObservers();
    }

    public int getBoardSize() {
        return board.length;
    }

    public boolean isGameFinished() {
        for (int i = 0; i < this.getBoardSize(); i++) {
            for (int j = 0; j < this.getBoardSize(); j++) {
                if (getValueAt(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void registerObserver(SudokuObserver view) {
        this.observers.add(view);
    }

    public void notifyObservers() {
        for (SudokuObserver observer : this.observers) {
            observer.update();
        }
    }

    public int getBlockSize() {
        return (int) Math.sqrt(board[0].length);
    }


}
