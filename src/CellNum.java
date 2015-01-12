
public class CellNum extends Cell {

    public CellNum(String input, VisiCalc sheet, int width) {
        //the sheet isn't actually used in this class, as this is a static cell
        super(input, sheet, width);
    }

    public String getValue() {
        return trimEnd(String.valueOf(Double.parseDouble(getFormula())));
    }
}
