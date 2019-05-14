package interpreter.AssetsReport;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.ItemsTable;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import interpreter.AssetsReport.Outcomes.TotallAssetsAnalyze;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class AssetsReport extends ItemsTable {

    private ObservableList<Item> items;
    private int rootId;
    private ObservableMap<String, String> settings;
    private Periods periods;

    public AssetsReport(ObservableList<Item> items, ObservableMap<String, String> settings) {
        super(items);
        this.items = items;
        this.settings = settings;
        Item root = getItemByCode("AssetsGeneral");
        this.rootId = (root != null) ? root.getId() : 0;
        this.periods = new Periods(settings);
    }

    public VBox get() {

        Label label = new Label("1. The Common-Size Analysis of the Assets, Liabilities and Shareholders' Equity ");
        label.getStyleClass().add("assets-label");
        label.setWrapText(true);
        Label tableName = new Label("Table 1. Assets Trend Analysis, in currency");
        tableName.getStyleClass().add("assets-table-name");
        tableName.setWrapText(true);
        VBox box = new VBox(10);
        box.getChildren().addAll(
                label,
                tableName,
                getAssetsReportTable(),
                new TotallAssetsAnalyze(getItemByCode("AssetsGeneral")).get()
        );
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


    private Item getItemByCode(String code) {
        for (Item item : this.items) {
            if (item.getShortName().equals(code)) {
                return item;
            }
        }
        return null;
    }
}

