public class CellText extends Cell {
    public CellText(String input) {
        super(input);
    }
    public String toString(int width) {
        String output = getFormula() + "";
        return output;
    }
}
