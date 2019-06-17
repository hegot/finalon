package reportGeneration.interpreter.ZScoreModel;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.TurnoverRatios.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ZScoreModel.Outcomes.RatiosTable;
import reportGeneration.storage.FormulaStorage;

public class ZScoreModel {
    private ObservableList<Formula> formulas;

    public ZScoreModel() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula AZS = storage.getItemByCode("AZS");
        if (AZS != null) {
            this.formulas = storage.getItems(AZS.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        RatiosTable turnoverTable = new RatiosTable(formulas);
        Label tableName = new Label("Table 12. The Z-Score Model for Private Firms ");
        tableName.getStyleClass().add("table-name");
        tableName.setWrapText(true);

        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        box.getChildren().addAll(
                tableName,
                turnoverTable.get(),
                formulaEvaluation.get()
        );
        return box;
    }
}
