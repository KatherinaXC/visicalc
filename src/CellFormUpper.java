
public class CellFormUpper extends CellForm {

    public CellFormUpper(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 6).toUpperCase() + input.substring(6)), sheet, width, input.substring(6, input.length() - 1).trim());
        setIsText(true);
    }

    public String getValue() {
        //overrides base method, does not loop at all
        //only takes cell references
        if (testParameters(getParamList()) != null) {
            return testParameters(getParamList());
        }
        String param = getParamList()[0];
        if (!sheet.isCellForm(param) || sheet.isRangeForm(param) || sheet.isText(param)) {
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
