package reportGeneration.interpreter.TurnoverRatios;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.interpreter.TurnoverRatios.Outcomes.FormulaEvaluation;
import reportGeneration.storage.FormulaStorage;

public class TurnoverRatios implements TableName {
    private ObservableList<Formula> formulas;

    public TurnoverRatios() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula FS = storage.get("T");
        if (FS != null) {
            this.formulas = storage.getItems(FS.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        RatiosTable turnoverTable = new RatiosTable(formulas);
        Label tableName = tableName("Table 10. Activity Ratios (Turnover Ratios)");
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        box.getChildren().addAll(
                tableName,
                turnoverTable.get(),
                formulaEvaluation.get()
        );
        return box;
    }
}
