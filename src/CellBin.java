
public class CellBin extends CellNum {

    private String[] operands;
    private double[] opints = new double[2];
    private char operator;

    public CellBin(String input, VisiCalc sheet) {
        super(input, sheet);
        String[] ops = input.split(" ");
        this.operands = new String[]{ops[0], ops[2]};
        this.operator = ops[1].charAt(0);
    }

    public String getValue() {
        String error = testOperands(operands);
        if (error != null) {
            return error;
        }
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
            if (isNumber(operands[i])) {
                opints[i] = Double.parseDouble(operands[i]);
            } else {
                //if a cell reference
                if (!isNumber(sheet.getCellValue(operands[i]))) {
                    return sheet.getCellValue(operands[i]);
                }
                opints[i] = Double.parseDouble(sheet.getCellValue(operands[i]));
            }
        }
        return trimEnd(String.valueOf(opints[0] + opints[1]));
    }

    private String calcSubtract() {
        for (int i = 0; i < operands.length; i++) {
            if (isNumber(operands[i])) {
                opints[i] = Double.parseDouble(operands[i]);
            } else {
                //if a cell reference
                if (!isNumber(sheet.getCellValue(operands[i]))) {
                    return sheet.getCellValue(operands[i]);
                }
                opints[i] = Double.parseDouble(sheet.getCellValue(operands[i]));
            }
        }
        return trimEnd(String.valueOf(opints[0] - opints[1]));
    }

    private String calcMultiply() {
        for (int i = 0; i < operands.length; i++) {
            if (isNumber(operands[i])) {
                opints[i] = Double.parseDouble(operands[i]);
            } else {
                //if a cell reference
                if (!isNumber(sheet.getCellValue(operands[i]))) {
                    return sheet.getCellValue(operands[i]);
                }
                opints[i] = Double.parseDouble(sheet.getCellValue(operands[i]));
            }
        }
        return trimEnd(String.valueOf(opints[0] * opints[1]));
    }

    private String calcDivide() {
        for (int i = 0; i < operands.length; i++) {
            if (isNumber(operands[i])) {
                opints[i] = Double.parseDouble(operands[i]);
            } else {
                //if a cell reference
                if (!isNumber(sheet.getCellValue(operands[i]))) {
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

}
