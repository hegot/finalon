package finalon.reportGeneration.interpreter.ProfitabilityRatios.Outcomes;

import finalon.entities.Formula;

public class ReturnonEquityafterTaxHook {

    private Double firstVal;
    private Double lastVal;

    ReturnonEquityafterTaxHook(Formula formula) {
        this.firstVal = formula.getFirstVal();
        this.lastVal = formula.getLastVal();
    }

    public String getResult() {
        StringBuilder output = new StringBuilder();
        if (firstVal != null && lastVal != null) {
            if (firstVal > 0 && lastVal < 0) {
                output.append("The ROE shows that the company in STARTDATE was earning " +
                        "a profit of about STARTVALUEPERCENT cents per CURRENCY of " +
                        "investment by stockholders, but it went to a loss of about " +
                        "LASTVALUEPERCENT cents per CURRENCY in ENDDATE");
            }
            if (firstVal < 0 && lastVal > 0) {
                output.append("The ROE shows that the company in STARTDATE was having loss " +
                        "of about STARTVALUEPERCENT cents per CURRENCY of investment by " +
                        "stockholders, but it went to a profit of about LASTVALUEPERCENT cents " +
                        "per CURRENCY in ENDDATE");
            }
        }
        return output.toString();
    }
}
