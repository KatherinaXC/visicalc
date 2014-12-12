public class CellText extends Cell {

    public CellText(String input) {
        super(input);
    }

    public String dump(int colwidth) {
        String dumpformula = getFormula();
        //Dump for empty cells says is blank
        if (dumpformula == null) {
            dumpformula = "";
        }
        return " \"Formula\" = \"" + dumpformula
                + "\", \"Value\" = \"" + dumpformula
                + "\", \"Alignment\" = \"" + getAlignment()
                + "\", \"Width\" = \"" + colwidth + "\" ";
    }

    public String toString(int width) {
        //if formula is NOTHING (cleared)
        if (getFormula() == null) {
            return super.toString(width);
        }
        //otherwise, if there is something
        String output = getFormula();
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
