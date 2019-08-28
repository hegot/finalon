package finalon.reportGeneration.interpreter.FinancialSustainability;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.FinancialSustainability.Outcomes.FormulaEvaluation;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.TwoDList;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class FinancialSustainabilityReport implements TableName {
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
        Label tableName = tableName(title);
        weight++;
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        RatiosTable sustainabilityTable = new RatiosTable(formulas);
        TableView tbl = sustainabilityTable.get();
        TwoDList items = getTableViewValues(tbl);
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
