
public class CellText extends Cell {

    public CellText(String input, VisiCalc sheet, int width) {
        super(input.substring(1, input.length() - 1), sheet, width);
        setAutoLeft(true);
    }

    public String getValue() {
        return "\"" + getFormula() + "\"";
    }
}
