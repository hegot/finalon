package reportGeneration.interpreter.AssetsReport;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.AssetsReport.Outcomes.*;
import reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import reportGeneration.interpreter.ReusableComponents.tables.IndexChangeTable;
import reportGeneration.interpreter.ReusableComponents.tables.StructureTable;
import reportGeneration.storage.IndexesStorage;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class AssetsReport {

    private int rootId;
    private ItemsStorage stor = ItemsStorage.getInstance();

    public AssetsReport() {
        Item root = stor.getItemByCode("AssetsGeneral");
        this.rootId = (root != null) ? root.getId() : 0;
        IndexesStorage.put("GeneralCurrentAssets", stor.getItemByCode("GeneralCurrentAssets"));
        IndexesStorage.put("NonCurrentAssets", stor.getItemByCode("NonCurrentAssets"));
        IndexesStorage.put("AssetsGeneral", stor.getItemByCode("AssetsGeneral"));
    }

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
        Label tableName = new Label("Table 1. Assets Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency")
        );
        tableName.getStyleClass().add("table-name");
        tableName.setWrapText(true);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Item nonCurrentAssets = IndexesStorage.get("NonCurrentAssets");
        Item currentAssets = IndexesStorage.get("GeneralCurrentAssets");
        box.getChildren().addAll(
                tableName,
                new IndexChangeTable(rootId).get(),
                new TotallAssetsAnalyze().get(),
                new CurrentNonCurrentAssetsAnalyze().get(),
                new AssetsCharts().get(),
                new RelativeItemsChange(
                        nonCurrentAssets,
                        stor.getItems(nonCurrentAssets.getId()),
                        "assets"
                ).get(),
                new RelativeItemsChange(
                        currentAssets,
                        stor.getItems(currentAssets.getId()),
                        "assets"
                ).get()
        );
        return box;
    }

    public VBox getStructure() {
        Label tableName = new Label("Table 3. Assets Structure Analysis");
        tableName.getStyleClass().add("table-name");
        tableName.setWrapText(true);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Periods per = Periods.getInstance();
        ArrayList<String> periodArr = per.getPeriodArr();
        String startKey = periodArr.get(0);
        String endKey = periodArr.get(periodArr.size() - 1);
        Item nonCurrentAssets = IndexesStorage.get("NonCurrentAssets");
        Item currentAssets = IndexesStorage.get("GeneralCurrentAssets");
        box.getChildren().addAll(
                tableName,
                new StructureTable(rootId).get(),
                new AssetStructureAnalyzeStart(
                        stor.getItems(currentAssets.getId()),
                        stor.getItems(nonCurrentAssets.getId()),
                        startKey
                ).get(),
                new AssetStructureChart(endKey).get(),
                new AssetStructureAnalyseEnd(
                        stor.getItems(currentAssets.getId()),
                        stor.getItems(nonCurrentAssets.getId()),
                        endKey
                ).get()
        );
        return box;
    }
}

