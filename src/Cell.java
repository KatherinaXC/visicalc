public class Cell {

    private String formula;
    private String alignment = "auto";

    public Cell(String input) {
        setFormula(input);
    }

    public Cell() {
        this.formula = null;
    }

    public void clear() {
        this.formula = null;
    }

    public String dump(int colwidth) {
        //Dump for empty cells says "null" right?
        return " \"Formula\" = \"" + this.formula
                + "\", \"Value\" = \"" + this.formula
                + "\", \"Alignment\" = \"" + this.alignment
                + "\", \"Width\" = \"" + colwidth + "\" ";
    }

    public void align(String input) {
        input = input.toLowerCase();
        if (input.equals("left")) {
            this.alignment = "left";
        } else if (input.equals("auto")) {
            this.alignment = "auto";
        } else if (input.equals("right")) {
            this.alignment = "right";
        }
    }

    public void setFormula(String input) {
        this.formula = input;
    }

    public String getFormula() {
        return this.formula;
    }

    public String getAlignment() {
        return this.alignment;
    }

    public String toString(int width) {
        //This class is only going to be used for blank cells anyway, so that VisiCalc doesn't crash on "dump"
        String output = "";
        for (int i = 0; i < width; i++) {
            output += " ";
        }
        return output;
    }
}
