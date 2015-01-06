public class Cell {

    private String formula;
    private String alignment = "auto";
    private int width = 10;
    VisiCalc sheet;

    public Cell(String input, VisiCalc sheet) {
        setFormula(input);
        this.sheet = sheet;
    }

    public Cell() {
        this.formula = null;
    }

    public String dump() {
        return " \"Input\" = \"" + getFormula()
                + "\", \"Value\" = \"" + getFormula()
                + "\", \"Alignment\" = \"" + getAlignment()
                + "\", \"Width\" = \"" + getWidth() + "\" ";
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
    
    public void setWidth(int width) {
        if (width < 0) {
            width = 0;
        } else if (width > 20) {
            width = 20;
        }
        this.width = width;
    }

    public String getFormula() {
        return this.formula;
    }

    public String getAlignment() {
        return this.alignment;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public String getValue() {
        return null;
    }

    public String toString() {
        //This class is only going to be used for blank cells anyway, so that VisiCalc doesn't crash on "dump"
        return blankReturn(getWidth());
    }
    
    public static String blankReturn(int length) {
        String out = "";
        for (int i = 0; i < length; i++) {
            out += " ";
        }
        return out;
    }
}
