package reportGeneration.interpreter.FinancialSustainability.Outcomes;

import entities.Formula;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import reportGeneration.interpreter.ReusableComponents.FormulaTable;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class FinancialSustainabilityTable extends FormulaTable implements JsCalcHelper {
    private ObservableList<Formula> formulas;
    private ArrayList<String> periods;

    public FinancialSustainabilityTable(ObservableList<Formula> formulas) {
        this.periods = Periods.getInstance().getPeriodArr();
        this.formulas = formulas;
    }

    public VBox get() {
        TableView<Formula> table = new TableView<Formula>();
        table.getStyleClass().add("assets-report");
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
                Double colStartVAl = Double.parseDouble(values.get(colStart));
                Double colEndVAl = Double.parseDouble(values.get(colEnd));
                if (colStartVAl != null && colEndVAl != null) {
                    String absolute = String.format("%.2f", colEndVAl - colStartVAl);
                    return new SimpleStringProperty(absolute);
                }
            }
            return null;
        });
        return col;
    }
}
