package reportGeneration.interpreter.Liquidity;

import entities.Formula;
import reportGeneration.interpreter.Liquidity.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class LiquidityReport {
    private ObservableList<Formula> formulas;
    private int weight;

    public LiquidityReport(int weight) {
        Formula Liquidity = FormulaStorage.get("Liquidity");
        if (Liquidity != null) {
            ResultsStorage.addStr(weight, "sectionTitle", Liquidity.getName());
            weight++;
            this.weight = weight;
            this.formulas = FormulaStorage.getItems(Liquidity.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);

        String title = "Table 6. Liquidity Ratios";
        Label tableName = TableName.name(title);
        weight++;
        RatiosTable liquidityTable = new RatiosTable(formulas);
        TableView tbl = liquidityTable.get();
        TwoDList items = TableName.getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items, title);
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
