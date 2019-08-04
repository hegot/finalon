package reportGeneration.interpreter.Liquidity;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.Liquidity.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class LiquidityReport implements TableName {
    private ObservableList<Formula> formulas;
    private int weight;
    public LiquidityReport(int weight) {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula Liquidity = storage.get("Liquidity");
        if (Liquidity != null) {
            ResultsStorage.addStr(weight, "sectionTitle", Liquidity.getName());
            weight++;
            this.weight = weight;
            this.formulas = storage.getItems(Liquidity.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);

        String title = "Table 6. Liquidity Ratios";
        Label tableName = tableName(title);
        ResultsStorage.addStr(weight, "tableName", title);
        weight++;
        RatiosTable liquidityTable = new RatiosTable(formulas);
        TableView tbl = liquidityTable.get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items);
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        box.getChildren().addAll(
                tableName,
                tbl,
                formulaEvaluation.get(weight)
        );
        return box;
    }
}
