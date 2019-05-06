package defaultData.Formula.Liquidity;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuickRatio {

    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int QuickRatio = counter;
        Formulas.add(new Formula(QuickRatio,
                "Quick Ratio (Acid Test Ratio)",
                "QuickRatio",
                "(CashAndCashEquivalents+TradeAndOtherCurrentReceivables)/CurrentLiabilities",
                "",
                "formula",
                "",
                parent));
        counter++;
        int CurrentRatiosatisfactory = counter;
        Formulas.add(new Formula(CurrentRatiosatisfactory,
                "excellent",
                ">=",
                "1",
                "The company's quick liquidity was satisfactory at the end of the period.",
                "",
                "",
                QuickRatio));
        counter++;
        int CurrentRatioexcellent = counter;
        Formulas.add(new Formula(CurrentRatioexcellent,
                "good",
                "<",
                "1",
                "The company's quick liquidity was unsatisfactory at the end of the period.",
                "<=",
                "0",
                QuickRatio));
        return Formulas;
    }
}
