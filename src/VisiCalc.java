
public class VisiCalc {

    private Cell[][] spreadsheet;
    private int height;
    private int width;
    private int colwidth;

    public VisiCalc(int cols, int rows) {
        //Initializes the visicalc
        spreadsheet = new Cell[rows][cols];
        this.height = rows;
        this.width = cols;
        this.colwidth = 10;
    }

    public VisiCalc() {
        //calls other constructor with default values 7 and 20
        this(7, 20);
    }

    public String calc(String input) {
        if (input.toLowerCase().equals("quit")) {
            return "Goodbye.";
        } else if (input.toLowerCase().indexOf("clear") == 0) {
            //TODO variants of clear command
            return "Clear command.";
        } else if (input.toLowerCase().indexOf("dump") == 0) {
            //TODO variants of dump command
            return "Dump command.";
        }
        String[] params = input.split(" = ");
        if (!isItActually(params[0])) {
            //Valid-cell error checking
            return "Cell reference, " + params[0] + ", is invalid";
        }
        //TODO actual method running
        return "Assignment command.";
    }

    public String getValue(String location) {
        //TODO return the actual value
        return "Result of getValue()";
    }

    private boolean isItActually(String cells) {
        //takes cells and ranges ONLY, returns if it is a valid range/cell
        if (cells.indexOf(':') == -1) {
            //if it is a single cell
            return colnum(cells) <= this.width && rownum(cells) <= this.height;
        } else {
            //if it is a range of cells
            String[] cellrange = cells.split(":");
            return isItActually(cellrange[0])
                    && isItActually(cellrange[1])
                    && colnum(cellrange[0]) <= colnum(cellrange[1])
                    && rownum(cellrange[0]) <= rownum(cellrange[1]);
        }
    }

    private int colnum(String cell) {
        //returns the columnn number of the given cell
        return cell.toUpperCase().charAt(0) - 'A';
    }

    private int rownum(String cell) {
        return Integer.parseInt(cell.substring(1));
    }

    public String toString() {
        String output = "  ";
        for (int i = 0; i <= width; i++) {
            //TODO printing
        }
        return "Result of toString()";
    }

    public static String getHelp() {
        //TODO finish this
        return "Help text here.";
    }
}
