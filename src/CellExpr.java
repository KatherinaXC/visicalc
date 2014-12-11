public class CellExpr extends Cell{
    public CellExpr(String input) {
        super(input);
    }
    public String toString(int width) {
        String output = getFormula() + "";
        return output;
    }
}
