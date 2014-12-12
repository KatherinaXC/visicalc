public class CellNum extends Cell {

    public CellNum(String input) {
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

    private Double getVal() {
        return Double.parseDouble(getFormula());
    }

    public String toString(int width) {
        //if output is NOTHING (cleared)
        if (getFormula() == null) {
            return super.toString(width);
        }
        //otherwise, if there is something
        String output = String.valueOf(getVal());
        //trim a ".0"
        if (output.substring(output.length() - 2).equals(".0")) {
            output = output.substring(0, output.length() - 2);
        }
        //pad the actual stuff
        if (output.length() < width) {
            if (getAlignment().equals("left")) {
                //pad the right side
                while (output.length() < width) {
                    output += " ";
                }
            } else if (getAlignment().equals("right") || getAlignment().equals("auto")) {
                //pad the left side
                while (output.length() < width) {
                    output = " " + output;
                }
            }
        } else if (output.length() > width) {
            output = "";
            for (int i = 0; i < width; i++) {
                output += "#";
            }
        }
        return output;
    }
}
