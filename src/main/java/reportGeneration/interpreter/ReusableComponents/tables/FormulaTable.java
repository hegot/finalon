package reportGeneration.interpreter.ReusableComponents.tables;

import database.setting.DbSettingHandler;
import entities.Formula;
import globalReusables.Setting;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;
import java.util.Collections;

public class FormulaTable {
    protected TableColumn getNameCol() {
        TableColumn<Formula, String> col = new TableColumn<Formula, String>("Formula");
        col.setId("name-column");
        col.setMinWidth(350);
        col.setSortable(false);
        col.setCellValueFactory(cellData -> {
            Formula formula = (Formula) cellData.getValue();
            if (formula != null) {
                String name = formula.getName();
                String unit = formatUnit(formula.getUnit());
                return new SimpleStringProperty(name + unit);
            }
            return null;
        });
        return col;
    }

    private String formatUnit(String unit) {
        if (unit.length() > 0) {
            String currency = SettingsStorage.get("defaultCurrency");
            String amount = SettingsStorage.get("amount");
            String replace = amount + " " + currency;
            unit = unit.replace("money", replace);
            return ", " + unit;
        } else {
            return "";
        }
    }

    protected TableColumn getPeriodCol(String colname) {
        String[] split = colname.split("-");
        TableColumn<Formula, String> col = new TableColumn<Formula, String>(split[1]);
        col.getStyleClass().add("period-col");
        col.setMinWidth(100);
        col.setSortable(false);
        col.setCellValueFactory(cellData -> {
            Formula formula = (Formula) cellData.getValue();
            if (formula != null && formula.getPeriods().size() > 0 && formula.getVal(colname) != null) {
                String period = Formatter.finalNumberFormat(formula.getVal(colname));
                if (period != null) {
                    return new SimpleStringProperty(period);
                }
            }
            return null;
        });
        return col;
    }

    protected TableColumn getFirstLastComparisonCol() {
        ArrayList<String> periods = Periods.getPeriodArr();
        String colStart = periods.get(0);
        String colEnd = periods.get(periods.size() - 1);
        String colname = "Absolute Change\n" + Formatter.formatDate(colStart) +
                " to \n" + Formatter.formatDate(colEnd);
        TableColumn<Formula, String> col = new TableColumn<Formula, String>(colname);
        col.getStyleClass().add("period-col");
        col.setMinWidth(150);
        col.setSortable(false);
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

    protected TableColumn getAbsoluteComparisonCol(String colStart, String colEnd) {
        String colname = "Absolute Change\n" + Formatter.formatDate(colEnd) + " to \n" + Formatter.formatDate(colStart);
        TableColumn<Formula, String> col = new TableColumn<Formula, String>(colname);
        col.getStyleClass().add("period-col");
        col.setMinWidth(150);
        col.setSortable(false);
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

    protected ArrayList<TableColumn> getAbsoluteCols() {
        ArrayList<TableColumn> colsArr = new ArrayList<>();
        ArrayList<String> periods = Periods.getPeriodArr();
        int count = periods.size() - 1;
        if (count > 0) {
            String colStart;
            String colEnd;
            for (int j = 0; j < count; j++) {
                colStart = periods.get(j);
                colEnd = periods.get(j + 1);
                colsArr.add(getAbsoluteComparisonCol(colStart, colEnd));
            }
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(colsArr);
        }
        return colsArr;
    }
}
