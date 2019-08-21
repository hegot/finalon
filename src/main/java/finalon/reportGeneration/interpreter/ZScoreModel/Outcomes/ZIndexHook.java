package finalon.reportGeneration.interpreter.ZScoreModel.Outcomes;

import finalon.entities.Formula;

public class ZIndexHook {
    private Double firstVal;
    private Double lastVal;

    ZIndexHook(Formula formula) {
        this.firstVal = formula.getFirstVal();
        this.lastVal = formula.getLastVal();
    }

    public String getResult() {
        StringBuilder output = new StringBuilder();
        if (firstVal < 1.2 && lastVal < 1.2) {
            output.append("It also witnessed that the company did not perform well enough to have its Z-Score" +
                    " value moving out of the distress area.");
        }
        if (firstVal < 1.2 && lastVal > 1.2 && lastVal < 2.9) {
            output.append("It also witnessed that the company performed well enough to have its Z-Score value " +
                    "moving from the distress area to the grey area.");
        }
        if (firstVal < 1.2 && lastVal > 2.9) {
            output.append("It also witnessed that the company performed well enough to have " +
                    "its Z-Score value moving from the distress area to the safe area.");
        }
        if (firstVal > 1.2 && firstVal < 2.9 && lastVal < 1.2) {
            output.append("It also witnessed that the company did not perform well enough to " +
                    "keep its Z-Score value within the gray area, and so it moved to the distress area in ENDDATE. ");
        }
        if (firstVal > 1.2 && firstVal < 2.9 && lastVal > 1.2 && lastVal < 2.9) {
            output.append("It also witnessed that the company did not perform well enough to " +
                    "move its Z-Score value to the safe area, and so it stayed within the gray area in ENDDATE. ");
        }
        if (firstVal > 1.2 && firstVal < 2.9 && lastVal > 2.9) {
            output.append("It also witnessed that the company performed well enough to move " +
                    "its Z-Score value out of the gray area, and so it moved to the safe area in ENDDATE. ");
        }
        if (firstVal > 2.9 && lastVal > 1.2 && lastVal < 2.9) {
            output.append("It also witnessed that the company did not perform well enough to " +
                    "keep its Z-Score value within the safe area, and so it moved to the gray area in ENDDATE. ");
        }
        if (firstVal > 2.9 && lastVal > 2.9) {
            output.append("It also witnessed that the company performed well enough to keep " +
                    "its Z-Score value within the safe zone.");
        }


        return output.toString();
    }
}
