
public class CellFormPower extends Cell {

    private String paramsection;
    private String[] paramlist;

    public CellFormPower(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 6).toUpperCase() + input.substring(6)), sheet, width);
        setIsText(false);
        this.paramsection = input.substring(6, input.length() - 1).trim();
        this.paramlist = getFormulaParameters(paramsection);
    }
    
    public String getValue() {
        //only takes numbers for BOTH
        if (!isNumber(paramlist[0]) || !isNumber(paramlist[1])) {
            return "#VALUE!";
        }
        return trimEnd("" + Math.pow(Double.parseDouble(paramlist[0]), Double.parseDouble(paramlist[1])));
    }
}
