package reportGeneration.interpreter.LaborProductivity;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.LaborProductivity.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.LaborProductivity.Outcomes.LaborProductivityChart;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;

public class LaborProductivity {
    private ObservableList<Formula> formulas;

    public LaborProductivity() {
        FormulaStorage storage = FormulaStorage.getInstance();
        Formula LaborProductivitySection = storage.get("LaborProductivitySection");
        if (LaborProductivitySection != null) {
            ResultsStorage.addStr(110, "sectionTitle", LaborProductivitySection.getName());
            this.formulas = storage.getItems(LaborProductivitySection.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        LaborProductivityChart chart = new LaborProductivityChart();
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        box.getChildren().addAll(
                chart.get(),
                formulaEvaluation.get()
        );
        return box;
    }
}