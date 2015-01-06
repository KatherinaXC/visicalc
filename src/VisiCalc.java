
import java.util.Scanner;

public class VisiCalc {

    private Cell[][] spreadsheet;
    private int height;
    private int width;

    public VisiCalc(int cols, int rows) {
        //Initializes the visicalc
        spreadsheet = new Cell[rows][cols];
        if (rows > 99) {
            rows = 99;
        }
        if (cols > 26) {
            cols = 26;
        }
        this.height = rows;
        this.width = cols;
        //All cells are null initially, assignment all points to same cell object
        Cell cell = new Cell();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spreadsheet[i][j] = cell;
            }
        }
    }

    public VisiCalc() {
        //calls other constructor with default values 7 and 20
        this(7, 20);
    }

    public String calc(String input) {
        if (input.toLowerCase().equals("quit")) {
            //self explanatory... quits program
            return "Goodbye.";
        } else if (input.toLowerCase().equals("help")) {
            return getHelp();
        } else if (input.toLowerCase().indexOf("clear") == 0) {
            return commandClear(input);
        } else if (input.toLowerCase().indexOf("dump") == 0) {
            return commandDump(input);
        } else if (input.toLowerCase().indexOf("align") == 0) {
            return commandAlign(input);
        } else if (input.toLowerCase().indexOf("width") == 0) {
            return commandWidth(input);
        }
        //Assumed enter-assignment mode
        String cell = input.substring(0, input.indexOf("=")).trim();
        String formula = input.substring(input.indexOf("=") + 1).trim();
        if (!isACell(cell)) {
            //Valid-cell error checking for LEFT SIDE ERRORS ONLY
            return "Cell reference, " + cell.trim() + ", is invalid";
        }
        //assign and create cells
        if (formula.charAt(0) == '"' && formula.charAt(formula.length() - 1) == '"') {
            //literal text
            spreadsheet[rownum(cell)][colnum(cell)] = new CellText(formula.substring(1, formula.length() - 1), this);
        } else if (Character.isLetter(formula.charAt(0)) || isExpr(formula)) {
            //expression or reference
            if (isCellForm(formula)) {
                //single reference
                spreadsheet[rownum(cell)][colnum(cell)] = new CellRef(formula, this);
            } else {
                //command op or binary op
                spreadsheet[rownum(cell)][colnum(cell)] = new CellBin(formula, this);
            }
        } else {
            //number, it's the only option left. Syntax is "guaranteed" for double input
            spreadsheet[rownum(cell)][colnum(cell)] = new CellNum(formula, this);
        }
        return null;
    }

    private String commandClear(String input) {
        //reads params and clears them
        input = input.substring(5);
        if (input.equals("")) {
            //clear ALL the cells - set input to full table
            input = fullTable();
        }
        //test if input is valid
        if (!isAParameter(input)) {
            return "Cell reference, " + input.trim() + ", is invalid";
        }
        String[] cellarray = getIndividualCells(input);
        Cell blankcell = new Cell();
        for (int i = 0; i < cellarray.length; i++) {
            spreadsheet[rownum(cellarray[i])][colnum(cellarray[i])] = blankcell;
        }
        return null;
    }

    private String commandDump(String input) {
        //dumps cells, their formulas, values, alignments, widths
        input = input.substring(4).trim();
        if (input.equals("")) {
            //dump ALL the cells - set input to full table
            input = fullTable();
        }
        //test if input is valid
        if (!isAParameter(input)) {
            return "Cell reference, " + input.trim() + ", is invalid";
        }
        String[] cellarray = getIndividualCells(input);
        String output = cellarray[0].toUpperCase()
                + " = {"
                + spreadsheet[rownum(cellarray[0])][colnum(cellarray[0])].dump()
                + "}";
        for (int i = 1; i < cellarray.length; i++) {
            output += "\n";
            output += cellarray[i].toUpperCase()
                    + " = {"
                    + spreadsheet[rownum(cellarray[i])][colnum(cellarray[i])].dump()
                    + "}";

        }
        return output;
    }

    private String commandAlign(String input) {
        //because only a cell or a range of cells can be input
        String[] inputarray = input.split(" ");
        String alignment = inputarray[inputarray.length - 1];
        String params = input.substring(5, input.length() - alignment.length());
        //test if input is valid
        if (!isAParameter(params)) {
            return "Cell reference, " + params.trim() + ", is invalid";
        }
        String[] cellarray = getIndividualCells(params);
        for (int i = 0; i < cellarray.length; i++) {
            spreadsheet[rownum(cellarray[i])][colnum(cellarray[i])].align(alignment);
        }
        return null;
    }

    private String commandWidth(String input) {
        //width command
        String[] inputarray = input.split(" ");
        //test if input column is valid
        if (inputarray[1].length() != 1
                || !Character.isLetter(inputarray[1].charAt(0))
                || Character.toUpperCase(inputarray[1].charAt(0)) - 'A' + 1 > width) {
            return "Column reference, " + inputarray[1] + " is invalid";
        }
        //max possible width is 20
        int newwidth = Integer.parseInt(inputarray[2]);
        //set the width (value validity testing is IN method)
        for (int i = 0; i < this.height - 1; i++) {
            spreadsheet[i][Character.toUpperCase(inputarray[1].charAt(0)) - 'A'].setWidth(newwidth);
        }
        return null;
    }

    public boolean isACell(String input) {
        //does the cell exist?
        return input.indexOf(':') == -1
                && input.indexOf(',') == -1
                && input.length() > 1
                && Character.isLetter(input.charAt(0))
                && colnum(input) + 1 <= this.width
                && rownum(input) + 1 <= this.height;
    }

    public boolean isARange(String input) {
        //split the colons and test each cell for validity, then make sure the cells are in the right order
        String[] cellrange = input.split(":");
        return isACell(cellrange[0])
                && isACell(cellrange[1])
                && colnum(cellrange[0]) <= colnum(cellrange[1])
                && rownum(cellrange[0]) <= rownum(cellrange[1]);
    }

    public boolean isAParameter(String input) {
        //tests if an input string is proper parameter
        String[] params = input.split(",");
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].trim();
            if (!isACell(params[i]) && !isARange(params[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isExpr(String input) {
        //tests if a cell is an expression (add, subtract, mult, div)
        return !(input.charAt(0) == '"' && input.charAt(input.length() - 1) == '"')
                && ((input.indexOf('+') != -1 && input.indexOf('+') != 0 && input.indexOf('+') != input.length() - 1)
                || (input.indexOf('-') != -1 && input.indexOf('-') != 0 && input.indexOf('-') != input.length() - 1)
                || (input.indexOf('*') != -1 && input.indexOf('*') != 0 && input.indexOf('*') != input.length() - 1)
                || (input.indexOf('/') != -1 && input.indexOf('/') != 0 && input.indexOf('/') != input.length() - 1));
    }

    public boolean isFilled(String input) {
        return isACell(input)
                && spreadsheet[rownum(input)][colnum(input)].getValue() != null;
    }

    private static boolean isNumber(String input) {
        Scanner read = new Scanner(input);
        return read.hasNextDouble();
    }

    private static boolean isCellForm(String input) {
        return input.length() >= 2
                && Character.isLetter(input.charAt(0))
                && isNumber(input.substring(1));
    }

    public String[] getIndividualCells(String input) {
        //returns a string array with all combinations of cells
        String[] params = input.split(",");
        int totalcells = 0;
        for (int i = 0; i < params.length; i++) {
            totalcells += numCells(params[i]);
        }
        String[] output = new String[totalcells];
        totalcells = 0;
        for (int i = 0; i < params.length; i++) {
            cellArray(params[i], output, totalcells);
            totalcells += numCells(params[i]);
        }
        return output;
    }

    public String getValue(String location) {
        //gets the printed value of a certain cell
        if (!isACell(location)) {
            return "Cell reference, " + location.trim() + ", is invalid";
        }
        return spreadsheet[rownum(location)][colnum(location)].toString();
    }

    public String getDump(String location) {
        return location.toUpperCase()
                + " = {"
                + spreadsheet[rownum(location)][colnum(location)].dump()
                + "}";
    }

    public String getCellValue(String location) {
        //gets the value of a certain cell
        if (!isACell(location)) {
            return "Cell reference, " + location.trim() + ", is invalid";
        }
        return spreadsheet[rownum(location)][colnum(location)].getValue();
    }

    private void cellArray(String param, String[] input, int startslot) {
        //this is a separate component of another method
        param = param.trim();
        if (isACell(param)) {
            //put the cell in the array
            input[startslot] = param;
        } else {
            //put the range in the array
            String[] cellcorners = param.split(":");
            int slot = 0;
            for (int i = 0; i <= Character.toUpperCase(cellcorners[1].charAt(0)) - Character.toUpperCase(cellcorners[0].charAt(0)); i++) {
                for (int j = 0; j <= Integer.parseInt(cellcorners[1].substring(1)) - Integer.parseInt(cellcorners[0].substring(1)); j++) {
                    input[slot + startslot] = "" + (char) (Character.toUpperCase(cellcorners[0].charAt(0)) + i) + (Integer.parseInt(cellcorners[0].substring(1)) + j);
                    slot++;
                }
            }
        }
    }

    private int numCells(String param) {
        //return the number of cells in the cell/range
        param = param.trim();
        if (isACell(param)) {
            //one cell is one cell
            return 1;
        } else if (isARange(param)) {
            //if it's a range
            String[] cells = param.split(":");
            return (colnum(cells[1]) - colnum(cells[0]) + 1) * (rownum(cells[1]) - rownum(cells[0]) + 1);
        } else {
            //if it's a nonexistent cell or something
            return 0;
        }
    }

    private int colnum(String cell) {
        //returns the column number of the given cell
        return cell.toUpperCase().charAt(0) - 'A';
    }

    private int rownum(String cell) {
        //returns the row number of the given cell
        return Integer.parseInt(cell.substring(1)) - 1;
    }

    private String fullTable() {
        //returns dimensions of the whole spreadsheet
        return "A1:" + (char) ('A' + width - 1) + (height);
    }

    public String toString() {
        //prints spreadsheet
        String output = "";
        //top row (just the column headers)
        String row = "   ";
        for (int cols = 0; cols < width; cols++) {
            for (int spaces = 0; spaces < spreadsheet[0][cols].getWidth() / 2; spaces++) {
                row += " ";
            }
            if (spreadsheet[0][cols].getWidth() != 0) {
                row += (char) ('A' + cols);
            }
            for (int spaces = 0; spaces < (spreadsheet[0][cols].getWidth() - 1) / 2; spaces++) {
                row += " ";
            }
        }
        if (row.length() < 80) {
            output += row;
        } else {
            output += row.substring(0, 80);
        }
        output += "\n";
        //section below head row with row numbers and content
        for (int rows = 0; rows < height; rows++) {
            row = "";
            //row numbers:
            if (rows < 9) {
                //this is so that the one-digit row numbers get padded
                row += " ";
            }
            row += (rows + 1) + " ";
            //actual data:                
            for (int cols = 0; cols < width; cols++) {
                //all cells have content, but "null" cells should return just blanks
                row += spreadsheet[rows][cols].toString() + " ";
            }
            if (row.length() < 80) {
                output += row + "\n";
            } else {
                output += row.substring(0, 80) + "\n";
            }
        }
        return output;
    }

    public static String getHelp() {
        //TODO update as necessary
        return "Help text here.";
    }
}
