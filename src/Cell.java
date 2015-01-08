
import java.util.Scanner;

class Cell {

    private String formula;
    private String alignment = "auto";
    private int width;
    private boolean autoleft = false;
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
        if (getValue() == null) {
            return " \"Input\" = \"" + getFormula()
                    + "\", \"Value\" = \"" + getFormula()
                    + "\", \"Alignment\" = \"" + getAlignment()
                    + "\", \"Width\" = \"" + getWidth() + "\" ";
        }
        return " \"Input\" = \"" + getFormula()
                + "\", \"Value\" = \"" + getValue()
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

    public void setAutoLeft(boolean autoleft) {
        this.autoleft = autoleft;
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

    public boolean getAutoLeft() {
        return autoleft;
    }

    public String getValue() {
        return null;
    }

    public String toString() {
        //in Cell class always run this
        if (sheet == null) {
            return blankReturn(getWidth());
        }
        //the rest of its subclasses
        String output = getFormula();
        if (output.charAt(0) == '"' && output.charAt(output.length() - 1) == '"') {
            output = output.substring(1, getFormula().length() - 1);
        }
        if (output.length() < getWidth()) {
            if (getAlignment().equals("left") || (getAlignment().equals("auto") && getAutoLeft() == true)) {
                //pad the right side
                while (output.length() < getWidth()) {
                    output += " ";
                }
            } else if (getAlignment().equals("right") || (getAlignment().equals("auto") && getAutoLeft() == false)) {
                //pad the left side
                while (output.length() < getWidth()) {
                    output = " " + output;
                }
            }
        } else if (output.length() > getWidth()) {
            if (getAutoLeft()) {
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
        String out = "";
        for (int i = 0; i < length; i++) {
            out += " ";
        }
        return out;
    }

    public String testOperands(String[] input) {
        //returns null if the string is a valid operand
        for (String val : input) {
            if (!Character.isLetter(val.charAt(0))) {
                return null;
            } else if (sheet.isCellForm(val)) {
                if (!sheet.isACell(val)) {
                    return "#REF!";
                } else if (!sheet.isFilled(val)) {
                    return "#VALUE!";
                } else if (sheet.getCellValue(val).equals("#DIV/0!")) {
                    return "#DIV/0!";
                } else if (sheet.getCellValue(val).equals("#REF!")) {
                    return "#REF!";
                } else if (sheet.getCellValue(val).equals("#VALUE!")) {
                    return "#VALUE!";
                } else if (!isNumber(sheet.getCellValue(val))) {
                    return "#VALUE!";
                } else {
                    return null;
                }
            } else {
                return "#VALUE!";
            }
        }
        return null;
    }

    public static boolean isNumber(String input) {
        Scanner read = new Scanner(input);
        return read.hasNextDouble();
    }
}
