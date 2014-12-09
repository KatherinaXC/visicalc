public class Cell {
    private String formula;
    private String alignment;
    
    public Cell(String input) {
        setFormula(input);
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
    }
    
    public String toString(int width) {
        //TODO this method is total bs right now just for testing purposes, change it later
        String output = "";
        for (int i = 0; i < width; i++) {
            output += "i";
        }
        return output;
    }
}
