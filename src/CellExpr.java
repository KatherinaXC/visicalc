public class CellExpr extends Cell{
    public CellExpr(String input) {
        super(input);
    }
    public String toString(int width) {
        //if formula is NOTHING (cleared)
        if (getFormula() == null) {
            return super.toString(width);
        }
        //otherwise, if there is something
        //TODO eval and such
        String output = "EXPRESSION CELL";
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
