package finalon.reportGeneration.interpreter.TurnoverRatios;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import finalon.reportGeneration.interpreter.TurnoverRatios.Outcomes.FormulaEvaluation;
import finalon.reportGeneration.interpreter.TurnoverRatios.Outcomes.RatiosTable;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.TwoDList;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

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
