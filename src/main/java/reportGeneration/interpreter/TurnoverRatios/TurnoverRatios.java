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
    private int weight;

    public TurnoverRatios(int weight) {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula Turnover = storage.get("Turnover");
        if (Turnover != null) {
            ResultsStorage.addStr(weight, "sectionTitle", Turnover.getName());
            weight++;
            this.weight = weight;
            this.formulas = storage.getItems(Turnover.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 10. Activity Ratios (Turnover Ratios)";
        ResultsStorage.addStr(weight, "tableName", title);
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items);
        weight++;
        box.getChildren().addAll(
                tableName(title),
                tbl,
                formulaEvaluation.get(weight)
        );
        return box;
    }
}
