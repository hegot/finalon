package reportGeneration.interpreter.ZScoreModel.Outcomes;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.tables.FormulaTable;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class RatiosTable extends FormulaTable {
    private ObservableList<Formula> formulas;
    private ArrayList<String> periods;

    public RatiosTable(ObservableList<Formula> formulas) {
        this.periods = Periods.getInstance().getPeriodArr();
        this.formulas = formulas;
    }

    public VBox get() {
        TableView<Formula> table = new TableView<Formula>();
        table.getStyleClass().add("report-table");
        table.setEditable(false);
        table.getColumns().add(getNameCol());
        for (String col : periods) {
            table.getColumns().add(getPeriodCol(col));
        }
        table.setItems(formulas);
        VBox vbox = new VBox();
        vbox.getChildren().add(table);
        return vbox;
    }


}
