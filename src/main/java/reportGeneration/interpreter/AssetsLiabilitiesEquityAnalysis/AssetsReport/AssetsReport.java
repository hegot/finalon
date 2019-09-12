package reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.AssetsReport;

import entities.Item;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.AssetsReport.Outcomes.*;
import reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.IndexChangeTable;
import reportGeneration.interpreter.ReusableComponents.tables.StructureItem;
import reportGeneration.interpreter.ReusableComponents.tables.StructureTable;
import reportGeneration.storage.*;

public class AssetsReport {

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

    public VBox getTrend(int weight) {
        String tblName = "Table 1. Assets Trend Analysis, in "
                + SettingsStorage.get("amount") + " " + SettingsStorage.get("defaultCurrency");
        Label tableName = TableName.name(tblName);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");

        TableView<Item> tbl = new IndexChangeTable(rootId).get();
        TwoDList items = TableName.getTableViewValues(tbl);
        ResultsStorage.addTable(++weight, items, tblName);

        box.getChildren().addAll(
                tableName,
                tbl,
                new TotallAssetsAnalyze().get(++weight),
                new CurrentNonCurrentAssetsAnalyze().get(++weight),
                new AssetsCharts().get(++weight),
                new RelativeItemsChange(
                        NonCurrentAssets,
                        ItemsStorage.getItems(NonCurrentAssets.getId()),
                        "assets"
                ).get(++weight),
                new RelativeItemsChange(
                        GeneralCurrentAssets,
                        ItemsStorage.getItems(GeneralCurrentAssets.getId()),
                        "assets"
                ).get(++weight)
        );
        return box;
    }

    public VBox getStructure(int weight) {
        String tblName = "Table 3. Assets Structure Analysis %";
        Label tableName = TableName.name(tblName);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        TableView<StructureItem> tbl = new StructureTable(root).get();
        TwoDList items = TableName.getTableViewValues(tbl);
        ResultsStorage.addTable(++weight, items, tblName);
        box.getChildren().addAll(
                tableName,
                tbl,
                new AssetStructureAnalyzeStart(
                        ItemsStorage.getItems(GeneralCurrentAssets.getId()),
                        ItemsStorage.getItems(NonCurrentAssets.getId())
                ).get(++weight),
                new AssetStructureChart(Periods.endKey()).get(++weight),
                new AssetStructureAnalyseEnd(
                        ItemsStorage.getItems(GeneralCurrentAssets.getId()),
                        ItemsStorage.getItems(NonCurrentAssets.getId())
                ).get(++weight)
        );
        return box;
    }
}

