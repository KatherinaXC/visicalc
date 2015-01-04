
import java.util.Scanner;

public class CellExpr extends Cell {

    VisiCalc sheet;

    //must have access to entire spreadsheet to be able to calculate values
    public CellExpr(String input, VisiCalc sheet) {
        super(input);
        this.sheet = sheet;
    }

    public String getValue() {
        String input = getFormula();
        String[] ops;
        if (input.indexOf(" + ") != -1) {
            //addition
            ops = input.split("\\+");
            ops[0] = ops[0].trim();
            ops[1] = ops[1].trim();
            if (testOperands(ops) != null) {
                return testOperands(ops);
            }
            return trimEnd(getDoubleOperands(ops[0]) + getDoubleOperands(ops[1]) + "");
        } else if (input.indexOf(" - ") != -1) {
            //subtraction
            ops = input.split("-");
            ops[0] = ops[0].trim();
            ops[1] = ops[1].trim();
            if (testOperands(ops) != null) {
                return testOperands(ops);
            }
            return trimEnd(getDoubleOperands(ops[0]) - getDoubleOperands(ops[1]) + "");
        } else if (input.indexOf(" / ") != -1) {
            //division
            ops = input.split("/");
            ops[0] = ops[0].trim();
            ops[1] = ops[1].trim();
            if (testOperands(ops) != null) {
                return testOperands(ops);
            }
            if (ops[1].equals("0") || sheet.getCellValue(ops[1]).equals("0")) {
                return "#DIV/0!";
            }
            return trimEnd(getDoubleOperands(ops[0]) / getDoubleOperands(ops[1]) + "");
        } else if (input.indexOf(" * ") != -1) {
            //multiplication
            ops = input.split("\\*");
            ops[0] = ops[0].trim();
            ops[1] = ops[1].trim();
            if (testOperands(ops) != null) {
                return testOperands(ops);
            }
            return trimEnd(getDoubleOperands(ops[0]) * getDoubleOperands(ops[1]) + "");
        } else if (input.toUpperCase().indexOf("CONCAT(") == 0) {
            //concat function
            input = input.substring(7, input.length() - 1);
            ops = input.split(",");
            //TODO
        } else if (input.toUpperCase().indexOf("COUNT(") == 0) {
            //count function
            input = input.substring(6, input.length() - 1);
            ops = input.split(",");
            //TODO
        } else if (input.toUpperCase().indexOf("SUM(") == 0) {
            //sum function
            input = input.substring(4, input.length() - 1);
            ops = input.split(",");
            //TODO
        } else if (input.toUpperCase().indexOf("UPPER(") == 0) {
            //touppercase function
            input = input.substring(6, input.length() - 1);
            ops = new String[]{input.trim().substring(1, input.length())};
            //TODO
        } else if (input.toUpperCase().indexOf("LENGTH(") == 0) {
            //length function
            input = input.substring(7, input.length() - 1);
            ops = new String[]{input.trim().substring(1, input.length())};
            //TODO
        } else if (input.toUpperCase().indexOf("POWER(") == 0) {
            //power function
            input = input.substring(6, input.length() - 1);
            ops = input.split(",");
            ops[0] = ops[0].trim();
            ops[1] = ops[1].trim();
            //TODO
        } else if (input.toUpperCase().indexOf("SQRT(") == 0) {
            //square root function
            input = input.substring(5, input.length());
            ops = new String[]{input.trim()};
            //TODO
        }
        return sheet.getCellValue(input.trim());
    }

    public String dump() {
        return " \"Input\" = \"" + getFormula()
                + "\", \"Value\" = \"" + getValue()
                + "\", \"Alignment\" = \"" + getAlignment()
                + "\", \"Width\" = \"" + getWidth() + "\" ";
    }

    private String testOperands(String[] input) {
        //returns null if the string is a valid operand
        for (String val : input) {
            if (isNumber(val)) {
                return null;
            } else if (isCellForm(val)) {
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

    private double getDoubleOperands(String input) {
        if (isNumber(input)) {
            return Double.parseDouble(input);
        }
        return Double.parseDouble(sheet.getCellValue(input));
    }
    
    private String getTextOperands(String input) {
        if (isTextForm(input)) {
            return input.substring(1, input.length() - 1);
        }
        return sheet.getCellValue(input);
    }

    private static boolean isCellForm(String input) {
        return input.length() >= 2
                && Character.isLetter(input.charAt(0))
                && isNumber(input.substring(1));
    }
    private static boolean isTextForm(String input) {
        return input.length() > 1 
                && input.charAt(0) == '"'
                && input.charAt(input.length() - 1) == '"';
    }

    private static boolean isNumber(String input) {
        Scanner read = new Scanner(input);
        return read.hasNextDouble();
    }

    public String toString() {
        //return value
        String output = getValue();
        String tempalignment = getAlignment();
        if (output.length() < getWidth()) {
            if (tempalignment.equals("auto")) {
                //test if it's number or string
                if (isNumber(output)) {
                    tempalignment = "right";
                } else {
                    tempalignment = "left";
                }
            }
            if (tempalignment.equals("left")) {
                //pad the right side
                while (output.length() < getWidth()) {
                    output += " ";
                }
            } else if (tempalignment.equals("right")) {
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

    private static String trimEnd(String input) {
        if (input.substring(input.length() - 2).equals(".0")) {
            input = input.substring(0, input.length() - 2);
        }
        return input;
    }
}
