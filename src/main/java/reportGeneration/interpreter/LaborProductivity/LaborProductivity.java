package reportGeneration.interpreter.LaborProductivity;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.LaborProductivity.Outcomes.LaborProductivityFormulaEvaluation;
import reportGeneration.interpreter.LaborProductivity.Outcomes.LaborProductivityChart;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;

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
        LaborProductivityFormulaEvaluation formulaEvaluation = new LaborProductivityFormulaEvaluation(formulas);
        box.getChildren().addAll(
                chart.get(weight),
                formulaEvaluation.get(weight++)
        );
        return box;
    }
}