package reportGeneration.interpreter.LaborProductivity;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.LaborProductivity.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.LaborProductivity.Outcomes.LaborProductivityChart;
import reportGeneration.storage.FormulaStorage;

public class LaborProductivity {
    private ObservableList<Formula> formulas;

    public LaborProductivity() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula OI = storage.get("OI");
        if (OI != null) {
            this.formulas = storage.getItems(OI.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);

        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        LaborProductivityChart chart = new LaborProductivityChart();

        box.getChildren().addAll(
                chart.get(),
                formulaEvaluation.get()
        );
        return box;
    }
}
