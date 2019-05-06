package defaultData.Formula.Liquidity;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetWorkingCapital {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int NetWorkingCapital = counter;
        Formulas.add(new Formula(NetWorkingCapital,
                "Net Working Capital",
                "NetWorkingCapital",
                "GeneralCurrentAssets-CurrentLiabilities",
                "",
                "formula",
                "money (tousand dollar)",
                parent));
        counter++;
        int NetWorkingCapitalExcellent = counter;
        Formulas.add(new Formula(NetWorkingCapitalExcellent,
                "good",
                ">",
                "0",
                "The working capital value was positive at the end of the period under review, meaning greater flexibility, since current assets may be modified easily as the sales volume changes. ",
                "",
                "",
                NetWorkingCapital));
        counter++;
        int NetWorkingCapitalSatisfactory = counter;
        Formulas.add(new Formula(NetWorkingCapitalSatisfactory,
                "satisfactory",
                "=",
                "0",
                "There was no working capital at the end of the period under review, meaning low flexibility, since noncurrent assets may not be modified easily as the sales volume changes.",
                "",
                "",
                NetWorkingCapital));
        counter++;
        int NetWorkingCapitalBad = counter;
        Formulas.add(new Formula(NetWorkingCapitalBad,
                "bad",
                "<",
                "0",
                "The working capital value was negative at the end of the period under review, meaning low flexibility, since noncurrent assets may not be modified easily as the sales volume changes.",
                "",
                "",
                NetWorkingCapital));
        counter++;
        return Formulas;
    }
}
