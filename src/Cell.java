
public class Cell {

    private boolean isNull;
    private String formula;
    private String alignment;

    public Cell(String input) {
        setFormula(input);
    }

    public Cell() {
        this.isNull = true;
    }

    public void clear() {
        this.isNull = false;
    }

    public String dump(int colwidth) {
        return " \"Formula\" = \"" + formula
                + "\", \"Value\" = \"" + calculate()
                + "\", \"Alignment\" = \"" + alignment
                + "\", \"Width\" = \"" + colwidth + "\" ";
    }

    public String calculate() {
        //TODO this method is total BS right now
        return "answer";
    }

    public void setFormula(String input) {
        this.formula = input;
        this.isNull = false;
    }

    public String toString(int width) {
        if (isNull) {
            String output = "";
            for (int i = 0; i < width; i++) {
                output += " ";
            }
            return output;
        } else {
            //TODO this method is total bs right now just for testing purposes, change it later
            String output = "";
            for (int i = 0; i < width; i++) {
                output += "i";
            }
            return output;
        }
    }
}
