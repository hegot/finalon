package reportGeneration.interpreter.FinancialResults.Outcomes;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

class FrItemsGetter {
    private Item profitLossBeforeTax;
    private Item financeCosts;
    private Item grossProfit;
    private Item incomeTaxExpense;
    private ArrayList<String> periods;

    FrItemsGetter() {
        this.periods = Periods.getPeriodArr();
        this.profitLossBeforeTax = ItemsStorage.get("ProfitLossBeforeTax");
        this.financeCosts = ItemsStorage.get("FinanceCosts");
        this.grossProfit = ItemsStorage.get("GrossProfit");
        this.incomeTaxExpense = ItemsStorage.get("IncomeTaxExpenseContinuingOperations");
    }

    Item getEbit() {
        Item itemEbit = new Item(0,
                "EBIT",
                "EBIT",
                true,
                false,
                0,
                0, 0);
        ObservableMap<String, Double> valuesEbit = FXCollections.observableHashMap();
        for (String period : periods) {
            Double val1 = profitLossBeforeTax.getVal(period);
            Double val2 = financeCosts.getVal(period);
            if (val1 == null) val1 = 0.0;
            if (val2 == null) val2 = 0.0;
            Double EbitVal = val1 + val2;
            valuesEbit.put(period, EbitVal);
        }
        itemEbit.setValues(valuesEbit);
        ItemsStorage.addItem(itemEbit);
        return itemEbit;
    }

    Item getOtherIncome() {
        Item itemOtherIncome = new Item(0,
                "Other income and expenses from continuing operations, " +
                        "except finance costs and income tax expense",
                "OIEFCOEFCAITE",
                true,
                false,
                0,
                0, 0);
        ObservableMap<String, Double> valuesItemOtherIncome = FXCollections.observableHashMap();
        Double val1;
        Double val2;
        Double val3;
        Double otherIncomeVal;
        for (String period : periods) {
            val1 = profitLossBeforeTax.getVal(period);
            val2 = financeCosts.getVal(period);
            val3 = grossProfit.getVal(period);
            if (val1 == null) val1 = 0.0;
            if (val2 == null) val2 = 0.0;
            if (val3 == null) val3 = 0.0;
            otherIncomeVal = val1 + val2 - val3;
            valuesItemOtherIncome.put(period, otherIncomeVal);
        }
        itemOtherIncome.setValues(valuesItemOtherIncome);
        ItemsStorage.addItem(itemOtherIncome);
        return itemOtherIncome;
    }

    Item getIncomeLossFromContinuingOperations() {
        Item IncomeLossFromContinuingOperations = new Item(0,
                "Income (loss) from continuing operations",
                "IncomeLossFromContinuingOperations",
                true,
                false,
                0,
                0, 0);
        ObservableMap<String, Double> valuesIncomeLossFromContinuingOperations = FXCollections.observableHashMap();
        Double val1;
        Double val4;
        for (String period : periods) {
            val1 = profitLossBeforeTax.getVal(period);
            val4 = incomeTaxExpense.getVal(period);
            if (val1 == null) val1 = 0.0;
            if (val4 == null) val4 = 0.0;
            valuesIncomeLossFromContinuingOperations.put(period, val1 - val4);
        }
        IncomeLossFromContinuingOperations.setValues(valuesIncomeLossFromContinuingOperations);
        return IncomeLossFromContinuingOperations;
    }
}