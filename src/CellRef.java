
public class CellRef extends Cell {

    VisiCalc sheet;

    //must have access to entire spreadsheet to be able to calculate values
    public CellRef(String input, VisiCalc sheet) {
        super(input, sheet);
    }

    public String getValue() {
        String input = getFormula();
        if (!sheet.isACell(input.trim())) {
            return "#REF!";
        }
        return sheet.getCellValue(input.trim());
    }

    public String dump() {
        return sheet.getDump(getFormula().trim());
    }

    public String toString() {
        return sheet.getValue(getFormula());
    }
}
