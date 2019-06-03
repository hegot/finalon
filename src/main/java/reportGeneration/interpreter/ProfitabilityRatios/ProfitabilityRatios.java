package reportGeneration.interpreter.ProfitabilityRatios;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ProfitabilityRatios.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;

public class ProfitabilityRatios {
    private ObservableList<Formula> formulas;

    public ProfitabilityRatios() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula PaP = storage.getItemByCode("PaP");
        if (PaP != null) {
            this.formulas = storage.getItems(PaP.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        Label tableName = new Label("Table 8. Profitability Ratios, %");
        tableName.getStyleClass().add("table-name");
        tableName.setWrapText(true);

        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        box.getChildren().addAll(
                tableName,
                new RatiosTable(formulas).get(),
                formulaEvaluation.get()
        );
        return box;
    }
}
