
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
        if (isText(output)) {
            output = output.substring(1, output.length() - 1);
        }
        output = trimEnd(output);
        if (output.length() < getWidth()) {
            if (getAlignment().equals("left") || (getAlignment().equals("auto") && getIsText())) {
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
            if (getIsText()) {
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

    public static String trimEnd(String input) {
        if (input.length() < 3) {
            return input;
        }
        if (input.substring(input.length() - 2).equals(".0")) {
            input = input.substring(0, input.length() - 2);
        }
        return input;
    }

    public static String[] getFormulaParameters(String input) {
        //Takes a set of formula-parameters and reads them
        input = input.trim();
        ArrayList<String> paramlist = new ArrayList<String>();
        boolean currentlyinstring = false;
        int lastquote = -1;
        int lastcomma = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '"' && !currentlyinstring) {
                //mark start of string
                currentlyinstring = true;
                lastquote = i;
            } else if (input.charAt(i) == '"' && currentlyinstring) {
                //mark end of string and add it
                currentlyinstring = false;
                paramlist.add(input.substring(lastquote, i + 1));
            } else if (input.charAt(i) == ',' && !currentlyinstring) {
                //set last comma point, only if not in a string
                lastcomma = i;
            } else if ((lastcomma == i - 2 || i == 0) && !currentlyinstring) {
                //if the reader is on a space AFTER the comma, and it's not a quote (cleared earlier)
                //then read the token (takes in as number or cell or range)
                Scanner readparam = new Scanner(input.substring(i));
                String nexttoken = readparam.next();
                if (nexttoken.charAt(nexttoken.length() - 1) == ',') {
                    nexttoken = nexttoken.substring(0, nexttoken.length() - 1);
                }
                paramlist.add(nexttoken);
                i += paramlist.get(paramlist.size() - 1).length() - 1;
            }
        }
        //save the arraylist to an array
        String[] output = new String[paramlist.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = paramlist.get(i);
        }
        return output;
    }

    public static boolean isNumber(String input) {
        Scanner read = new Scanner(input);
        return read.hasNextDouble();
    }

    public static boolean isText(String input) {
        return input.length() > 1
                && input.charAt(0) == '"'
                && input.charAt(input.length() - 1) == '"';
    }
}
