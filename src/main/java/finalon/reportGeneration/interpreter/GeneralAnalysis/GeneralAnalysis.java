package finalon.reportGeneration.interpreter.GeneralAnalysis;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.GeneralAnalysis.Outcomes.FormulaEvaluation;
import finalon.reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.TwoDList;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class GeneralAnalysis {
    private ObservableList<Formula> formulas;
    private Formula section;
    private int weight;

    public GeneralAnalysis(String type, int weight) {
        Formula formula = FormulaStorage.get(type);
        if (formula != null) {
            ResultsStorage.addStr(weight, "sectionTitle", formula.getName());
            weight++;
            this.section = formula;
            this.formulas = FormulaStorage.getItems(formula.getId());
            this.weight = weight;
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        if (section != null) {
            String title = "Table 5. Evaluation of " + section.getName();
            Label tableName = TableName.name(title);
            weight++;
            FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
            RatiosTable table = new RatiosTable(formulas);
            TableView tbl = table.get();
            TwoDList items = TableName.getTableViewValues(tbl);
            ResultsStorage.addTable(weight, items, title);
            weight++;
            box.getChildren().addAll(
                    tableName,
                    tbl,
                    formulaEvaluation.get(weight)
            );
        }
        return box;
    }
}
