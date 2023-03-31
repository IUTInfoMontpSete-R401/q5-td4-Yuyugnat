package mvc.colors;

public class Colors {

    private static final boolean ANSI = true;

    public enum Color {
        BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String colorize(String text, Color color) {
        if (!ANSI) {
            return text;
        }
        String colorCode = "";
        switch (color) {
            case BLACK:
                colorCode = ANSI_BLACK;
                break;
            case RED:
                colorCode = ANSI_RED;
                break;
            case GREEN:
                colorCode = ANSI_GREEN;
                break;
            case YELLOW:
                colorCode = ANSI_YELLOW;
                break;
            case BLUE:
                colorCode = ANSI_BLUE;
                break;
            case PURPLE:
                colorCode = ANSI_PURPLE;
                break;
            case CYAN:
                colorCode = ANSI_CYAN;
                break;
            case WHITE:
                colorCode = ANSI_WHITE;
                break;
        }
        return colorCode + text + ANSI_RESET;
    }
}
