package defaultData.Formula.FinancialSustainability;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TimesInterestEarned {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int TimesInterestEarned = counter;
        Formulas.add(new Formula(TimesInterestEarned,
                "Times Interest Earned",
                "TimesInterestEarned",
                "(ProfitLossBeforeTax-FinanceCosts-GainsLossesOnNetMonetaryPosition)/FinanceCosts",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int TimesInterestEarnedexcellent = counter;
        Formulas.add(new Formula(TimesInterestEarnedexcellent,
                "excellent",
                ">=",
                "2",
                "",
                "",
                "",
                TimesInterestEarned));
        counter++;
        int TimesInterestEarnedsatisfactory = counter;
        Formulas.add(new Formula(TimesInterestEarnedsatisfactory,
                "satisfactory",
                "<=",
                "1",
                "",
                "<",
                "2",
                TimesInterestEarned));
        counter++;
        int TimesInterestEarnedbad = counter;
        Formulas.add(new Formula(TimesInterestEarnedbad,
                "bad",
                "<",
                "1",
                "",
                "",
                "",
                TimesInterestEarned));
        counter++;
        return Formulas;
    }
}
