public class CellText extends Cell {
    public CellText(String input) {
        super(input.substring(1, input.length() - 1));
        align("left");
    }
    public String toString(int width) {
        String output = getFormula() + "";
        if (output.length() < width) {
            if (getAlignment().equals("left") || getAlignment().equals("auto")) {
                //pad the right side
                for (int i = getFormula().length(); i <= width; i++) {
                    output += " ";
                }
            } else if (getAlignment().equals("right")) {
                //pad the left side
                for (int i = getFormula().length(); i <= width; i++) {
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
