
public class CellFormSum extends CellForm {

    public CellFormSum(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 4).toUpperCase() + input.substring(4)), sheet, width, input.substring(4, input.length() - 1).trim());
        setIsText(false);
    }

    public double process(String input) {
        //input here is always a number-format
        return Double.parseDouble(input);
    }

}
