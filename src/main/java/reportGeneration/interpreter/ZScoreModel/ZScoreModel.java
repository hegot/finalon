package reportGeneration.interpreter.ZScoreModel;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.TurnoverRatios.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ZScoreModel.Outcomes.RatiosTable;
import reportGeneration.storage.FormulaStorage;

public class ZScoreModel implements TableName {
    private ObservableList<Formula> formulas;

    public ZScoreModel() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula AZS = storage.get("AZS");
        if (AZS != null) {
            this.formulas = storage.getItems(AZS.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        RatiosTable turnoverTable = new RatiosTable(formulas);
        Label tableName = tableName("Table 12. The Z-Score Model for Private Firms ");
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        box.getChildren().addAll(
                tableName,
                turnoverTable.get(),
                formulaEvaluation.get()
        );
        return box;
    }
}
