package reportGeneration.interpreter.FinancialSustainability;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.FinancialSustainability.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class FinancialSustainabilityReport implements TableName {
    private ObservableList<Formula> formulas;

    public FinancialSustainabilityReport() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula FS = storage.get("FS");
        if (FS != null) {
            this.formulas = storage.getItems(FS.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);

        String title = "Table 5. Key ratios of the company's financial sustainability";
        Label tableName = tableName(title);
        ResultsStorage.addStr(41, "tableName", title);
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);


        RatiosTable sustainabilityTable = new RatiosTable(formulas);
        TableView tbl = sustainabilityTable.get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(41, items);

        box.getChildren().addAll(
                tableName,
                tbl,
                formulaEvaluation.get()
        );
        return box;
    }

}
