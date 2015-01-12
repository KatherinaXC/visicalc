
public class CellFormLength extends Cell {
    
    private String param;

    public CellFormLength(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 7).toUpperCase() + input.substring(7)), sheet, width);
        setIsText(false);
        this.param = input.substring(7, input.length() - 1).trim();
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
        return trimEnd("" + sheet.getCellValue(param).length());
    }
}
