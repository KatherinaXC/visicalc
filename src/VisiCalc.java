public class VisiCalc {
    
    Cell[][] spreadsheet;
    int height;
    int width;

    public VisiCalc(int cols, int rows) {
        spreadsheet = new Cell[rows][cols];
        this.height = rows;
        this.width = cols;
    }

    public VisiCalc() {
        //calls other constructor with default values 7 and 20
        this(7, 20);
    }

    public String calc(String input) {
    }
    
    public String getValue(String location) {
    }

    public String toString() {
    }

    public static String getHelp() {
        //TODO finish this
        return "Help text here.";
    }
}
