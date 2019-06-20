package reportGeneration.interpreter.FinancialSustainability;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.FinancialSustainability.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;

public class FinancialSustainabilityReport implements TableName {
    private ObservableList<Formula> formulas;

    public FinancialSustainabilityReport() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula FS = storage.get("FS");
        if (FS != null) {
            this.formulas = storage.getItems(FS.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        RatiosTable sustainabilityTable = new RatiosTable(formulas);
        Label tableName = tableName("Table 5. Key ratios of the company's financial sustainability");
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        box.getChildren().addAll(
                tableName,
                sustainabilityTable.get(),
                formulaEvaluation.get()
        );
        return box;
    }

}
