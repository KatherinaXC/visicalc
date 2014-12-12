public class VisiCalc {

    private Cell[][] spreadsheet;
    private int height;
    private int width;
    private int[] colwidth;

    public VisiCalc(int cols, int rows) {
        //Initializes the visicalc
        spreadsheet = new Cell[rows][cols];
        this.height = rows;
        this.width = cols;
        //All cells are null initially, assignment creates new cell objects
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spreadsheet[i][j] = new Cell();
            }
        }
        //column width default is 10
        this.colwidth = new int[cols];
        for (int i = 0; i < colwidth.length; i++) {
            colwidth[i] = 10;
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
            //reads params and clears them
            input = input.substring(5);
            if (input.equals("")) {
                //clear ALL the cells - set input to full table
                input = fullTable();
            }
            //test if input is valid
            if (!isAParameter(input)) {
                return "Invalid parameters";
            }
            String[] cellarray = getIndividualCells(input);
            for (int i = 0; i < cellarray.length; i++) {
                spreadsheet[rownum(cellarray[i])][colnum(cellarray[i])].clear();
            }
            return null;
        } else if (input.toLowerCase().indexOf("dump") == 0) {
            //dumps cells, their formulas, values, alignments, widths
            input = input.substring(4).trim();
            if (input.equals("")) {
                //dump ALL the cells - set input to full table
                input = fullTable();
            }
            //test if input is valid
            if (!isAParameter(input)) {
                return "Invalid parameters";
            }
            String[] cellarray = getIndividualCells(input);
            String output = cellarray[0].toUpperCase()
                    + " = {"
                    + spreadsheet[rownum(cellarray[0])][colnum(cellarray[0])].dump(colwidth[colnum(cellarray[0])])
                    + "}";
            for (int i = 1; i < cellarray.length; i++) {
                output += "\n";
                output += cellarray[i].toUpperCase()
                        + " = {"
                        + spreadsheet[rownum(cellarray[i])][colnum(cellarray[i])].dump(colwidth[colnum(cellarray[i])])
                        + "}";

            }
            return output;
        } else if (input.toLowerCase().indexOf("align") == 0) {
            //because only a cell or a range of cells can be input
            String[] inputarray = input.split(" ");
            String alignment = inputarray[inputarray.length - 1];
            String params = input.substring(5, input.length() - alignment.length());
            //test if input is valid
            if (!isAParameter(params)) {
                return "Invalid parameters";
            }
            String[] cellarray = getIndividualCells(params);
            for (int i = 0; i < cellarray.length; i++) {
                spreadsheet[rownum(cellarray[i])][colnum(cellarray[i])].align(alignment);
            }
            return null;
        } else if (input.toLowerCase().indexOf("width") == 0) {
            //width command
            String[] inputarray = input.split(" ");
            //test if input column is valid
            if (inputarray[1].length() != 1
                    || !Character.isLetter(inputarray[1].charAt(0))
                    || Character.toUpperCase(inputarray[1].charAt(0)) - 'A' + 1 > width) {
                return "That column does not exist";
            }
            //max possible width is 20
            int newwidth = Integer.parseInt(inputarray[2]);
            if (newwidth > 20) {
                newwidth = 20;
            } else if (newwidth < 0) {
                newwidth = 0;
            }
            //set the width
            colwidth[Character.toUpperCase(inputarray[1].charAt(0)) - 'A'] = newwidth;
            return null;
        }
        //Assumed enter-assignment mode
        String[] params = input.split("=");
        params[0] = params[0].trim();
        params[1] = params[1].trim();
        if (!isACell(params[0])) {
            //Valid-cell error checking for LEFT SIDE ERRORS ONLY
            return "Cell reference, " + params[0] + ", is invalid";
        }
        //assign and create cells
        if (Character.isLetter(params[1].charAt(0))) {
            //expression or reference
            spreadsheet[rownum(params[0])][colnum(params[0])] = new CellExpr(params[1]);
        } else if (params[1].charAt(0) == '"') {
            //literal text
            spreadsheet[rownum(params[0])][colnum(params[0])] = new CellText(params[1].substring(1, params[1].length() - 1));
        } else {
            //TODO figure out why there are strings with equal signs slipping past the previous test?
            //number, it's the only option left. Syntax is "guaranteed" for double input
            spreadsheet[rownum(params[0])][colnum(params[0])] = new CellNum(params[1]);
        }
        return null;
    }

    private boolean isACell(String input) {
        //does the cell exist
        return input.indexOf(':') == -1
                && input.indexOf(',') == -1
                && input.length() > 1
                && Character.isLetter(input.charAt(0))
                && colnum(input) + 1 <= this.width
                && rownum(input) + 1 <= this.height;
    }

    private boolean isARange(String input) {
        //split the colons and test each cell for validity, then make sure the cells are in the right order
        String[] cellrange = input.split(":");
        return isACell(cellrange[0])
                && isACell(cellrange[1])
                && colnum(cellrange[0]) <= colnum(cellrange[1])
                && rownum(cellrange[0]) <= rownum(cellrange[1]);
    }

    private boolean isAParameter(String input) {
        String[] params = input.split(",");
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].trim();
            if (!isACell(params[i]) && !isARange(params[i])) {
                return false;
            }
        }
        return true;
    }

    private String[] getIndividualCells(String input) {
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
        //this... isn't actually used... :/
        return spreadsheet[rownum(location)][colnum(location)] + "";
    }

    private void cellArray(String param, String[] input, int startslot) {
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
        return "A1:" + (char) ('A' + width - 1) + (height);
    }

    public String toString() {
        String output = "";
        //top row (just the column headers)
        String row = "   ";
        for (int cols = 0; cols < width; cols++) {
            for (int spaces = 0; spaces < colwidth[cols] / 2; spaces++) {
                row += " ";
            }
            if (colwidth[cols] != 0) {
                row += (char) ('A' + cols);
            }
            for (int spaces = 0; spaces < (colwidth[cols] - 1) / 2; spaces++) {
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
                //all cells have content, but "null" cells will return just blanks
                row += spreadsheet[rows][cols].toString(colwidth[cols]) + "";
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
