package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalestoFixedAssets {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int SalestoFixedAssets = counter;
        Formulas.add(new Formula(SalestoFixedAssets,
                "Sales to Fixed Assets",
                "SalestoFixedAssets",
                "RevenueGeneral/(PropertyPlantAndEquipment[0]/2+PropertyPlantAndEquipment[1]/2)",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int SalestoFixedAssets1 = counter;
        Formulas.add(new Formula(SalestoFixedAssets1,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The fixed assets turned over LASTVALUE times in ENDDATE. ",
                "",
                "",
                SalestoFixedAssets));
        counter++;
        int SalestoFixedAssetsSuffix = counter;
        Formulas.add(new Formula(SalestoFixedAssetsSuffix,
                EvaluationTypes.SUFFIX.toString(),
                "",
                "",
                "Within one period, on average, the current assets are turned over at least AVERAGEVALUE times.",
                "",
                "",
                SalestoFixedAssets));
        return Formulas;
    }
}
