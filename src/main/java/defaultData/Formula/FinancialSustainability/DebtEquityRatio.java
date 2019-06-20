package defaultData.Formula.FinancialSustainability;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DebtEquityRatio {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int DebtEquityRatio = counter;
        Formulas.add(new Formula(DebtEquityRatio,
                "Debt/Equity Ratio",
                "DebtEquityRatio",
                "(NonCurrentAssets+CurrentLiabilities)/EquityGeneral",
               "",
                "formula",
                "",
                parent));
        counter++;
        int DebtEquityRatioPrefix = counter;
        Formulas.add(new Formula(DebtEquityRatioPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The debt/equity ratio is another computation that determines " +
                        "the entity's long-term debt-paying ability. " +
                        "At the end of ENDDATE this ratio was LASTVALUE%, ",
                "",
                "",
                DebtEquityRatio));
        counter++;
        int DebtEquityRatioGood = counter;
        Formulas.add(new Formula(DebtEquityRatioGood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "1.5",
                "meaning that creditors were protected in case of insolvency. ",
                "",
                "",
                DebtEquityRatio));
        counter++;
        int DebtEquityRatioBad = counter;
        Formulas.add(new Formula(DebtEquityRatioBad,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "1.5",
                "meaning that creditors were not protected in case of insolvency. ",
                "",
                "",
                DebtEquityRatio));
        counter++;
        return Formulas;
    }
}
