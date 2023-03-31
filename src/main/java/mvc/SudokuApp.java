package mvc;

import java.util.Scanner;

import mvc.controller.SudokuController;

public class SudokuApp {
    public static void main(String[] args) {
        SudokuController controller = new SudokuController();
        controller.startGame();
    }
}
