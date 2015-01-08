
public class CellForm extends Cell {

    public CellForm(String input, VisiCalc sheet, int width) {
        super(input, sheet, width);
        setAutoLeft(false);
    }

    public String getValue() {
        String input = getFormula();
        String[] ops;
        if (input.toUpperCase().indexOf("CONCAT(") == 0) {
            //concat function
            //returns string
            input = input.substring(7, input.length() - 1);
            ops = input.split(",");
            //TODO
        } else if (input.toUpperCase().indexOf("COUNT(") == 0) {
            //count function
            //returns number
            input = input.substring(6, input.length() - 1);
            ops = input.split(",");
            //TODO
        } else if (input.toUpperCase().indexOf("SUM(") == 0) {
            //sum function
            //returns number
            input = input.substring(4, input.length() - 1);
            ops = input.split(",");
            //TODO
        } else if (input.toUpperCase().indexOf("UPPER(") == 0) {
            //touppercase function
            //returns string
            input = input.substring(6, input.length() - 1);
            ops = new String[]{input.trim().substring(1, input.length())};
            //TODO
        } else if (input.toUpperCase().indexOf("LENGTH(") == 0) {
            //length function
            //returns number
            input = input.substring(7, input.length() - 1);
            ops = new String[]{input.trim().substring(1, input.length())};
            //TODO
        } else if (input.toUpperCase().indexOf("POWER(") == 0) {
            //power function
            //returns number
            input = input.substring(6, input.length() - 1);
            ops = input.split(",");
            ops[0] = ops[0].trim();
            ops[1] = ops[1].trim();
            //TODO
        } else {//(input.toUpperCase().indexOf("SQRT(") == 0)
            //square root function
            //returns number
            input = input.substring(5, input.length());
            ops = new String[]{input.trim()};
            //TODO
        }
    }
}
