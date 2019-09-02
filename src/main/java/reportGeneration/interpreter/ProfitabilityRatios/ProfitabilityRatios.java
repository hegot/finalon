package reportGeneration.interpreter.ProfitabilityRatios;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ProfitabilityRatios.Outcomes.DupontAnalysis;
import reportGeneration.interpreter.ProfitabilityRatios.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class ProfitabilityRatios {
    private ObservableList<Formula> formulas;
    private int weight;

    public ProfitabilityRatios(int weight) {
        Formula PaP = FormulaStorage.get("ProfitabilityAndPerformance");
        if (PaP != null) {
            ResultsStorage.addStr(weight, "sectionTitle", PaP.getName());
            weight++;
            this.weight = weight;
            this.formulas = FormulaStorage.getItems(PaP.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 8. Profitability Ratios, %";
        Label tableName = TableName.name(title);
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = TableName.getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items, title);
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
