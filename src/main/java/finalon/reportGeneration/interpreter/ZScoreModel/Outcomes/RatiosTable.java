package finalon.reportGeneration.interpreter.ZScoreModel.Outcomes;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.FormulaTable;
import finalon.reportGeneration.storage.Periods;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class RatiosTable extends FormulaTable {
    private ObservableList<Formula> formulas;
    private ArrayList<String> periods;

    public RatiosTable(ObservableList<Formula> formulas) {
        this.periods = Periods.getPeriodArr();
        this.formulas = formulas;
    }

    public TableView get() {
        TableView<Formula> table = new TableView<Formula>();
        table.getStyleClass().add("report-table");
        table.setEditable(false);
        if (formulas != null) {
            table.getColumns().add(getNameCol());
            for (String col : periods) {
                table.getColumns().add(getPeriodCol(col));
            }
            table.setItems(formulas);
        }
        return table;
    }


}
