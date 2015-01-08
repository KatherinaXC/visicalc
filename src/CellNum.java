
public class CellNum extends Cell {

    public CellNum(String input, VisiCalc sheet, int width) {
        //the sheet isn't actually used in this class, as this is a static cell
        super(input, sheet, width);
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

    public static String trimEnd(String input) {
        if (input.substring(input.length() - 2).equals(".0")) {
            input = input.substring(0, input.length() - 2);
        }
        return input;
    }
}
