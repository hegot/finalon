package reportGeneration.interpreter.ZScoreModel;

import entities.Formula;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.interpreter.ZScoreModel.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ZScoreModel.Outcomes.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ZScoreModel {
    private ObservableList<Formula> formulas;
    private int weight;

    public ZScoreModel(int weight) {
        Formula ZscoreModel = FormulaStorage.get("ZscoreModel");
        if (ZscoreModel != null) {
            ResultsStorage.addStr(weight, "sectionTitle", ZscoreModel.getName());
            weight++;
            this.weight = weight;
            this.formulas = FormulaStorage.getItems(ZscoreModel.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 12. The Z-Score Model for Private Firms ";
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = TableName.getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items, title);
        weight++;
        box.getChildren().addAll(
                TableName.name(title),
                tbl,
                formulaEvaluation.get(weight)
        );
        return box;
    }
}
