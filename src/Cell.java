
import java.util.Scanner;


class Cell {

    private String formula;
    private String alignment = "auto";
    private int width;
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
        return " \"Input\" = \"" + getFormula()
                + "\", \"Value\" = \"" + getFormula()
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

    public String getFormula() {
        return this.formula;
    }

    public String getAlignment() {
        return this.alignment;
    }

    public int getWidth() {
        return this.width;
    }

    public String getValue() {
        return null;
    }

    public String toString() {
        //This class is only going to be used for blank cells anyway, so that VisiCalc doesn't crash on "dump"
        return blankReturn(getWidth());
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
