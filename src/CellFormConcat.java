
public class CellFormConcat extends Cell {

    private String paramsection;
    private String[] paramlist;

    public CellFormConcat(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 7).toUpperCase() + input.substring(7)), sheet, width);
        setIsText(true);
        this.paramsection = input.substring(7, input.length() - 1).trim();
        this.paramlist = getFormulaParameters(paramsection);
    }

    public String getValue() {
        return "\"" + concat(paramlist) + "\"";
    }

    private String concat(String[] list) {
        //separate method so that it can call itself when encountering ranges
        String output = "";
        for (int i = 0; i < list.length; i++) {
            if (sheet.isACell(list[i])) {
                //if a reference leads to a string literal
                String cellvalue = sheet.getCellValue(list[i]);
                if (isText(cellvalue)) {
                    cellvalue = cellvalue.substring(1, cellvalue.length() - 1);
                }
                output += cellvalue;
            } else if (sheet.isARange(list[i])) {
                //if it's a range and contains references to string literals
                String[] celllist = sheet.getIndividualCells(list[i]);
                output += concat(celllist);
            } else {
                if (isText(list[i])) {
                    output += list[i].substring(1, list[i].length() - 1);
                } else {
                    output += list[i];
                }
            }
        }
        return output;
    }
}
