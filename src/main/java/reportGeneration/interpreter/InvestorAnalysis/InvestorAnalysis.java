package reportGeneration.interpreter.InvestorAnalysis;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.InvestorAnalysis.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;

public class InvestorAnalysis  implements TableName {
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
        RatiosTable investorTable = new RatiosTable(formulas);
        Label tableName = tableName("Table 11. Investor Analysis");
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        box.getChildren().addAll(
                tableName,
                investorTable.get(),
                formulaEvaluation.get()
        );
        return box;
    }
}
