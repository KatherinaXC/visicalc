
public class CellFormCount extends Cell {

    private String paramsection;
    private String[] paramlist;

    public CellFormCount(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 6).toUpperCase() + input.substring(6)), sheet, width);
        setIsText(false);
        this.paramsection = input.substring(6, input.length() - 1).trim();
        this.paramlist = getFormulaParameters(paramsection);
    }

    public String getValue() {
        //count how many numbers / number references show up
        return trimEnd("" + count(paramlist));
    }
    
    private int count(String[] list) {
        //separate method so that it can call itself when encountering ranges
        int count = 0;
        for(int i = 0; i < list.length; i++) {
            if (isNumber(list[i])) {
                //direct number
                count++;
            } else if (sheet.isACell(list[i]) && sheet.isNumber(sheet.getCellValue(list[i]))) {
                //a cell containing a number/reference to a number
                count++;
            } else if (sheet.isARange(list[i])) {
                //a range containing numbers/references to numbers
                String[] celllist = sheet.getIndividualCells(list[i]);
                count += count(celllist);
            }
        }
        return count;
    }
}
