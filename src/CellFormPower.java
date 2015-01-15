
public class CellFormPower extends CellForm {

    public CellFormPower(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 6).toUpperCase() + input.substring(6)), sheet, width, input.substring(6, input.length() - 1).trim());
        setIsText(false);
    }

    public String getValue() {
        //overrides parent method, does not loop
        String[] paramlist = getParamList();
        //only takes numbers for BOTH
        if (!sheet.isNumber(paramlist[0]) || !sheet.isNumber(paramlist[1])) {
            return "#VALUE!";
        }
        return trimEnd("" + Math.pow(Double.parseDouble(paramlist[0]), Double.parseDouble(paramlist[1])));
    }
}
