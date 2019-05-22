package interpreter.AssetsReport;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import interpreter.AssetsReport.Outcomes.*;
import interpreter.ReusableComponents.IndexChangeTable;
import interpreter.ReusableComponents.RelativeItemsChange;
import interpreter.ReusableComponents.ReportHelper;
import interpreter.ReusableComponents.StructureTable;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class AssetsReport extends ReportHelper {

    private ObservableList<Item> items;
    private int rootId;
    private ObservableMap<String, String> settings;
    private Periods periods;
    private Item currentAssets;
    private Item nonCurrentAssets;
    private String start;
    private String end;
    private Item assetsGeneral;

    public AssetsReport(ObservableList<Item> items, ObservableMap<String, String> settings) {
        super(items);
        this.items = items;
        this.settings = settings;
        Item root = getItemByCode("AssetsGeneral");
        this.rootId = (root != null) ? root.getId() : 0;
        this.periods = new Periods(settings);
        this.currentAssets = getItemByCode("GeneralCurrentAssets");
        this.nonCurrentAssets = getItemByCode("NonCurrentAssets");
        this.start = periods.getStart();
        this.end = periods.getEnd();
        this.assetsGeneral = getItemByCode("AssetsGeneral");
    }

    public VBox getTrend() {
        Label tableName = new Label("Table 1. Assets Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency")
        );
        tableName.getStyleClass().add("assets-table-name");
        tableName.setWrapText(true);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        box.getChildren().addAll(
                tableName,
                new IndexChangeTable(
                        items,
                        periods,
                        rootId
                ).get(),
                new TotallAssetsAnalyze(
                        settings,
                        assetsGeneral,
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
                ).get(),
                new RelativeItemsChange(
                        nonCurrentAssets,
                        getItems(nonCurrentAssets.getId()),
                        start,
                        end,
                        "assets"
                ).get(),
                new RelativeItemsChange(
                        currentAssets,
                        getItems(currentAssets.getId()),
                        start,
                        end,
                        "assets"
                ).get()

        );
        return box;
    }

    public VBox getStructure() {
        Label tableName = new Label("Table 3. Assets Structure Analysis");
        tableName.getStyleClass().add("assets-table-name");
        tableName.setWrapText(true);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Item assetsGeneral = getItemByCode("AssetsGeneral");
        Item currentAssets = getItemByCode("GeneralCurrentAssets");
        Item nonCurrentAssets = getItemByCode("NonCurrentAssets");
        ArrayList<String> periodArr = periods.getPeriodArr();
        String startKey = periodArr.get(0);
        String endKey = periodArr.get(periodArr.size() - 1);
        box.getChildren().addAll(
                tableName,
                new StructureTable(
                        items,
                        periods,
                        rootId
                ).get(),
                new AssetStructureAnalyzeStart(
                        assetsGeneral,
                        currentAssets,
                        nonCurrentAssets,
                        getItems(currentAssets.getId()),
                        getItems(nonCurrentAssets.getId()),
                        startKey
                ).get(),
                new AssetStructureChart(
                        settings,
                        assetsGeneral,
                        currentAssets,
                        nonCurrentAssets,
                        endKey
                ).get(),
                new AssetStructureAnalyseEnd(
                        assetsGeneral,
                        currentAssets,
                        nonCurrentAssets,
                        getItems(currentAssets.getId()),
                        getItems(nonCurrentAssets.getId()),
                        endKey
                ).get()
        );
        return box;
    }
}

