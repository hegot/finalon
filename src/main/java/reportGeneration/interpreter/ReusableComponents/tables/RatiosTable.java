package reportGeneration.interpreter.ReusableComponents.tables;

import entities.Formula;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class RatiosTable extends FormulaTable implements JsCalcHelper, ParseDouble, Round {
    private ObservableList<Formula> formulas;
    private ArrayList<String> periods;

    public RatiosTable(ObservableList<Formula> formulas) {
        this.periods = Periods.getInstance().getPeriodArr();
        this.formulas = formulas;
    }

    public TableView get() {
        TableView<Formula> table = new TableView<Formula>();
        table.getStyleClass().add("report-table");
        table.setEditable(false);
        table.getColumns().add(getNameCol());
        for (String col : periods) {
            table.getColumns().add(getPeriodCol(col));
        }
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        int count = arr.size() - 1;
        if (count > 0) {
            for (int j = 0; j < count; j++) {
                String colStart = arr.get(j);
                String colEnd = arr.get(j + 1);
                table.getColumns().add(getAbsoluteComparisonCol(colStart, colEnd));
            }
        }
        table.setItems(formulas);
        return table;
    }


    TableColumn getAbsoluteComparisonCol(String colStart, String colEnd) {
        String colname = "Absolute Change\n" + formatDate(colEnd) + " to \n" + formatDate(colStart);
        TableColumn<Formula, String> col = new TableColumn<Formula, String>(colname);
        col.setMinWidth(150);
        col.setCellValueFactory(cellData -> {
            Formula formula = (Formula) cellData.getValue();
            if (formula != null) {
                Double colStartVAl = formula.getFirstVal();
                Double colEndVAl = formula.getLastVal();
                if (colStartVAl != null && colEndVAl != null) {
                    String absolute = round(colEndVAl - colStartVAl);
                    return new SimpleStringProperty(absolute);
                }
            }
            return null;
        });
        return col;
    }

}
