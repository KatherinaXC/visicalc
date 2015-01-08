
public class CellText extends Cell {

    public CellText(String input, VisiCalc sheet, int width) {
        super(input.substring(1, input.length() - 1), sheet, width);
    }

    public String getValue() {
        return getFormula();
    }

    public String toString() {
        //otherwise, if there is something
        String output = getFormula();
        if (output.length() < getWidth()) {
            if (getAlignment().equals("left") || getAlignment().equals("auto")) {
                //pad the right side
                while (output.length() < getWidth()) {
                    output += " ";
                }
            } else if (getAlignment().equals("right")) {
                //pad the left side
                while (output.length() < getWidth()) {
                    output = " " + output;
                }
            }
        } else if (output.length() > getWidth()) {
            //always "left" aligned, truncate right
            output = output.substring(0, getWidth());
        }
        return output;
    }
}
