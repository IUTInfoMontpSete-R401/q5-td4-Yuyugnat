package mvc.command;

import mvc.controller.SudokuController;

public class UndoCommand implements SudokuCommand {
    private SudokuController controller;

    public UndoCommand(SudokuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        if (!this.controller.getUndoStack().isEmpty()) {
            SudokuCommand command = controller.getUndoStack().pop();
            command.undo();
        }
    }

    @Override
    public void undo() {}
    
}
