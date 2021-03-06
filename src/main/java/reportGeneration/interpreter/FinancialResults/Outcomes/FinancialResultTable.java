package reportGeneration.interpreter.FinancialResults.Outcomes;

import database.setting.DbSettingHandler;
import entities.Item;
import globalReusables.LabelWrap;
import globalReusables.Setting;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.interpreter.ReusableComponents.tables.ItemsTable;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;
import java.util.Collections;

public class FinancialResultTable extends ItemsTable {

    private ObservableList<Item> items;
    private ArrayList<String> periods;
    private Item grossProfit;
    private Item itemEbit;
    private Item comprehensiveIncome;
    private FrItemsGetter itemsGetter = new FrItemsGetter();
    private int periodsSize;

    public FinancialResultTable() {
        super(ItemsStorage.getItems());
        this.periods = Periods.getPeriodArr();
        this.grossProfit = ItemsStorage.get("GrossProfit");
        this.itemEbit = itemsGetter.getEbit();
        this.comprehensiveIncome = ItemsStorage.get("ComprehensiveIncomeGeneral");
        this.items = getItems();
        this.periodsSize = periods.size();
    }

    private ObservableList<Item> getItems() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(ItemsStorage.get("RevenueGeneral"));
        items.add(ItemsStorage.get("CostOfSales"));
        items.add(grossProfit);
        items.add(itemsGetter.getOtherIncome());
        items.add(itemEbit);
        items.add(ItemsStorage.get("FinanceCosts"));
        items.add(ItemsStorage.get("IncomeTaxExpenseContinuingOperations"));
        items.add(itemsGetter.getIncomeLossFromContinuingOperations());
        items.add(comprehensiveIncome);
        return items;
    }


    public Label analyseEbit(int weight) {
        String out = "";
        Double first = itemEbit.getFirstVal();
        Double last = itemEbit.getLastVal();
        String startDate = Periods.getStart();
        String endDate = Periods.getEnd();

        String positive = (first > 0) ? "positive" : "negative";
        if (last == 0) {
            out += "Zero EBIT indicates poor performance in " + endDate + ". ";
        } else {
            out += "EBIT was " + positive + " at " + SettingsStorage.get("defaultCurrency") +
                    " " + Formatter.finalNumberFormat(last) + " " + SettingsStorage.get("amount") + " in " + endDate + ". ";
        }

        if (first != 0) {
            double change = last - first;
            String growth = Formatter.finalNumberFormat(change / first * 100);
            if (change > 0) {
                out += "The EBIT growth was " + growth + "% during " + startDate + "-" + endDate + ". ";
            } else if (change < 0) {
                out += "The EBIT declined " + growth + "% during " + startDate + "-" + endDate + ". ";
            } else {
                out += "The EBIT was stable during " + startDate + "-" + endDate + ". ";
            }
        }
        out += comprehensiveIncome();
        ResultsStorage.addStr(weight, "text", out);
        return LabelWrap.wrap(out);
    }


    private String comprehensiveIncome() {
        String endDate = Periods.getEnd();
        Double last = comprehensiveIncome.getLastVal();
        String output = "";
        if (last != null) {
            if (last <= 0) {
                output = "On the whole, " + endDate + " was a bad period as the company recorded " +
                        SettingsStorage.get("defaultCurrency") + " " + Formatter.finalNumberFormat(last) + " " +
                        SettingsStorage.get("amount") + " comprehensive loss.";
            } else {
                output = "On the whole, " + endDate + " was a good period as the company recorded " +
                        SettingsStorage.get("defaultCurrency") + " " + Formatter.finalNumberFormat(last) + " " +
                        SettingsStorage.get("amount") + " comprehensive income.";
            }
        }
        return output;
    }

    public TableView get() {
        TableView<Item> table = new TableView<Item>();
        table.setEditable(false);
        table.getStyleClass().add("report-table");
        table.getColumns().add(getNameCol());
        for (TableColumn col : getPeriodCols()) {
            table.getColumns().add(col);
        }
        if (periodsSize > 3) {
            String colStart = periods.get(periodsSize - 2);
            String colEnd = periods.get(periodsSize - 1);
            table.getColumns().addAll(
                    getAbsoluteComparisonCol(colStart, colEnd),
                    getFirstLastAbsComparisonCol(),
                    getRelativeComparisonCol(colStart, colEnd),
                    getFirstLastPercentComparisonCol()
            );
        } else {
            for (TableColumn col : getCols("Absolute")) {
                table.getColumns().add(col);
            }
            if (periodsSize > 2) {
                table.getColumns().add(getFirstLastAbsComparisonCol());
            }
            for (TableColumn col : getCols("Relative")) {
                table.getColumns().add(col);
            }
            if (periodsSize > 2) {
                table.getColumns().add(getFirstLastPercentComparisonCol());
            }
        }
        table.setItems(items);
        return table;
    }

    private ArrayList<TableColumn> getCols(String type) {
        ArrayList<TableColumn> colsArr = new ArrayList<>();
        ArrayList<String> arr = Periods.getPeriodArr();
        int count = arr.size() - 1;
        if (count > 0) {
            String colStart;
            String colEnd;
            TableColumn col;
            for (int j = 0; j < count; j++) {
                colStart = arr.get(j);
                colEnd = arr.get(j + 1);
                col = (type.equals("Absolute")) ? getAbsoluteComparisonCol(colStart, colEnd) : getRelativeComparisonCol(colStart, colEnd);
                colsArr.add(col);
            }
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(colsArr);
        }
        return colsArr;
    }

    protected TableColumn getRelativeComparisonCol(String colStart, String colEnd) {
        String colname = "Percentage change\n" + Formatter.formatDate(colEnd) + " to \n" + Formatter.formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.setMinWidth(150);
        col.getStyleClass().add("period-col");
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double colStartVAl = values.get(colStart);
                Double colEndVAl = values.get(colEnd);
                if (colStartVAl != null && colEndVAl != null) {
                    String relative = Calc.getRelativeChange(colStartVAl, colEndVAl);
                    if (relative.length() > 0) {
                        return new SimpleStringProperty(Formatter.stringCommaFormat(relative) + "%");
                    }
                }
            }
            return null;
        });
        return col;
    }

    protected TableColumn getFirstLastAbsComparisonCol() {
        ArrayList<String> periods = Periods.getPeriodArr();
        String colStart = periods.get(0);
        String colEnd = periods.get(periods.size() - 1);
        String colname = "Absolute Change\n" + Formatter.formatDate(colEnd) + " to \n" + Formatter.formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.getStyleClass().add("period-col");
        col.setMinWidth(150);
        col.setSortable(false);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                return Calc.diff(
                        values.get(colStart),
                        values.get(colEnd)
                );
            }
            return null;
        });
        return col;
    }

    protected TableColumn getFirstLastPercentComparisonCol() {
        ArrayList<String> periods = Periods.getPeriodArr();
        String colStart = periods.get(0);
        String colEnd = periods.get(periods.size() - 1);
        String colname = "Percentage change\n" + Formatter.formatDate(colEnd) + " to \n" + Formatter.formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.setMinWidth(150);
        col.getStyleClass().add("period-col");
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double colStartVAl = values.get(colStart);
                Double colEndVAl = values.get(colEnd);
                if (colStartVAl != null && colEndVAl != null) {
                    String relative = Calc.getRelativeChange(colStartVAl, colEndVAl);
                    if (relative.length() > 0) {
                        return new SimpleStringProperty(Formatter.stringCommaFormat(relative) + "%");
                    }
                }
            }
            return null;
        });
        return col;
    }

}


