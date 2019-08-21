package finalon.reportGeneration.interpreter.FinancialRating.Outcomes;

public class ScaleItem {
    private Double col1;
    private Double col2;
    private String col3;
    private String col4;
    private String color;

    public ScaleItem(
            Double col1,
            Double col2,
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

    public Double getCol1() {
        return col1;
    }

    public Double getCol2() {
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
