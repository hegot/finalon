package interpreter.AssetsReport;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import interpreter.AssetsReport.Outcomes.*;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AssetsReport {

    private ObservableList<Item> items;
    private int rootId;
    private ObservableMap<String, String> settings;
    private Periods periods;

    public AssetsReport(ObservableList<Item> items, ObservableMap<String, String> settings) {
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
        Label tableName = new Label("Table 1. Assets Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency")
        );
        tableName.getStyleClass().add("assets-table-name");
        tableName.setWrapText(true);
        VBox box = new VBox(5);
        Item currentAssets = getItemByCode("GeneralCurrentAssets");
        Item nonCurrentAssets = getItemByCode("NonCurrentAssets");
        String start = periods.getStart();
        String end = periods.getEnd();
        box.getChildren().addAll(
                label,
                tableName,
                new AssetsReportTable(items, periods, rootId).get(),
                new TotallAssetsAnalyze(
                        getItemByCode("AssetsGeneral"),
                        start,
                        end
                ).get(),
                new CurrentNonCurrentAssetsAnalyze(
                        currentAssets,
                        nonCurrentAssets,
                        start,
                        end
                ).get(),
                new AssetsCharts(
                        settings,
                        periods,
                        currentAssets,
                        nonCurrentAssets
                ).get()
        );
        return box;
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

