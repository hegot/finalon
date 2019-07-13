package reportGeneration.interpreter.AssetsReport;

import entities.Item;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.AssetsReport.Outcomes.*;
import reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.IndexChangeTable;
import reportGeneration.interpreter.ReusableComponents.tables.StructureTable;
import reportGeneration.storage.*;

import java.util.ArrayList;

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

    private static TwoDList getTableViewValues(TableView tableView) {
        TwoDList values = new TwoDList();

        ObservableList<TableColumn> columns = tableView.getColumns();
        for (Object row : tableView.getItems()) {
            ArrayList<String> rowVals = new ArrayList<>();
            for (TableColumn column : columns) {
                if (column.getCellObservableValue(row) != null) {
                    rowVals.add((String) column.getCellObservableValue(row).getValue());
                } else {
                    rowVals.add("");
                }
            }
            values.addList(rowVals);
        }
        return values;
    }

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
        String tblName = "Table 1. Assets Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency");
        ResultsStorage.addStr("h2", "tablename1", tblName);
        Label tableName = tableName(tblName);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");

        TableView<Item> tbl = new IndexChangeTable(rootId).get();

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
                ).get(),
                new RelativeItemsChange(
                        GeneralCurrentAssets,
                        stor.getItems(GeneralCurrentAssets.getId()),
                        "assets"
                ).get()
        );

        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable("AssetsTrend", items);

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

