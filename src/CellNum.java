public class CellNum extends Cell {
    public CellNum(String input) {
        super(input);
    }
    
    public String toString(int width) {
        //TODO manage with doubles too
        String output = getFormula() + "";
        if (output.length() < width) {
            if (getAlignment().equals("left")) {
                for (int i = getAlignment().length(); i <= width; i++) {
                    output += " ";
                }
            } else if (getAlignment().equals("right") || getAlignment().equals("auto")) {
                for (int i = getAlignment().length(); i <= width; i++) {
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
