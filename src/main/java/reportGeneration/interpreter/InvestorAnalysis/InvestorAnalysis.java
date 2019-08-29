package reportGeneration.interpreter.InvestorAnalysis;

import entities.Formula;
import reportGeneration.interpreter.InvestorAnalysis.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class InvestorAnalysis {
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
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = TableName.getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items, title);
        weight++;
        box.getChildren().addAll(
                TableName.name(title),
                tbl,
                formulaEvaluation.get(weight)
        );
        return box;
    }
}
