package finalon.reportGeneration.interpreter.FormulaList;

import finalon.entities.Formula;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.FormulaTable;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.Periods;

import java.util.ArrayList;

public class FormulaList extends FormulaTable {
    private ObservableList<Formula> formulas;
    private ArrayList<String> periods;

    public FormulaList() {
        this.periods = Periods.getPeriodArr();
        this.formulas = FormulaStorage.getItems();
    }

    public VBox get() {
        TableView<Formula> table = new TableView<Formula>();
        table.getStyleClass().add("report-table");
        table.setEditable(true);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        table.setMinHeight(primaryScreenBounds.getHeight() - 150);
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
