public class CellNum extends Cell {
    
    private double value;

    public CellNum(String input) {
        super(input);
        this.value = Double.parseDouble(getFormula());
    }
    
    public String getValue() {
        return String.valueOf(this.value);
    }
    
    public String dump() {
        return " \"Input\" = \"" + getFormula()
                //so that the "value" is consistent with the displayed value
                + "\", \"Value\" = \"" + trimEnd(String.valueOf(this.value))
                + "\", \"Alignment\" = \"" + getAlignment()
                + "\", \"Width\" = \"" + getWidth() + "\" ";
    }

    public String toString() {
        String output = String.valueOf(this.value);
        //trim a ".0"
        output = trimEnd(output);
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
    
    private static String trimEnd(String input) {
        if (input.substring(input.length() - 2).equals(".0")) {
            input = input.substring(0, input.length() - 2);
        }
        return input;
    }
}
