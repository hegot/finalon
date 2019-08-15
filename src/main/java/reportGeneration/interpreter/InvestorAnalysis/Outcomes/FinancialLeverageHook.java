package reportGeneration.interpreter.InvestorAnalysis.Outcomes;

import entities.Formula;
import entities.Item;
import reportGeneration.storage.ItemsStorage;

public class FinancialLeverageHook {
    private Double lastVal;
    private Double financeCostsVal;
    private Double profitLossFromOperatingActivitiesVal;
    private Double financeIncomeVal;


    FinancialLeverageHook(Formula formula) {
        this.lastVal = formula.getLastVal();
        this.financeCostsVal = getLastVal("FinanceCosts");
        this.profitLossFromOperatingActivitiesVal = getLastVal("ProfitLossFromOperatingActivities");
        this.financeIncomeVal = getLastVal("FinanceIncome");
    }


    private Double getLastVal(String code) {
        Item item = ItemsStorage.get(code);
        if (item != null) {
            Double val = item.getLastVal();
            if (val != null) {
                return val;
            }
        }
        return 0.0;
    }

    public String getResult() {
        if (lastVal != null) {
            StringBuilder output = new StringBuilder();
            Double sum = financeCostsVal + profitLossFromOperatingActivitiesVal + financeIncomeVal;
            if (lastVal > 0 && financeCostsVal != 0) {
                output.append("If earnings before interest increase, the financial leverage will be favorable. " +
                        "The degree of financial leverage was " + lastVal + " in ENDDATE. " +
                        "This means that any change in the EBIT will be accompanied by " + lastVal + " times that change in the net income. ");
            }
            if (lastVal > 1 && sum > 0 && lastVal < 2) {
                output.append("Company showed low degree of financial leverage in ENDDATE. ");
            }
            if (lastVal >= 2 && sum > 0 && lastVal < 2) {
                output.append("Company showed high degree of financial leverage in ENDDATE. ");
            }
            return output.toString();
        }
        return "";
    }
}