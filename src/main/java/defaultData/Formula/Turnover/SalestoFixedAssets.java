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
                EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString(),
                "formula",
                "times",
                parent));
        counter++;
        int SalestoFixedAssets1 = counter;
        Formulas.add(new Formula(SalestoFixedAssets1,
                EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString(),
                "",
                "",
                "The fixed assets turned over CURRENTVALUE times in CURRENTPERIOD. ",
                "",
                "",
                SalestoFixedAssets));
        counter++;
        int SalestoFixedAssets2 = counter;
        Formulas.add(new Formula(SalestoFixedAssets2,
                EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString(),
                "",
                "",
                "In fixed assets CURRENTPERIOD turned CURRENTVALUE. ",
                "",
                "",
                SalestoFixedAssets));
        counter++;
        int SalestoFixedAssets3 = counter;
        Formulas.add(new Formula(SalestoFixedAssets3,
                EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString(),
                "",
                "",
                "CURRENTVALUE times fixed assets turned over in CURRENTPERIOD. ",
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
