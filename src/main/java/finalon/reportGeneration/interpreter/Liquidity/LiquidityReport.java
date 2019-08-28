package finalon.reportGeneration.interpreter.Liquidity;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.Liquidity.Outcomes.FormulaEvaluation;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.TwoDList;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class LiquidityReport implements TableName {
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
        Label tableName = tableName(title);
        weight++;
        RatiosTable liquidityTable = new RatiosTable(formulas);
        TableView tbl = liquidityTable.get();
        TwoDList items = getTableViewValues(tbl);
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
