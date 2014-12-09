
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
            return "Goodbye.";
        } else if (input.toLowerCase().indexOf("clear") == 0) {
            input = input.substring(5).trim();
            spreadsheet[rownum(input)][colnum(input)] = null;
            return "Cell cleared.";
        } else if (input.toLowerCase().indexOf("dump") == 0) {
            //TODO fix this
            input = input.substring(4).trim();
            return input.toUpperCase() + " = {" + spreadsheet[rownum(input)][colnum(input)].dump(colwidth[colnum(input)]) + "}";
        } else if (input.toLowerCase().indexOf("align") == 0) {
            //TODO this
            return "Align command.";
        } else if (input.toLowerCase().indexOf("width") == 0) {
            //width command
            int newwidth = Integer.parseInt(input.substring(8));
            if (newwidth > 20) {
                newwidth = 20;
            } else if (newwidth < 0) {
                newwidth = 0;
            }
            colwidth[Character.toUpperCase(input.charAt(6)) - 'A'] = newwidth;
            return "Width of given column set to " + newwidth + ".";
        }
        String[] params = input.split(" = ");
        if (!isACell(params[0])) {
            //Valid-cell error checking for LEFT SIDE ERRORS
            return "Cell reference, " + params[0] + ", is invalid";
        }
        spreadsheet[rownum(params[0])][colnum(params[0])] = new Cell(params[1]);
        //TODO actual method running
        return "Assignment command.";
    }

    public String getValue(String location) {
        return spreadsheet[rownum(location)][colnum(location)] + "";
    }

    private boolean isACell(String input) {
        return input.indexOf(':') == -1
                && input.indexOf(',') == -1
                && colnum(input) <= this.width
                && rownum(input) <= this.height;
    }

    private boolean isARange(String input) {
        //split the colons and test each cell for validity, then make sure the cells are in the right order
        String[] cellrange = input.split(":");
        return isACell(cellrange[0])
                && isACell(cellrange[1])
                && colnum(cellrange[0]) <= colnum(cellrange[1])
                && rownum(cellrange[0]) <= rownum(cellrange[1]);
    }

    private String[] readParams(String input) {
        String[] params = input.split(",");
        int totalcells = 0;
        for (int i = 0; i < params.length; i++) {
            totalcells += numCells(params[i]);
        }
        String[] output = new String[totalcells];
        
        //TODO patch this later
        return output;
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

    public String toString() {
        String output = "";
        //top row (just the column headers)
        String row = "  ";
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
            row += (rows + 1);
            //actual data:                
            for (int cols = 0; cols < width; cols++) {
                if (spreadsheet[rows][cols] == null) {
                    //if that cell is null, write blanks
                    for (int i = 0; i < colwidth[cols]; i++) {
                        row += " ";
                    }
                } else {
                    //if it has content, write its content
                    row += spreadsheet[rows][cols].toString(colwidth[cols]) + "";
                }
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
