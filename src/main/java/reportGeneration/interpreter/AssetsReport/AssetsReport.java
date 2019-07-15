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
import reportGeneration.interpreter.ReusableComponents.tables.StructureTable;
import reportGeneration.storage.*;

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
        String tblName = "Table 1. Assets Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency");
        ResultsStorage.addStr(3, "h2", tblName);
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
                        stor.getItems(NonCurrentAssets.getId()),
                        "assets"
                ).get(9),
                new RelativeItemsChange(
                        GeneralCurrentAssets,
                        stor.getItems(GeneralCurrentAssets.getId()),
                        "assets"
                ).get(10)
        );
        return box;
    }

    public VBox getStructure() {
        String tblName = "Table 3. Assets Structure Analysis";
        Label tableName = tableName(tblName);
        ResultsStorage.addStr(11, "h2", tblName);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Periods p = Periods.getInstance();
        TableView<Item> tbl = new StructureTable(rootId).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(12, items);
        box.getChildren().addAll(
                tableName,
                tbl,
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

