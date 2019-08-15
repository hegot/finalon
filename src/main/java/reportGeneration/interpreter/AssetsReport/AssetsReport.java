package reportGeneration.interpreter.AssetsReport;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.AssetsReport.Outcomes.*;
import reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.IndexChangeTable;
import reportGeneration.interpreter.ReusableComponents.tables.StructureItem;
import reportGeneration.interpreter.ReusableComponents.tables.StructureTable;
import reportGeneration.storage.*;

public class AssetsReport implements TableName {

    private Item root;
    private int rootId;
    private Item NonCurrentAssets;
    private Item GeneralCurrentAssets;

    public AssetsReport() {
        this.root = ItemsStorage.get("AssetsGeneral");
        this.rootId = (root != null) ? root.getId() : 0;
        this.NonCurrentAssets = ItemsStorage.get("NonCurrentAssets");
        this.GeneralCurrentAssets = ItemsStorage.get("GeneralCurrentAssets");
    }

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        String tblName = "Table 1. Assets Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency");
        ResultsStorage.addStr(3, "tableName", tblName);
        Label tableName = tableName(tblName);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");

        TableView<Item> tbl = new IndexChangeTable(rootId).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(4, items);

        box.getChildren().addAll(
                tableName,
                tbl,
                new TotallAssetsAnalyze().get(),
                new CurrentNonCurrentAssetsAnalyze().get(),
                new AssetsCharts().get(),
                new RelativeItemsChange(
                        NonCurrentAssets,
                        ItemsStorage.getItems(NonCurrentAssets.getId()),
                        "assets"
                ).get(9),
                new RelativeItemsChange(
                        GeneralCurrentAssets,
                        ItemsStorage.getItems(GeneralCurrentAssets.getId()),
                        "assets"
                ).get(10)
        );
        return box;
    }

    public VBox getStructure() {
        String tblName = "Table 3. Assets Structure Analysis %";
        Label tableName = tableName(tblName);
        ResultsStorage.addStr(11, "tableName", tblName);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        TableView<StructureItem> tbl = new StructureTable(root).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(12, items);
        box.getChildren().addAll(
                tableName,
                tbl,
                new AssetStructureAnalyzeStart(
                        ItemsStorage.getItems(GeneralCurrentAssets.getId()),
                        ItemsStorage.getItems(NonCurrentAssets.getId())
                ).get(),
                new AssetStructureChart(Periods.endKey()).get(),
                new AssetStructureAnalyseEnd(
                        ItemsStorage.getItems(GeneralCurrentAssets.getId()),
                        ItemsStorage.getItems(NonCurrentAssets.getId())
                ).get()
        );
        return box;
    }
}

