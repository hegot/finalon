package reportGeneration.interpreter.TurnoverRatios;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.interpreter.TurnoverRatios.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.TurnoverRatios.Outcomes.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class TurnoverRatios {
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
