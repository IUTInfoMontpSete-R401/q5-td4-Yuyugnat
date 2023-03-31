package mvc.view;

import mvc.model.SudokuModel;

public class SudokuView implements SudokuObserver {

    private SudokuModel model;

    public SudokuView(SudokuModel model) {
        this.model = model;
    }
    
    public void displayVictoryMessage() {
        System.out.println("Congratulations, you won the game!");
    }

    public void update() {
        for (int row = 0; row < this.model.getBoardSize(); row++) {
            if (row % this.model.getBlockSize() == 0) {
                System.out.println(" -----------------------");
            }
            for (int col = 0; col < this.model.getBoardSize(); col++) {
                if (col % this.model.getBlockSize() == 0) {
                    System.out.print("| ");
                }
                int value = this.model.getValueAt(row, col);
                if (value == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(value + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }
}
