package finalon.reportGeneration.interpreter.TurnoverRatios.Outcomes;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import finalon.reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.FormulaTable;
import finalon.reportGeneration.storage.Periods;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
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
            for (int j = 1; j < periods.size(); j++) {
                String col = periods.get(j);
                table.getColumns().add(getPeriodCol(col));
            }
            int count = periods.size() - 1;
            if (count > 0) {
                for (int j = 0; j < count; j++) {
                    String colStart = periods.get(j);
                    String colEnd = periods.get(j + 1);
                    table.getColumns().add(getAbsoluteComparisonCol(colStart, colEnd));
                }
            }
            table.setItems(formulas);
        }
        return table;
    }

    TableColumn getAbsoluteComparisonCol(String colStart, String colEnd) {
        String colname = "Absolute Change\n" + Formatter.formatDate(colEnd) + " to \n" + Formatter.formatDate(colStart);
        TableColumn<Formula, String> col = new TableColumn<Formula, String>(colname);
        col.setMinWidth(150);
        col.setCellValueFactory(cellData -> {
            Formula formula = (Formula) cellData.getValue();
            if (formula != null) {
                return Calc.diff(
                        formula.getVal(colStart),
                        formula.getVal(colEnd)
                );
            }
            return null;
        });
        return col;
    }

}
