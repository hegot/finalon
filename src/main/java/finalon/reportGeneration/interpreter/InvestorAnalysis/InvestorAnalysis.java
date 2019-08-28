package finalon.reportGeneration.interpreter.InvestorAnalysis;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.InvestorAnalysis.Outcomes.FormulaEvaluation;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.TwoDList;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

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
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items, title);
        weight++;
        box.getChildren().addAll(
                tableName(title),
                tbl,
                formulaEvaluation.get(weight)
        );
        return box;
    }
}
