package mvc.command;

import mvc.model.SudokuModel;

public class SetValueCommand implements SudokuCommand {
    private int row;
    private int col;
    private int value;

    private SudokuModel model;

    public SetValueCommand(SudokuModel model, int row, int col, int value) {
        this.model = model;
        this.row = row;
        this.col = col;
        this.value = value;
    }

    @Override
    public void execute() {
        this.model.setValueAt(row, col, value);
    }

    @Override
    public void undo() {
        this.model.setValueAt(row, col, 0);
    }

    @Override
    public String toString() {
        return String.format("SetValueCommand: row=%d, col=%d, value=%d", row, col, value);
    }
}
