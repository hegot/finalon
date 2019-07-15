package reportGeneration.interpreter.TurnoverRatios;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.interpreter.TurnoverRatios.Outcomes.FormulaEvaluation;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class TurnoverRatios implements TableName {
    private ObservableList<Formula> formulas;

    public TurnoverRatios() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula FS = storage.get("T");
        if (FS != null) {
            this.formulas = storage.getItems(FS.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 10. Activity Ratios (Turnover Ratios)";
        ResultsStorage.addStr(57, "h2", title);
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(58, items);

        box.getChildren().addAll(
                tableName(title),
                tbl,
                formulaEvaluation.get()
        );
        return box;
    }
}
