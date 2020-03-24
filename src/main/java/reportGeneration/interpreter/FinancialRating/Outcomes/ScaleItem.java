package reportGeneration.interpreter.FinancialRating.Outcomes;

public class ScaleItem {
    private String col1;
    private String col2;
    private String col3;
    private String col4;
    private String color;

    public ScaleItem(
            String col1,
            String col2,
            String col3,
            String col4,
            String color
    ) {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.col4 = col4;
        this.color = color;
    }

    public String getCol1() {
        return col1;
    }

    public String getCol2() {
        return col2;
    }

    public String getCol3() {
        return col3;
    }

    public String getCol4() {
        return col4;
    }

    public String getColor() {
        return color;
    }
}
