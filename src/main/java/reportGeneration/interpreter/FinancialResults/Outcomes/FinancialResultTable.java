package reportGeneration.interpreter.FinancialResults.Outcomes;

import entities.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.*;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class FinancialResultTable implements ParseDouble, JsCalcHelper, OutcomeBase, LabelWrap, Round {

    private ObservableList<Item> items;
    private ArrayList<String> periods;
    private Item grossProfit;
    private Item itemEbit;
    private Item comprehensiveIncome;
    private ItemsStorage storage = ItemsStorage.getInstance();
    private ObservableMap<String, String> settings = SettingsStorage.getSettings();
    private ItemsGetter itemsGetter = new ItemsGetter();

    public FinancialResultTable() {
        this.periods = new Periods().getPeriodArr();
        this.grossProfit = storage.getItemByCode("GrossProfit");
        this.itemEbit = itemsGetter.getEbit();
        this.comprehensiveIncome = storage.getItemByCode("ComprehensiveIncomeGeneral");
        this.items = getItems();
    }

    private ObservableList<Item> getItems() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(storage.getItemByCode("RevenueGeneral"));
        items.add(storage.getItemByCode("CostOfSales"));
        items.add(grossProfit);
        items.add(itemsGetter.getOtherIncome());
        items.add(itemEbit);
        items.add(storage.getItemByCode("FinanceCosts"));
        items.add(storage.getItemByCode("IncomeTaxExpenseContinuingOperations"));
        items.add(itemsGetter.getIncomeLossFromContinuingOperations());
        items.add(comprehensiveIncome);
        return items;
    }


    public Label analyseEbit() {
        String out = "";
        Double first = getFirstVal(itemEbit.getValues());
        Double last = getLastVal(itemEbit.getValues());
        String startDate = Periods.getInstance().getStart();
        String endDate = Periods.getInstance().getEnd();

        String positive = (first > 0) ? "positive" : "negative";
        out += "EBIT was " + positive + " at " + settings.get("defaultCurrency") +
                " " + last + " " + settings.get("amount") + " in " + endDate + ".";
        Double change = last - first;
        String growth = round(change / first * 100);
        if (change > 0) {
            out += "The EBIT growth was " + growth + "% during " + startDate + "-" + endDate + ".";
        } else if (change < 0) {
            out += "The EBIT declined " + growth + "% during " + startDate + "-" + endDate + ".";
        } else {
            out += "The EBIT was stable during " + startDate + "-" + endDate + ".";
        }
        out += comprehensiveIncome();
        return labelWrap(out);
    }


    private String comprehensiveIncome() {
        String endDate = Periods.getInstance().getEnd();
        Double last = getLastVal(comprehensiveIncome.getValues());
        String output = "";
        if (last != null) {
            if (last <= 0) {
                output = "On the whole, " + endDate + " was a bad period as the company recorded " +
                        settings.get("defaultCurrency") + " " + last + " " +
                        settings.get("amount") + " comprehensive loss.";
            } else {
                output = "On the whole, " + endDate + " was a good period as the company recorded " +
                        settings.get("defaultCurrency") + " " + last + " " +
                        settings.get("amount") + " comprehensive income.";
            }
        }
        return output;
    }


    public VBox get() {
        TableView<Item> table = new TableView<Item>();
        table.setEditable(false);
        table.getStyleClass().add("report-table");
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
        table.setItems(items);
        VBox vbox = new VBox();
        vbox.getChildren().add(table);
        return vbox;
    }

    protected TableColumn getNameCol() {
        TableColumn<Item, String> col = new TableColumn<Item, String>("Item");
        col.setMinWidth(350);
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        return col;
    }

    protected TableColumn getPeriodCol(String colname) {
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.setMinWidth(100);
        col.setCellValueFactory(cellData -> {
            Item item = (Item) cellData.getValue();
            if (item != null && item.getValues().size() > 0) {
                Double period = item.getValues().get(colname);
                if (period != null) {
                    return new SimpleStringProperty(round(period));
                }
            }
            return null;
        });
        return col;
    }

    private ObservableMap<String, Double> getValues(TableColumn.CellDataFeatures<Item, String> cellData) {
        Item item = (Item) cellData.getValue();
        if (item != null) {
            if (item.getValues().size() > 0) {
                return item.getValues();
            }
        }
        return null;
    }

    TableColumn getAbsoluteComparisonCol(String colStart, String colEnd) {
        String colname = "Absolute Change\n" + formatDate(colEnd) + " to \n" + formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.setMinWidth(150);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double colStartVAl = values.get(colStart);
                Double colEndVAl = values.get(colEnd);
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


class ItemsGetter {
    private ObservableMap<String, Double> profitLossBeforeTaxValues;
    private ObservableMap<String, Double> financeCostsValues;
    private ObservableMap<String, Double> grossProfitValues;
    private ObservableMap<String, Double> incomeTaxExpenseValues;
    private ItemsStorage storage = ItemsStorage.getInstance();
    private ArrayList<String> periods;

    ItemsGetter() {
        this.periods = new Periods().getPeriodArr();
        this.profitLossBeforeTaxValues = getVals("ProfitLossBeforeTax");
        this.financeCostsValues = getVals("FinanceCosts");
        this.grossProfitValues = getVals("GrossProfit");
        this.incomeTaxExpenseValues = getVals("IncomeTaxExpenseContinuingOperations");
    }

    Item getEbit() {
        Item itemEbit = new Item(0,
                "EBIT",
                "EBIT",
                true,
                false,
                0,
                0);
        ObservableMap<String, Double> valuesEbit = FXCollections.observableHashMap();
        for (String period : periods) {
            Double val1 = getVal(profitLossBeforeTaxValues, period);
            Double val2 = getVal(financeCostsValues, period);
            Double EbitVal = val1 + val2;
            valuesEbit.put(period, EbitVal);
        }
        itemEbit.setValues(valuesEbit);
        storage.addItem(itemEbit);

        return itemEbit;
    }

    Item getOtherIncome() {
        Item itemOtherIncome = new Item(0,
                "Other income and expenses from continuing operations, " +
                        "except finance costs and income tax expense",
                "OIEFCOEFCAITE",
                true,
                false,
                0,
                0);
        ObservableMap<String, Double> valuesItemOtherIncome = FXCollections.observableHashMap();
        for (String period : periods) {
            Double val1 = getVal(profitLossBeforeTaxValues, period);
            Double val2 = getVal(financeCostsValues, period);
            Double val3 = getVal(grossProfitValues, period);
            Double otherIncomeVal = val1 + val2 - val3;
            valuesItemOtherIncome.put(period, otherIncomeVal);
        }
        itemOtherIncome.setValues(valuesItemOtherIncome);
        storage.addItem(itemOtherIncome);
        return itemOtherIncome;
    }

    public Item getIncomeLossFromContinuingOperations() {
        Item IncomeLossFromContinuingOperations = new Item(0,
                "Income (loss) from continuing operations",
                "IncomeLossFromContinuingOperations",
                true,
                false,
                0,
                0);
        ObservableMap<String, Double> valuesIncomeLossFromContinuingOperations = FXCollections.observableHashMap();
        for (String period : periods) {
            Double val1 = getVal(profitLossBeforeTaxValues, period);
            Double val4 = getVal(incomeTaxExpenseValues, period);
            Double IncomeLossFromContinuingOperationsVal = val1 - val4;
            valuesIncomeLossFromContinuingOperations.put(period, IncomeLossFromContinuingOperationsVal);
        }
        IncomeLossFromContinuingOperations.setValues(valuesIncomeLossFromContinuingOperations);
        return IncomeLossFromContinuingOperations;
    }


    private ObservableMap<String, Double> getVals(String key) {
        Item item = storage.getItemByCode(key);
        ObservableMap<String, Double> values = FXCollections.observableHashMap();
        if (item != null) {
            values = item.getValues();
        }
        return values;
    }

    private Double getVal(ObservableMap<String, Double> vals, String period) {
        Double val = vals.get(period);
        if (val == null) val = 0.0;
        return val;
    }
}
