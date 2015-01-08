
public class CellNum extends Cell {

    public CellNum(String input, VisiCalc sheet) {
        //the sheet isn't actually used in this class, as this is a static cell
        super(input, sheet);
    }

    public String getDump() {
        return " \"Input\" = \"" + getFormula()
                //so that the "value" is consistent with the displayed value
                + "\", \"Value\" = \"" + getValue()
                + "\", \"Alignment\" = \"" + getAlignment()
                + "\", \"Width\" = \"" + getWidth() + "\" ";
    }

    public String getValue() {
        return trimEnd(String.valueOf(Double.parseDouble(getFormula())));
    }

    public String toString() {
        String output = getValue();
        //pad the actual stuff
        if (output.length() < getWidth()) {
            if (getAlignment().equals("left")) {
                //pad the right side
                while (output.length() < getWidth()) {
                    output += " ";
                }
            } else if (getAlignment().equals("right") || getAlignment().equals("auto")) {
                //pad the left side
                while (output.length() < getWidth()) {
                    output = " " + output;
                }
            }
        } else if (output.length() > getWidth()) {
            output = "";
            for (int i = 0; i < getWidth(); i++) {
                output += "#";
            }
        }
        return output;
    }

    public static String trimEnd(String input) {
        if (input.substring(input.length() - 2).equals(".0")) {
            input = input.substring(0, input.length() - 2);
        }
        return input;
    }
}
