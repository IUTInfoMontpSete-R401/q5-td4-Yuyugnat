package mvc.command;

public interface SudokuCommand {
    public abstract void execute();

    public abstract void undo();
}
