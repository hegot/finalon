package reportGeneration.interpreter.ZScoreModel;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ZScoreModel.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ZScoreModel.Outcomes.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class ZScoreModel implements TableName {
    private ObservableList<Formula> formulas;

    public ZScoreModel() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula AZS = storage.get("AZS");
        if (AZS != null) {
            this.formulas = storage.getItems(AZS.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 12. The Z-Score Model for Private Firms ";
        ResultsStorage.addStr(63, "h2", title);
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(64, items);

        box.getChildren().addAll(
                tableName(title),
                tbl,
                formulaEvaluation.get()
        );
        return box;
    }
}
