
public class CellFormUpper extends Cell {

    private String param;

    public CellFormUpper(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 6).toUpperCase() + input.substring(6)), sheet, width);
        setIsText(true);
        this.param = input.substring(6, input.length() - 1).trim();
    }

    public String getValue() {
        //only takes cell references
        if (!sheet.isCellForm(param)) {
            //if not a reference
            return "#VALUE!";
        }
        if (!sheet.isACell(param)) {
            //if reference out of bounds
            return "#REF!";
        }
        return sheet.getCellValue(param).toUpperCase();
    }
}
