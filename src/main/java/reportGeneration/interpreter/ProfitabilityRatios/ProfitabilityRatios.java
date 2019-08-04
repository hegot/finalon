package reportGeneration.interpreter.ProfitabilityRatios;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ProfitabilityRatios.Outcomes.DupontAnalysis;
import reportGeneration.interpreter.ProfitabilityRatios.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class ProfitabilityRatios implements TableName {
    private ObservableList<Formula> formulas;
    private int weight;

    public ProfitabilityRatios(int weight) {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula PaP = storage.get("ProfitabilityAndPerformance");
        if (PaP != null) {
            ResultsStorage.addStr(weight, "sectionTitle", PaP.getName());
            weight++;
            this.weight = weight;
            this.formulas = storage.getItems(PaP.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 8. Profitability Ratios, %";
        Label tableName = tableName(title);
        ResultsStorage.addStr(weight, "tableName", title);
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items);
        weight++;
        box.getChildren().addAll(
                tableName,
                tbl,
                formulaEvaluation.get(weight),
                new DupontAnalysis().get(weight++)
        );
        return box;
    }
}
