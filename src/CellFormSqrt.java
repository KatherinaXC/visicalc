
public class CellFormSqrt extends Cell {

    private String paramsection;
    private String[] paramlist;

    public CellFormSqrt(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 5).toUpperCase() + input.substring(5)), sheet, width);
        setIsText(false);
        this.paramsection = input.substring(5, input.length() - 1).trim();
        this.paramlist = getFormulaParameters(paramsection);
    }
    
    public String getValue() {
        //param must be a number
        if (!isNumber(paramlist[0])) {
            return "#VALUE!";
        }
        return trimEnd("" + Math.sqrt(Double.parseDouble(paramlist[0])));
    }
}
