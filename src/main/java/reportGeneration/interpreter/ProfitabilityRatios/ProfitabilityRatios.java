package reportGeneration.interpreter.ProfitabilityRatios;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ProfitabilityRatios.Outcomes.DupontAnalysis;
import reportGeneration.interpreter.ProfitabilityRatios.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;

public class ProfitabilityRatios implements TableName {
    private ObservableList<Formula> formulas;

    public ProfitabilityRatios() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula PaP = storage.get("PaP");
        if (PaP != null) {
            this.formulas = storage.getItems(PaP.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        Label tableName = tableName("Table 8. Profitability Ratios, %");
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        box.getChildren().addAll(
                tableName,
                new RatiosTable(formulas).get(),
                formulaEvaluation.get(),
                new DupontAnalysis().get()
        );
        return box;
    }
}
