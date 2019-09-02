package reportGeneration.interpreter.TurnoverRatios.Outcomes;

import database.setting.DbSettingHandler;
import entities.Formula;
import globalReusables.Setting;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.interpreter.ReusableComponents.tables.FormulaTable;
import reportGeneration.storage.Periods;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Collections;

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
            for (TableColumn col : getPeriodCols()) {
                table.getColumns().add(col);
            }
            for (TableColumn col : getAbsoluteCols()) {
                table.getColumns().add(col);
            }
            table.setItems(formulas);
        }
        return table;
    }




    protected ArrayList<TableColumn> getPeriodCols() {
        ArrayList<TableColumn> colsArr = new ArrayList<TableColumn>();
        for (int j = 1; j < periods.size(); j++) {
            String col = periods.get(j);
            colsArr.add(getPeriodCol(col));
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(colsArr);
        }
        return colsArr;
    }

}
