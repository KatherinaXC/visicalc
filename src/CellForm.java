
import java.util.ArrayList;
import java.util.Scanner;

public class CellForm extends Cell {

    private String paramsection;
    private String[] paramlist;

    public CellForm(String input, VisiCalc sheet, int width, String paramsection) {
        super(input, sheet, width);
        setIsText(false);
        this.paramsection = paramsection;
        this.paramlist = getFormulaParameters(paramsection);
    }

    public String[] getParamList() {
        return this.paramlist;
    }

    public String getValue() {
        if (testParameters(paramlist) != null) {
            return testParameters(paramlist);
        }
        return trimEnd("" + function(paramlist));
    }

    private double function(String[] list) {
        //separate method so that it can call itself when encountering ranges
        //this method in its own class doesn't actually do calculation - uses the process() override in subclasses
        double result = 0;
        for (int i = 0; i < list.length; i++) {
            if (sheet.isNumber(list[i])) {
                //direct number
                result += process(list[i]);
            } else if (sheet.isACell(list[i]) && sheet.isNumber(sheet.getCellValue(list[i]))) {
                //a cell containing a number/reference to a number
                result += process(sheet.getCellValue(list[i]));
            } else if (sheet.isARange(list[i])) {
                //a range containing numbers/references to numbers
                String[] celllist = sheet.getIndividualCells(list[i]);
                result += function(celllist);
            }
        }
        return result;
    }

    public String testParameters(String[] list) {
        for (String val : list) {
            if (sheet.isNumber(val)) {
                return null;
            } else if (val.charAt(0) == '"') {
                return null;
            }else if (sheet.isCellForm(val) && !sheet.isACell(val)) {
                return "#REF!";
            } else if (sheet.isRangeForm(val) && !sheet.isARange(val)) {
                return "#REF!";
            } else if (sheet.isACell(val)) {
                if (sheet.getCellValue(val).charAt(0) == '#') {
                    return sheet.getCellValue(val);
                }
            } else if (sheet.isARange(val)) {
                if (testParameters(sheet.getIndividualCells(val)) != null) {
                    return testParameters(sheet.getIndividualCells(val));
                }
            } else {
                return "#VALUE!";
            }
        }
        return null;
    }

    public double process(String input) {
        //this method is not used, but it will be developed in CellFormSum and CellFormCount
        return 0;
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

}
