
public class CellBin extends CellNum {

    private String[] operands;
    private double[] opints = new double[2];
    private char operator;

    public CellBin(String input, VisiCalc sheet, int width) {
        super(input, sheet, width);
        String[] ops = input.split(" ");
        this.operands = new String[]{ops[0], ops[2]};
        this.operator = ops[1].charAt(0);
    }

    public String getValue() {
        if (testOperands(operands) != null) {
            return testOperands(operands);
        }
        setIsText(false);
        if (operator == '+') {
            return calcAdd();
        } else if (operator == '-') {
            return calcSubtract();
        } else if (operator == '*') {
            return calcMultiply();
        } else { //if operator == '/'
            return calcDivide();
        }
    }

    private String calcAdd() {
        for (int i = 0; i < operands.length; i++) {
            if (sheet.isNumber(operands[i])) {
                opints[i] = Double.parseDouble(operands[i]);
            } else {
                //if a cell reference
                if (!sheet.isNumber(sheet.getCellValue(operands[i]))) {
                    return sheet.getCellValue(operands[i]);
                }
                opints[i] = Double.parseDouble(sheet.getCellValue(operands[i]));
            }
        }
        return trimEnd(String.valueOf(opints[0] + opints[1]));
    }

    private String calcSubtract() {
        for (int i = 0; i < operands.length; i++) {
            if (sheet.isNumber(operands[i])) {
                opints[i] = Double.parseDouble(operands[i]);
            } else {
                //if a cell reference
                if (!sheet.isACell(sheet.getCellValue(operands[i]))) {
                    return sheet.getCellValue(operands[i]);
                }
                opints[i] = Double.parseDouble(sheet.getCellValue(operands[i]));
            }
        }
        return trimEnd(String.valueOf(opints[0] - opints[1]));
    }

    private String calcMultiply() {
        for (int i = 0; i < operands.length; i++) {
            if (sheet.isNumber(operands[i])) {
                opints[i] = Double.parseDouble(operands[i]);
            } else {
                //if a cell reference
                if (sheet.isACell(sheet.getCellValue(operands[i]))) {
                    return sheet.getCellValue(operands[i]);
                }
                opints[i] = Double.parseDouble(sheet.getCellValue(operands[i]));
            }
        }
        return trimEnd(String.valueOf(opints[0] * opints[1]));
    }

    private String calcDivide() {
        for (int i = 0; i < operands.length; i++) {
            if (sheet.isNumber(operands[i])) {
                opints[i] = Double.parseDouble(operands[i]);
            } else {
                //if a cell reference
                if (sheet.isACell(sheet.getCellValue(operands[i]))) {
                    return sheet.getCellValue(operands[i]);
                }
                opints[i] = Double.parseDouble(sheet.getCellValue(operands[i]));
            }
        }
        if (trimEnd(String.valueOf(opints[1])).equals("0")) {
            return "#DIV/0!";
        }
        return trimEnd(String.valueOf(opints[0] / opints[1]));
    }

    public String testOperands(String[] input) {
        //returns null if the string is a valid operand
        //This one is stricter than its parent... it doesn't allow ranges
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
                } else if (!sheet.isNumber(sheet.getCellValue(val))) {
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

}
