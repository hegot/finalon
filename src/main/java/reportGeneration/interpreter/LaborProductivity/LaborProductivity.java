package reportGeneration.interpreter.LaborProductivity;

import entities.Formula;
import entities.Item;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.LaborProductivity.Outcomes.LaborProductivityChart;
import reportGeneration.interpreter.LaborProductivity.Outcomes.LaborProductivityFormulaEvaluation;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.ResultsStorage;

public class LaborProductivity {
    private ObservableList<Formula> formulas;
    private int weight;
    private String sectionTitle;

    public LaborProductivity(int weight) {
        Formula LaborProductivitySection = FormulaStorage.get("LaborProductivitySection");
        if (LaborProductivitySection != null) {
            this.weight = weight;
            this.sectionTitle = LaborProductivitySection.getName();
            this.formulas = FormulaStorage.getItems(LaborProductivitySection.getId());
        }
    }

    public VBox get() {
        VBox box = new VBox(8);
        if(laborProductivityPopulated()){
            ResultsStorage.addStr(weight, "sectionTitle", sectionTitle);
            weight++;
            LaborProductivityChart chart = new LaborProductivityChart();
            LaborProductivityFormulaEvaluation formulaEvaluation = new LaborProductivityFormulaEvaluation(formulas);
            box.getChildren().addAll(
                    chart.get(weight),
                    formulaEvaluation.get(weight++)
            );
        }
        return box;
    }

    private Boolean laborProductivityPopulated() {
        Item numberOfEmployees = ItemsStorage.get("NumberOfEmployees");
        Boolean laborProductivityShow = false;
        if (numberOfEmployees.getValues().size() > 0) {
            ObservableMap<String, Double> vals = numberOfEmployees.getValues();
            for (String key : vals.keySet()) {
                if (vals.get(key) != 0) {
                    laborProductivityShow = true;
                }
            }
        }
        return laborProductivityShow;
    }
}