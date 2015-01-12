
public class CellRef extends Cell {

    public CellRef(String input, VisiCalc sheet, int width) {
        super(input, sheet, width);
    }

    public String getValue() {
        if (!sheet.isACell(getFormula())) {
            return "#REF!";
        }
        if (!sheet.isFilled(getFormula())) {
            return "#VALUE!";
        }
        return sheet.getCellValue(getFormula());
    }

    public String toString() {
        if (!sheet.isACell(getFormula())) {
            return "#REF!" + blankReturn(getWidth() - 5);
        }
        if (!sheet.isFilled(getFormula())) {
            return "#VALUE!" + blankReturn(getWidth() - 7);
        }
        return sheet.getValue(getFormula());
    }
}
