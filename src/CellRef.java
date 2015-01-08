
public class CellRef extends Cell {
    public CellRef(String input, VisiCalc sheet, int width) {
        super(input, sheet, width);
        if (getFormula().charAt(0) == '"' && getFormula().charAt(getFormula().length() - 1) == '"') {
            setAutoLeft(true);
        }
    }
    
    public String getValue() {
        String input = getFormula();
        if (!sheet.isACell(input.trim())) {
            return "#REF!";
        }
        return sheet.getCellValue(input.trim());
    }
}
