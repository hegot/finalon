package reportGeneration.interpreter.Liquidity;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.Liquidity.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;

public class LiquidityReport {
    private ObservableList<Formula> formulas;

    public LiquidityReport() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula L = storage.getItemByCode("L");
        if (L != null) {
            this.formulas = storage.getItems(L.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        RatiosTable sustainabilityTable = new RatiosTable(formulas);
        Label tableName = new Label("Table 6. Liquidity Ratios");
        tableName.getStyleClass().add("table-name");
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
