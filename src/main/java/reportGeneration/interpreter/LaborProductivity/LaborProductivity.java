package reportGeneration.interpreter.LaborProductivity;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.LaborProductivity.Outcomes.FormulaEvaluation;
import reportGeneration.interpreter.LaborProductivity.Outcomes.LaborProductivityChart;

public class LaborProductivity {
    private ObservableList<Formula> formulas;


    public VBox get() {
        VBox box = new VBox(8);

        FormulaEvaluation formulaEvaluation = new FormulaEvaluation();
        LaborProductivityChart chart = new LaborProductivityChart();

        box.getChildren().addAll(
                chart.get(),
                formulaEvaluation.get()
        );
        return box;
    }
}
