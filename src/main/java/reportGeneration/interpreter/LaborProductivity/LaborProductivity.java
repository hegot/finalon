package reportGeneration.interpreter.LaborProductivity;

import entities.Formula;
import reportGeneration.interpreter.LaborProductivity.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.LaborProductivity.Outcomes.LaborProductivityChart;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class LaborProductivity {
    private ObservableList<Formula> formulas;
    private int weight;

    public LaborProductivity(int weight) {
        Formula LaborProductivitySection = FormulaStorage.get("LaborProductivitySection");
        if (LaborProductivitySection != null) {
            ResultsStorage.addStr(weight, "sectionTitle", LaborProductivitySection.getName());
            weight++;
            this.weight = weight;
            this.formulas = FormulaStorage.getItems(LaborProductivitySection.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        LaborProductivityChart chart = new LaborProductivityChart();
        FormulaEvaluation formulaEvaluation = new FormulaEvaluation(formulas);
        box.getChildren().addAll(
                chart.get(weight),
                formulaEvaluation.get(weight++)
        );
        return box;
    }
}