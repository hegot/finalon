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
        Formula InvestorAnalysis = storage.get("InvestorAnalysis");
        if (InvestorAnalysis != null) {
            ResultsStorage.addStr(90, "sectionTitle", InvestorAnalysis.getName());
            this.formulas = storage.getItems(InvestorAnalysis.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 11. Investor Analysis";
        ResultsStorage.addStr(91, "tableName", title);
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(92, items);

        box.getChildren().addAll(
                tableName(title),
                tbl,
                formulaEvaluation.get()
        );
        return box;
    }
}
