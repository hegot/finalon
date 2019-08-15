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
    private int weight;

    public InvestorAnalysis(int weight) {
        Formula InvestorAnalysis = FormulaStorage.get("InvestorAnalysis");
        if (InvestorAnalysis != null) {
            ResultsStorage.addStr(weight, "sectionTitle", InvestorAnalysis.getName());
            weight++;
            this.weight = weight;
            this.formulas = FormulaStorage.getItems(InvestorAnalysis.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 11. Investor Analysis";
        ResultsStorage.addStr(weight, "tableName", title);
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);

        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items);
        weight++;
        box.getChildren().addAll(
                tableName(title),
                tbl,
                formulaEvaluation.get(weight)
        );
        return box;
    }
}
