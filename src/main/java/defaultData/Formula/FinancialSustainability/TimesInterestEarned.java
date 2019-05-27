package defaultData.Formula.FinancialSustainability;

import defaultData.EvaluationTypes;
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
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "times",
                parent));
        counter++;
        int TimesInterestEarnedexcellent = counter;
        Formulas.add(new Formula(TimesInterestEarnedexcellent,
                "excellent",
                ">=",
                "2",
                "The times interest earned ratio indicates that COMPANYNAME had no difficulty generating enough cash flow to pay interest on its debt at the end of ENDDATE. ",
                "",
                "",
                TimesInterestEarned));
        counter++;
        int TimesInterestEarnedsatisfactory = counter;
        Formulas.add(new Formula(TimesInterestEarnedsatisfactory,
                "satisfactory",
                "<=",
                "1",
                "The times interest earned ratio close to 1 indicates COMPANYNAME having difficulty generating enough cash flow to pay interest on its debt at the end of ENDDATE. Ideally, a ratio should be over 1.5. ",
                "<",
                "2",
                TimesInterestEarned));
        counter++;
        int TimesInterestEarnedbad = counter;
        Formulas.add(new Formula(TimesInterestEarnedbad,
                "bad",
                "<",
                "1",
                "The times interest earned ratio indicates that COMPANYNAME could not generate enough cash flow to pay interest on its debt at the end of ENDDATE. ",
                "",
                "",
                TimesInterestEarned));
        counter++;
        int TimesInterestEarnedIncrease = counter;
        Formulas.add(new Formula(TimesInterestEarnedIncrease,
                "periodComparison",
                "",
                "increase",
                "COMPANYNAME's ability to pay interest on debt was better in ENDDATE comparing to STARTDATE. ",
                "",
                "",
                TimesInterestEarned));
        counter++;
        int TimesInterestEarnedDecline = counter;
        Formulas.add(new Formula(TimesInterestEarnedDecline,
                "periodComparison",
                "",
                "decrease",
                "COMPANYNAME's ability to pay interest on debt was worse in ENDDATE comparing to STARTDATE.",
                "",
                "",
                TimesInterestEarned));
        counter++;
        return Formulas;
    }
}
