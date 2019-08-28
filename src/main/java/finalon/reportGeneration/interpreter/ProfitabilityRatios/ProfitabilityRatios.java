package finalon.reportGeneration.interpreter.ProfitabilityRatios;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.ProfitabilityRatios.Outcomes.DupontAnalysis;
import finalon.reportGeneration.interpreter.ProfitabilityRatios.Outcomes.FormulaEvaluation;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.TwoDList;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ProfitabilityRatios implements TableName {
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
        Label tableName = tableName(title);
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
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
