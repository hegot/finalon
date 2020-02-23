package reportGeneration.interpreter.ZScoreModel.Outcomes;

import database.setting.DbSettingHandler;
import entities.Formula;
import globalReusables.Setting;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import reportGeneration.interpreter.ReusableComponents.tables.FormulaTable;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.Collections;

public class RatiosTable extends FormulaTable {
    private ObservableList<Formula> formulas;

    public RatiosTable(ObservableList<Formula> formulas) {
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
            for(int i = 0; i < formulas.size(); i++){
                Formula formula = formulas.get(i);
                if(formula.valsEmpty()){
                    i--;
                    formulas.remove(formula);
                }
            }
            table.setItems(formulas);
        }
        return table;
    }


    protected ArrayList<TableColumn> getPeriodCols() {
        ArrayList<TableColumn> colsArr = new ArrayList<TableColumn>();
        for (String col : Periods.getPeriodArr()) {
            colsArr.add(getPeriodCol(col));
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(colsArr);
        }
        return colsArr;
    }
}
