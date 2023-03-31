package mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import mvc.colors.Colors;
import mvc.colors.Colors.Color;
import mvc.command.SetValueCommand;
import mvc.command.SudokuCommand;
import mvc.command.UndoCommand;
import mvc.model.SudokuModel;
import mvc.view.SudokuView;

public class SudokuController {
    private SudokuModel model;
    private SudokuView view;

    private Stack<SudokuCommand> undoStack;
    private static List<String> validCommands = List.of("play", "undo");

    public void startGame() {
        this.model = new SudokuModel();
        this.view = new SudokuView(this.model);
        this.model.registerObserver(this.view);
        this.view.update();
        this.undoStack = new Stack<SudokuCommand>();

        while (!this.model.isGameFinished()) {
            String command = handleUserCommandInput();
            doAction(command);
        }

        this.view.displayVictoryMessage();
    }

    private int handleUserPlayInput(String rowOrCol) {
        List<Integer> validInputs = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int val;
        do {
            System.out.println(Colors.colorize(String.format("Enter a %s in [1-9]: ", rowOrCol), Color.YELLOW));
            val = (new Scanner(System.in)).nextInt();
        } while (!validInputs.contains(val));
        return val;
    }

    private String handleUserCommandInput() {
        String command;
        do {
            System.out.println(Colors.colorize("Enter a command (undo or play) :", Color.YELLOW));
            command = (new Scanner(System.in)).nextLine();
        } while (!validCommands.contains(command));
        return command;
    }

    public Stack<SudokuCommand> getUndoStack() {
        return undoStack;
    }

    private void doAction(String command) {
        switch (command) {
            case "play" -> {
                int row = handleUserPlayInput("row");
                int col = handleUserPlayInput("column");
                int value = handleUserPlayInput("value");
                SudokuCommand setValueCommand = new SetValueCommand(this.model, row, col, value);
                this.undoStack.push(setValueCommand);
                setValueCommand.execute();
            }
            case "undo" -> {
                if (this.undoStack.isEmpty()) {
                    System.out.println(Colors.colorize("Nothing to undo!", Color.RED));
                    return;
                }
                SudokuCommand undoCommand = new UndoCommand(this);
                undoCommand.execute();
            }
        }
    }
}
