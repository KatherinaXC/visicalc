
public class CellRef extends Cell {
    public CellRef(String input, VisiCalc sheet, int width) {
        super(input, sheet, width);
        if (getFormula().charAt(0) == '"' && getFormula().charAt(getFormula().length() - 1) == '"') {
            setAutoLeft(true);
        }
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
            return "#REF!";
        }
        if (!sheet.isFilled(getFormula())) {
            return "#VALUE!";
        }
        return sheet.getValue(getFormula());
    }
}
