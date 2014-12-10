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
        return " \"Formula\" = \"" + this.formula
                + "\", \"Value\" = \"" + calculate()
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

    public String calculate() {
        //TODO this method is total BS right now
        return formula;
    }

    public void setFormula(String input) {
        this.formula = input;
    }

    public String toString(int width) {
        if (this.formula == null) {
            //if it's no value
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
