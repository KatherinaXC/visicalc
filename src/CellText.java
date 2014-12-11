
public class CellText extends Cell {

    public CellText(String input) {
        super(input.substring(1, input.length() - 1));
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
        String output = getFormula() + "";
        //if output is NOTHING (cleared)
        if (output.equals("")) {
            for (int i = 0; i < width; i++) {
                output += " ";
            }
            return output;
        }
        if (output.length() < width) {
            if (getAlignment().equals("left") || getAlignment().equals("auto")) {
                //pad the right side
                for (int i = getFormula().length(); i < width; i++) {
                    output += " ";
                }
            } else if (getAlignment().equals("right")) {
                //pad the left side
                for (int i = getFormula().length(); i < width; i++) {
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
