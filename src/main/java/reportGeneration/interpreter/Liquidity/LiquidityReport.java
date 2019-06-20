package reportGeneration.interpreter.Liquidity;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.Liquidity.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;

public class LiquidityReport implements TableName {
    private ObservableList<Formula> formulas;

    public LiquidityReport() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula L = storage.get("L");
        if (L != null) {
            this.formulas = storage.getItems(L.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        RatiosTable liquidityTable = new RatiosTable(formulas);
        Label tableName = tableName("Table 6. Liquidity Ratios");

        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        box.getChildren().addAll(
                tableName,
                liquidityTable.get(),
                formulaEvaluation.get()
        );
        return box;
    }
}
