package reportGeneration.interpreter.ReusableComponents.tables;

import entities.Formula;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
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

    public VBox get() {
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
        VBox vbox = new VBox();
        vbox.getChildren().add(table);
        return vbox;
    }

    private ObservableMap<String, String> getValues(TableColumn.CellDataFeatures<Formula, String> cellData) {
        Formula formula = (Formula) cellData.getValue();
        if (formula != null) {
            if (formula.getPeriods().size() > 0) {
                return formula.getPeriods();
            }
        }
        return null;
    }

    TableColumn getAbsoluteComparisonCol(String colStart, String colEnd) {
        String colname = "Absolute Change\n" + formatDate(colEnd) + " to \n" + formatDate(colStart);
        TableColumn<Formula, String> col = new TableColumn<Formula, String>(colname);
        col.setMinWidth(150);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, String> values = getValues(cellData);
            if (values != null) {
                Double colStartVAl = parseDouble(values.get(colStart));
                Double colEndVAl = parseDouble(values.get(colEnd));
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
