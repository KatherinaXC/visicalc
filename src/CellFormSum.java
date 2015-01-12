
public class CellFormSum extends Cell {

    private String paramsection;
    private String[] paramlist;

    public CellFormSum(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 4).toUpperCase() + input.substring(4)), sheet, width);
        setIsText(false);
        this.paramsection = input.substring(4, input.length() - 1).trim();
        this.paramlist = getFormulaParameters(paramsection);
    }

    public String getValue() {
        //count how many numbers / number references show up
        return trimEnd("" + sum(paramlist));
    }
    
    private double sum(String[] list) {
        //separate method so that it can call itself when encountering ranges
        int sum = 0;
        for(int i = 0; i < list.length; i++) {
            if (isNumber(list[i])) {
                //direct number
                sum += Double.parseDouble(list[i]);
            } else if (sheet.isACell(list[i]) && sheet.isNumber(sheet.getCellValue(list[i]))) {
                //a cell containing a number/reference to a number
                sum += Double.parseDouble(sheet.getCellValue(list[i]));
            } else if (sheet.isARange(list[i])) {
                //a range containing numbers/references to numbers
                String[] celllist = sheet.getIndividualCells(list[i]);
                sum += sum(celllist);
            }
        }
        return sum;
    }
}
