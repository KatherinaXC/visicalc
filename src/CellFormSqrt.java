
public class CellFormSqrt extends CellForm {

    public CellFormSqrt(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 5).toUpperCase() + input.substring(5)), sheet, width, input.substring(5, input.length() - 1).trim());
        setIsText(false);
    }

    public String getValue() {
        //overrides base method, function does not loop
        String[] paramlist = getParamList();
        //param must be a number
        if (!sheet.isNumber(paramlist[0])) {
            return "#VALUE!";
        }
        return trimEnd("" + Math.sqrt(Double.parseDouble(paramlist[0])));
    }
}
