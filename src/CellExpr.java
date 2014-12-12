
public class CellExpr extends Cell {

    //must have access to entire spreadsheet to be able to calculate values
    public CellExpr(String input, Cell[][] spreadsheet) {
        super(input);
    }
    
    public String dump(int colwidth) {
        String dumpformula = getFormula();
        //Dump for empty cells says is blank
        if (dumpformula == null) {
            dumpformula = "";
        }
        return " \"Formula\" = \"" + dumpformula
                + "\", \"Value\" = \"" + calculate(dumpformula)
                + "\", \"Alignment\" = \"" + getAlignment()
                + "\", \"Width\" = \"" + colwidth + "\" ";
    }

    private String calculate(String input) {
    }

    public String toString(int width) {
        //if formula is NOTHING (cleared)
        if (getFormula() == null) {
            return super.toString(width);
        }
        //otherwise, if there is something
        //TODO eval and such
        String output = calculate(getFormula());
        if (output.length() < width) {
            if (getAlignment().equals("left") || getAlignment().equals("auto")) {
                //pad the right side
                while (output.length() < width) {
                    output += " ";
                }
            } else if (getAlignment().equals("right")) {
                //pad the left side
                while (output.length() < width) {
                    output = " " + output;
                }
            }
        } else if (output.length() > width) {
            //always "left" aligned, truncate right
            output = output.substring(0, width);
        }
        return output;
    }
}
