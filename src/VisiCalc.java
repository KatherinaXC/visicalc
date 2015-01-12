
import java.util.ArrayList;
import java.util.Scanner;

public class VisiCalc {

    private Cell[][] sheet;
    private int height;
    private int width;

    //constructors
    public VisiCalc(int cols, int rows) {
        //limitations on #cols and #rows
        if (rows > 99) {
            rows = 99;
        }
        if (cols > 26) {
            cols = 26;
        }
        if (rows < 0) {
            rows = 0;
        }
        if (cols < 0) {
            cols = 0;
        }
        //initializes spreadsheet + variables
        this.sheet = new Cell[rows][cols];
        this.height = rows;
        this.width = cols;
        //adds basic empty cells to sheet, all link to same single cell
        for (int temprow = 0; temprow < this.height; temprow++) {
            for (int tempcol = 0; tempcol < this.width; tempcol++) {
                this.sheet[temprow][tempcol] = new Cell();
            }
        }
    }

    public VisiCalc() {
        //calls other constructor with 7 rows and 20 columns
        this(7, 20);
    }

    //main body method
    public String calc(String input) {
        //tests commands first
        if (input.toLowerCase().equals("quit")) {
            //quit command
            //format: "quit"
            return "Goodbye.";
        } else if (input.toLowerCase().equals("help")) {
            //help command
            //format: "help"
            return getHelp();
        } else if (input.toLowerCase().indexOf("clear") == 0) {
            //clear command (resets given cells to the empty cell)
            //format: "clear", "clear <cell>", "clear <range>"
            return commandClear(input.substring("clear ".length() - 1).trim());
        } else if (input.toLowerCase().indexOf("dump") == 0) {
            //dump command
            //format: "dump", "dump <cell>", "dump <range>"
            return commandDump(input.substring("dump ".length() - 1).trim());
        } else if (input.toLowerCase().indexOf("align") == 0) {
            //align command
            //format: "align <cell> <alignment>", "align <range> <alignment>"
            return commandAlign(input.substring("align ".length() - 1).trim());
        } else if (input.toLowerCase().indexOf("width") == 0) {
            //setwidth command
            //format: "width <column> <value>"
            return commandWidth(input.substring("width ".length() - 1).trim());
        }
        //everything that's not commands would be assignments
        String celladdress = input.substring(0, input.indexOf("=")).trim();
        String cellformula = input.substring(input.indexOf("=") + 1).trim();
        if (!isACell(celladdress)) {
            //Valid-cell error checking for left side errors
            if (isCellForm(celladdress)) {
                return "Cell reference, " + celladdress + ", is invalid";
            }
        }
        //to preserve width across overwrite...
        int oldwidth = sheet[rownum(celladdress)][colnum(celladdress)].getWidth();
        //init new cells in specific forms
        if (cellformula.charAt(0) == '"' && cellformula.charAt(cellformula.length() - 1) == '"') {
            //literal text
            sheet[rownum(celladdress)][colnum(celladdress)] = new CellText(cellformula, this, oldwidth);
        } else if (isABinExpr(cellformula)) {
            //binary expression
            sheet[rownum(celladdress)][colnum(celladdress)] = new CellBin(cellformula, this, oldwidth);
        } else if (Character.isLetter(cellformula.charAt(0))) {
            //function or reference
            if (isCellForm(cellformula)) {
                //single reference
                sheet[rownum(celladdress)][colnum(celladdress)] = new CellRef(cellformula, this, oldwidth);
            } else {
                //command op
                if (cellformula.toUpperCase().startsWith("CONCAT")) {
                    sheet[rownum(celladdress)][colnum(celladdress)]
                            = new CellFormConcat(cellformula, this, oldwidth);
                } else if (cellformula.toUpperCase().startsWith("COUNT")) {
                    sheet[rownum(celladdress)][colnum(celladdress)]
                            = new CellFormCount(cellformula, this, oldwidth);
                } else if (cellformula.toUpperCase().startsWith("SUM")) {
                    sheet[rownum(celladdress)][colnum(celladdress)]
                            = new CellFormSum(cellformula, this, oldwidth);
                } else if (cellformula.toUpperCase().startsWith("UPPER")) {
                    sheet[rownum(celladdress)][colnum(celladdress)]
                            = new CellFormUpper(cellformula, this, oldwidth);
                } else if (cellformula.toUpperCase().startsWith("LENGTH")) {
                    sheet[rownum(celladdress)][colnum(celladdress)]
                            = new CellFormLength(cellformula, this, oldwidth);
                } else if (cellformula.toUpperCase().startsWith("POWER")) {
                    sheet[rownum(celladdress)][colnum(celladdress)]
                            = new CellFormPower(cellformula, this, oldwidth);
                } else {//(cellformula.toUpperCase().startsWith("SQRT("))
                    sheet[rownum(celladdress)][colnum(celladdress)]
                            = new CellFormSqrt(cellformula, this, oldwidth);
                }
            }
        } else {
            //number
            sheet[rownum(celladdress)][colnum(celladdress)] = new CellNum(cellformula, this, oldwidth);
        }
        return null;
    }

    //command methods
    public static String getHelp() {
        //TODO
        return "Help function.";
    }

    public String commandClear(String input) {
        //if input is "", then clear all the cells
        if (input.equals("")) {
            input = fullTable();
        }
        //test for input validity
        if (commandRangeError(input) != null) {
            return commandRangeError(input);
        }
        String[] cellarray = getIndividualCells(input);
        for (int i = 0; i < cellarray.length; i++) {
            sheet[rownum(cellarray[i])][colnum(cellarray[i])] = new Cell();
        }
        return null;
    }

    public String commandDump(String input) {
        //if input is "", then dump all the cells
        if (input.equals("")) {
            input = fullTable();
        }
        //test for input validity
        if (commandRangeError(input) != null) {
            return commandRangeError(input);
        }
        String[] cellarray = getIndividualCells(input);
        String output = getDump(cellarray[0]);
        for (int i = 1; i < cellarray.length; i++) {
            output += "\n" + getDump(cellarray[i]);
        }
        return output;
    }

    public String commandAlign(String input) {
        //array containing [<colmark>, <width>]
        String[] inputarray = input.split(" ");
        //test if input is valid
        if (commandRangeError(inputarray[0]) != null) {
            return commandRangeError(inputarray[0]);
        }
        String[] cellarray = getIndividualCells(inputarray[0]);
        for (int i = 0; i < cellarray.length; i++) {
            sheet[rownum(cellarray[i])][colnum(cellarray[i])].setAlign(inputarray[1]);
        }
        return null;
    }

    public String commandWidth(String input) {
        //array containing [<column>, <value>]
        String[] inputarray = input.split(" ");
        //test if input column is valid
        if (inputarray[0].length() != 1
                || !Character.isLetter(inputarray[0].charAt(0))
                || Character.toUpperCase(inputarray[0].charAt(0)) - 'A' + 1 > width) {
            return "Column reference, " + inputarray[0] + " is invalid";
        }
        //max possible width is 20, although this will be enforced in the cell-method
        int newwidth = Integer.parseInt(inputarray[1]);
        for (int i = 0; i < this.height; i++) {
            sheet[i][Character.toUpperCase(inputarray[0].charAt(0)) - 'A'].setWidth(newwidth);
        }
        return null;
    }

    //util methods
    public boolean isCellForm(String input) {
        //returns if the input is in the shape of a cell address
        //DOES NOT TEST FOR OUT OF BOUNDS
        return input.indexOf(':') == -1
                && input.indexOf(',') == -1
                && input.length() > 1
                && Character.isLetter(input.charAt(0))
                && isNumber(input.substring(1));
    }

    public boolean isACell(String celladdress) {
        //this method tests if the cell format is correct, and if so, if it is within the range of the spreadsheet
        return isCellForm(celladdress)
                && colnum(celladdress) < width
                && rownum(celladdress) < height;
    }

    public boolean isARange(String input) {
        //split the colons and test each cell for validity, then make sure the cells are in the right order
        String[] cellrange = input.split(":");
        return cellrange.length > 1
                && isACell(cellrange[0])
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

    public static boolean isABinExpr(String input) {
        //tests if a cell is a binary expression (add, subtract, mult, div)
        return (input.indexOf(" + ") != -1 && input.indexOf(" + ") != 0 && input.indexOf(" + ") != input.length() - 3)
                || (input.indexOf(" - ") != -1 && input.indexOf(" - ") != 0 && input.indexOf(" - ") != input.length() - 3)
                || (input.indexOf(" * ") != -1 && input.indexOf(" * ") != 0 && input.indexOf(" * ") != input.length() - 3)
                || (input.indexOf(" / ") != -1 && input.indexOf(" / ") != 0 && input.indexOf(" / ") != input.length() - 3);
    }

    public static boolean isNumber(String input) {
        Scanner read = new Scanner(input);
        return read.hasNextDouble();
    }

    public boolean isFilled(String location) {
        //tests if a cell contains any information
        return sheet[rownum(location)][colnum(location)].getValue() != null;
    }

    public static int colnum(String cell) {
        //translate a cell coord to 0-based Cell[][x] number
        //all input is assumed to be in cell form
        return cell.toUpperCase().charAt(0) - 'A';
    }

    public static int rownum(String cell) {
        //translate a cell coord to 0-based Cell[x][] number
        //all input is assumed to be in cell form
        return Integer.parseInt(cell.substring(1)) - 1;
    }

    public String fullTable() {
        //returns a range representing the entire spreadsheet
        return "A1:" + (char) ('A' + width - 1) + (height);
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

    private void cellArray(String param, String[] input, int startslot) {
        //this is a separate component of getIndividualCells() method
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

    public String commandRangeError(String input) {
        if (!isAParameter(input)) {
            //Valid-cell error checking for left side errors
            if (isCellForm(input)) {
                //if it's an invalid cell
                return "Cell reference, " + input + ", is invalid";
            }
            //if it's an invalid range
            if (!isCellForm(input.substring(0, input.indexOf(':')))) {
                //first param
                return "Cell reference, " + input.substring(0, input.indexOf(':')) + ", is invalid";
            } else {
                //second param
                return "Cell reference, " + input.substring(input.indexOf(':') + 1, input.length()) + ", is invalid";
            }
        }
        return null;
    }

    //obtaining properties/info about a cell, ALL PUBLIC (or should be haha)
    public String getDump(String location) {
        location = location.trim();
        //returns one line (no linebreak) of dumptext for a single cell
        return location.toUpperCase()
                + " = {"
                + sheet[rownum(location)][colnum(location)].getDump()
                + "}";
    }

    public String getValue(String location) {
        location = location.trim();
        //gets the printed value of a certain cell, WITH SPACING. (This includes #####)
        //if the cell is invalid it returns null
        if (!isACell(location)) {
            return null;
        }
        return sheet[rownum(location)][colnum(location)].toString();
    }

    public String getCellValue(String location) {
        //returns the value of a certain cell, WITH NO PADDING.
        //if the cell is invalid it returns null
        location = location.trim();
        if (!isACell(location)) {
            return null;
        }
        return sheet[rownum(location)][colnum(location)].getValue();
    }

    //print (toString) method
    public String toString() {
        //prints spreadsheet
        String output = "";
        //top row (just the column headers)
        String row = "   ";
        for (int cols = 0; cols < width; cols++) {
            for (int spaces = 0; spaces < sheet[0][cols].getWidth() / 2; spaces++) {
                row += " ";
            }
            if (sheet[0][cols].getWidth() != 0) {
                row += (char) ('A' + cols);
            }
            for (int spaces = 0; spaces < (sheet[0][cols].getWidth() - 1) / 2; spaces++) {
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
                row += sheet[rows][cols].toString() + " ";
            }
            if (row.length() < 80) {
                output += row + "\n";
            } else {
                output += row.substring(0, 80) + "\n";
            }
        }
        return output;
    }
}
