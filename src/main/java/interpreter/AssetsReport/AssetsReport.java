package interpreter.AssetsReport;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.ItemsTable;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class AssetsReport extends ItemsTable {

    private ObservableList<Item> items;
    private int rootId;
    private ArrayList<TreeItem> roots;
    private ObservableMap<String, String> settings;
    private Periods periods;

    public AssetsReport(ObservableList<Item> items, ObservableMap<String, String> settings) {
        super(items);
        this.items = items;
        this.roots = new ArrayList<>();
        this.settings = settings;
        this.rootId = getRoot();
        this.periods = new Periods(settings);
    }

    public VBox get() {
        String companyName = settings.get("company") + "'s";
        Label title = new Label(
                "Analysis of " + companyName
                        + " financial statements for the period from "
                        + periods.getStart() + " to "
                        + periods.getEnd());
        Label text1 = new Label("This report analyzes the balance sheets and income statements of " + companyName
                + ". Trends for the major balance sheet and income statement items and ratio analysis are used " +
                "to understand the financial position and financial effectiveness of the company. " +
                "The report studied the " + periods.getStart() + " - " + periods.getEnd() + " period.");
        Label text2 = new Label("1. The Common-Size Analysis of the Assets, Liabilities and Shareholders' Equity ");
        Label text3 = new Label("Table 1. Assets Trend Analysis, in currency");
        VBox box = new VBox(20);
        box.getChildren().addAll(title, text1, text2, text3, getAssetsReportTable());
        return box;
    }


    private TreeTableView<Item> getAssetsReportTable() {
        TreeTableView<Item> table = getTable(rootId);
        table.getStyleClass().add("assets-report");
        table.setPrefWidth(880);
        Columns cols = new Columns(settings);
        table.getColumns().addAll(cols.getNameCol());
        ArrayList<String> arr = periods.getPeriodArr();
        for (String col : arr) {
            table.getColumns().add(cols.getPeriodCol(col));
        }
        int count = arr.size() - 1;
        if (count > 0) {
            for (int j = 0; j < count; j++) {
                String colStart = arr.get(j);
                String colEnd = arr.get(j + 1);
                table.getColumns().add(cols.getAbsoluteComparisonCol(colStart, colEnd));
            }
            for (int j = 0; j < count; j++) {
                String colStart = arr.get(j);
                String colEnd = arr.get(j + 1);
                table.getColumns().add(cols.getRelativeComparisonCol(colStart, colEnd));
            }
        }

        return table;
    }

    private int getRoot() {
        for (Item item : this.items) {
            if (item.getShortName().equals("AssetsGeneral")) {
                return item.getId();
            }
        }
        return 0;
    }


}

