package reportGeneration.interpreter.FinancialSustainability;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.FinancialSustainability.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class FinancialSustainabilityReport {
    private ObservableList<Formula> formulas;
    private int weight;

    public FinancialSustainabilityReport(int weight) {
        Formula FS = FormulaStorage.get("FinancialSustainability");
        if (FS != null) {
            ResultsStorage.addStr(weight, "sectionTitle", FS.getName());
            weight++;
            this.weight = weight;
            this.formulas = FormulaStorage.getItems(FS.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);

        String title = "Table 5. Key ratios of the company's financial sustainability";
        Label tableName = TableName.name(title);
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        RatiosTable sustainabilityTable = new RatiosTable(formulas);
        TableView tbl = sustainabilityTable.get();
        TwoDList items = TableName.getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items, title);
        weight++;
        box.getChildren().addAll(
                tableName,
                tbl,
                formulaEvaluation.get(weight)
        );
        return box;
    }

}
