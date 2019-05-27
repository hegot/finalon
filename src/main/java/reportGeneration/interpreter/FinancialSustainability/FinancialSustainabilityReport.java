package reportGeneration.interpreter.FinancialSustainability;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.FinancialSustainability.Outcomes.FinancialSustainabilityTable;
import reportGeneration.interpreter.FinancialSustainability.Outcomes.FormulaEvaluation;
import reportGeneration.storage.FormulaStorage;

public class FinancialSustainabilityReport {
    private ObservableList<Formula> formulas;

    public FinancialSustainabilityReport() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula FS = storage.getItemByCode("FS");
        if (FS != null) {
            this.formulas = storage.getItems(FS.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        FinancialSustainabilityTable sustainabilityTable = new FinancialSustainabilityTable(formulas);
        Label tableName = new Label("Table 5. Key ratios of the company's financial sustainability");
        tableName.getStyleClass().add("assets-table-name");
        tableName.setWrapText(true);

        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        box.getChildren().addAll(
                tableName,
                sustainabilityTable.get(),
                formulaEvaluation.get()
        );
        return box;
    }

}
