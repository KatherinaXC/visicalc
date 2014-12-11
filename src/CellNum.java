public class CellNum extends Cell {
    public CellNum(String input) {
        super(input);
        align("right");
    }
    
    public String toString(int width) {
        String output = getFormula() + "";
        if (output.length() < width) {
            if (getAlignment().equals("left")) {
                //pad the right side
                for (int i = getFormula().length(); i <= width; i++) {
                    output += " ";
                }
            } else if (getAlignment().equals("right") || getAlignment().equals("auto")) {
                //pad the left side
                for (int i = getFormula().length(); i <= width; i++) {
                    output = " " + output;
                }
            }
        } else if (output.length() > width) {
            output = "";
            for (int i = 0; i < width; i++) {
                output += "#";
            }
        }
        return output;
    }
}
