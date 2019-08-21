package finalon.reportGeneration.interpreter.TurnoverRatios;

import finalon.entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.interpreter.TurnoverRatios.Outcomes.FormulaEvaluation;
import finalon.reportGeneration.interpreter.TurnoverRatios.Outcomes.RatiosTable;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.TwoDList;

public class TurnoverRatios implements TableName {
    private ObservableList<Formula> formulas;
    private int weight;

    public TurnoverRatios(int weight) {
        Formula Turnover = FormulaStorage.get("Turnover");
        if (Turnover != null) {
            ResultsStorage.addStr(weight, "sectionTitle", Turnover.getName());
            weight++;
            this.weight = weight;
            this.formulas = FormulaStorage.getItems(Turnover.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 10. Activity Ratios (Turnover Ratios)";
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items, title);
        weight++;
        box.getChildren().addAll(
                tableName(title),
                tbl,
                formulaEvaluation.get(weight)
        );
        return box;
    }
}
