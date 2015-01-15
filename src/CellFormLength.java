
public class CellFormLength extends CellForm {
    

    public CellFormLength(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 7).toUpperCase() + input.substring(7)), sheet, width, input.substring(7, input.length() - 1).trim());
        setIsText(false);
    }
    
    public String getValue() {
        if (testParameters(getParamList()) != null) {
            return testParameters(getParamList());
        }
        String param = getParamList()[0];
        //only takes cell references
        if (!sheet.isCellForm(param) || sheet.isRangeForm(param) || sheet.isText(param)) {
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
