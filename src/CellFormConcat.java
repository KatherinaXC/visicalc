
public class CellFormConcat extends CellForm {

    public CellFormConcat(String input, VisiCalc sheet, int width) {
        super((input.substring(0, 7).toUpperCase() + input.substring(7)), sheet, width, input.substring(7, input.length() - 1).trim());
        setIsText(true);
    }

    public String getValue() {
        //this is the only method that returns a string so override
        return "\"" + concat(getParamList()) + "\"";
    }

    private String concat(String[] list) {
        //separate method so that it can call itself when encountering ranges
        String output = "";
        for (int i = 0; i < list.length; i++) {
            if (sheet.isACell(list[i])) {
                //if a reference leads to a string literal
                String cellvalue = sheet.getCellValue(list[i]);
                if (sheet.isText(cellvalue)) {
                    cellvalue = cellvalue.substring(1, cellvalue.length() - 1);
                }
                output += cellvalue;
            } else if (sheet.isARange(list[i])) {
                //if it's a range and contains references to string literals
                String[] celllist = sheet.getIndividualCells(list[i]);
                output += concat(celllist);
            } else {
                if (sheet.isText(list[i])) {
                    output += list[i].substring(1, list[i].length() - 1);
                } else {
                    output += list[i];
                }
            }
        }
        return output;
    }
}
