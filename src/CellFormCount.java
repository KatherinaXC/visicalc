
public class CellFormCount extends CellForm {

    public CellFormCount(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 6).toUpperCase() + input.substring(6)), sheet, width, input.substring(6, input.length() - 1).trim());
        setIsText(false);
    }

    public double process(String input) {
        //this method is used to increment the result
        return 1;
    }
}
