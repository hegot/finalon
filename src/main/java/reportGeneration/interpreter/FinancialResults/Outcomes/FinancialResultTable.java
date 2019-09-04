package reportGeneration.interpreter.FinancialResults.Outcomes;

import database.setting.DbSettingHandler;
import entities.Item;
import globalReusables.LabelWrap;
import globalReusables.Setting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.interpreter.ReusableComponents.tables.ItemsTable;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;
import java.util.Collections;

public class FinancialResultTable extends ItemsTable {

    private ObservableList<Item> items;
    private ArrayList<String> periods;
    private Item grossProfit;
    private Item itemEbit;
    private Item comprehensiveIncome;
    private ItemsGetter itemsGetter = new ItemsGetter();

    public FinancialResultTable() {
        super(ItemsStorage.getItems());
        this.periods = Periods.getPeriodArr();
        this.grossProfit = ItemsStorage.get("GrossProfit");
        this.itemEbit = itemsGetter.getEbit();
        this.comprehensiveIncome = ItemsStorage.get("ComprehensiveIncomeGeneral");
        this.items = getItems();
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


    public Label analyseEbit() {
        String out = "";
        Double first = itemEbit.getFirstVal();
        Double last = itemEbit.getLastVal();
        String startDate = Periods.getStart();
        String endDate = Periods.getEnd();

        String positive = (first > 0) ? "positive" : "negative";
        if (first == 0) {
            out += "Zero EBIT indicates poor performance in " + endDate + ". ";
        } else {
            out += "EBIT was " + positive + " at " + SettingsStorage.get("defaultCurrency") +
                    " " + last + " " + SettingsStorage.get("amount") + " in " + endDate + ". ";
        }

        double change = last - first;
        String growth = Formatter.doubleCommaFormat(change / first * 100);
        if (change > 0) {
            out += "The EBIT growth was " + growth + "% during " + startDate + "-" + endDate + ". ";
        } else if (change < 0) {
            out += "The EBIT declined " + growth + "% during " + startDate + "-" + endDate + ". ";
        } else {
            out += "The EBIT was stable during " + startDate + "-" + endDate + ". ";
        }
        out += comprehensiveIncome();
        return LabelWrap.wrap(out);
    }


    private String comprehensiveIncome() {
        String endDate = Periods.getEnd();
        Double last = comprehensiveIncome.getLastVal();
        String output = "";
        if (last != null) {
            if (last <= 0) {
                output = "On the whole, " + endDate + " was a bad period as the company recorded " +
                        SettingsStorage.get("defaultCurrency") + " " + last + " " +
                        SettingsStorage.get("amount") + " comprehensive loss.";
            } else {
                output = "On the whole, " + endDate + " was a good period as the company recorded " +
                        SettingsStorage.get("defaultCurrency") + " " + last + " " +
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
        for (TableColumn col : getAbsoluteCols()) {
            table.getColumns().add(col);
        }
        table.setItems(items);
        return table;
    }

    protected ArrayList<TableColumn> getAbsoluteCols() {
        ArrayList<TableColumn> colsArr = new ArrayList<>();
        ArrayList<String> periods = Periods.getPeriodArr();
        int count = periods.size() - 1;
        if (count > 0) {
            for (int j = 0; j < count; j++) {
                String colStart = periods.get(j);
                String colEnd = periods.get(j + 1);
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


