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
        Formula ZscoreModel = storage.get("ZscoreModel");
        if (ZscoreModel != null) {
            ResultsStorage.addStr(100, "sectionTitle", ZscoreModel.getName());
            this.formulas = storage.getItems(ZscoreModel.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 12. The Z-Score Model for Private Firms ";
        ResultsStorage.addStr(101, "tableName", title);
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(102, items);

        box.getChildren().addAll(
                tableName(title),
                tbl,
                formulaEvaluation.get()
        );
        return box;
    }
}
