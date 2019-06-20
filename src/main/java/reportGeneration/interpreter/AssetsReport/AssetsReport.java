package reportGeneration.interpreter.AssetsReport;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.AssetsReport.Outcomes.*;
import reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.IndexChangeTable;
import reportGeneration.interpreter.ReusableComponents.tables.StructureTable;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

public class AssetsReport implements TableName {

    private int rootId;
    private ItemsStorage stor = ItemsStorage.getInstance();
    private Item NonCurrentAssets;
    private Item GeneralCurrentAssets;

    public AssetsReport() {
        Item root = stor.get("AssetsGeneral");
        this.rootId = (root != null) ? root.getId() : 0;
        this.NonCurrentAssets = stor.get("NonCurrentAssets");
        this.GeneralCurrentAssets = stor.get("GeneralCurrentAssets");
    }

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
        Label tableName = tableName("Table 1. Assets Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency")
        );
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        box.getChildren().addAll(
                tableName,
                new IndexChangeTable(rootId).get(),
                new TotallAssetsAnalyze().get(),
                new CurrentNonCurrentAssetsAnalyze().get(),
                new AssetsCharts().get(),
                new RelativeItemsChange(
                        NonCurrentAssets,
                        stor.getItems(NonCurrentAssets.getId()),
                        "assets"
                ).get(),
                new RelativeItemsChange(
                        GeneralCurrentAssets,
                        stor.getItems(GeneralCurrentAssets.getId()),
                        "assets"
                ).get()
        );
        return box;
    }

    public VBox getStructure() {
        Label tableName = tableName("Table 3. Assets Structure Analysis");
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Periods p = Periods.getInstance();
        box.getChildren().addAll(
                tableName,
                new StructureTable(rootId).get(),
                new AssetStructureAnalyzeStart(
                        stor.getItems(GeneralCurrentAssets.getId()),
                        stor.getItems(NonCurrentAssets.getId())
                ).get(),
                new AssetStructureChart(p.endKey()).get(),
                new AssetStructureAnalyseEnd(
                        stor.getItems(GeneralCurrentAssets.getId()),
                        stor.getItems(NonCurrentAssets.getId())
                ).get()
        );
        return box;
    }
}

