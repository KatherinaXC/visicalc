
import java.util.ArrayList;
import java.util.Scanner;

class Cell {

    private String formula;
    private String alignment = "auto";
    private int width;
    private boolean istext = false;
    VisiCalc sheet;

    public Cell(String input, VisiCalc sheet, int width) {
        this.formula = input;
        this.sheet = sheet;
        this.width = width;
    }

    public Cell() {
        this("", null, 10);
    }

    public String getDump() {
        String value = getValue();
        if (value == null) {
            value = "";
        }
        if (value.length() > 2 && value.charAt(0) == '"') {
            value = value.substring(1, value.length() - 1);
        }
        return " \"Input\" = \"" + getFormula()
                + "\", \"Value\" = \"" + value
                + "\", \"Alignment\" = \"" + getAlignment()
                + "\", \"Width\" = \"" + getWidth() + "\" ";
    }

    public void setAlign(String input) {
        input = input.toLowerCase();
        if (input.equals("left")) {
            this.alignment = "left";
        } else if (input.equals("auto")) {
            this.alignment = "auto";
        } else if (input.equals("right")) {
            this.alignment = "right";
        }
    }

    public void setWidth(int width) {
        if (width < 0) {
            width = 0;
        } else if (width > 20) {
            width = 20;
        }
        this.width = width;
    }

    public void setIsText(boolean istext) {
        this.istext = istext;
    }

    public String getFormula() {
        return this.formula;
    }

    public String getAlignment() {
        return this.alignment;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean getIsText() {
        return istext;
    }

    public String getValue() {
        return null;
    }

    public String toString() {
        //in Cell class always run this
        if (sheet == null) {
            return blankReturn(getWidth());
        }
        //its subclasses will run this
        String output = getValue();
        if (sheet.isText(output)) {
            output = output.substring(1, output.length() - 1);
        }
        output = trimEnd(output);
        if (output.length() < getWidth()) {
            if (getAlignment().equals("left") || (getAlignment().equals("auto") && getIsText()) || output.charAt(0) == '#') {
                //pad the right side
                while (output.length() < getWidth()) {
                    output += " ";
                }
            } else if (getAlignment().equals("right") || (getAlignment().equals("auto") && !getIsText())) {
                //pad the left side
                while (output.length() < getWidth()) {
                    output = " " + output;
                }
            }
        } else if (output.length() > getWidth()) {
            if (getIsText() || output.charAt(0) == '#') {
                //if it is auto left, then it's text and should truncate
                output = output.substring(0, getWidth());
            } else {
                //if not, it's a number and should use ####
                output = "";
                for (int i = 0; i < getWidth(); i++) {
                    output += "#";
                }
            }
        }
        return output;
    }

    public static String blankReturn(int length) {
        //just for simplification of adding blanks of a certain length
        String out = "";
        for (int i = 0; i < length; i++) {
            out += " ";
        }
        return out;
    }

    public static String trimEnd(String input) {
        if (input.length() < 3) {
            return input;
        }
        if (input.substring(input.length() - 2).equals(".0")) {
            input = input.substring(0, input.length() - 2);
        }
        return input;
    }
}
