package reportGeneration.interpreter.InvestorAnalysis;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.InvestorAnalysis.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class InvestorAnalysis implements TableName {
    private ObservableList<Formula> formulas;

    public InvestorAnalysis() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula IA = storage.get("IA");
        if (IA != null) {
            this.formulas = storage.getItems(IA.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 11. Investor Analysis";
        ResultsStorage.addStr(60, "h2", title);
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(61, items);

        box.getChildren().addAll(
                tableName(title),
                tbl,
                formulaEvaluation.get()
        );
        return box;
    }
}
