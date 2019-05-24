package reportGeneration.interpreter.AssetsReport;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.IndexesStorage;
import reportGeneration.Periods;
import reportGeneration.SettingsStorage;
import reportGeneration.interpreter.AssetsReport.Outcomes.*;
import reportGeneration.interpreter.ReusableComponents.IndexChangeTable;
import reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import reportGeneration.interpreter.ReusableComponents.ReportHelper;
import reportGeneration.interpreter.ReusableComponents.StructureTable;

import java.util.ArrayList;

public class AssetsReport extends ReportHelper {

    private int rootId;

    public AssetsReport() {
        Item root = getItemByCode("AssetsGeneral");
        this.rootId = (root != null) ? root.getId() : 0;
        IndexesStorage.put("GeneralCurrentAssets", getItemByCode("GeneralCurrentAssets"));
        IndexesStorage.put("NonCurrentAssets", getItemByCode("NonCurrentAssets"));
        IndexesStorage.put("AssetsGeneral", getItemByCode("AssetsGeneral"));
    }

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        Label tableName = new Label("Table 1. Assets Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency")
        );
        tableName.getStyleClass().add("assets-table-name");
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
                        getItems(nonCurrentAssets.getId()),
                        "assets"
                ).get(),
                new RelativeItemsChange(
                        currentAssets,
                        getItems(currentAssets.getId()),
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
        ArrayList<String> periodArr = Periods.getInstance().getPeriodArr();
        String startKey = periodArr.get(0);
        String endKey = periodArr.get(periodArr.size() - 1);
        Item nonCurrentAssets = IndexesStorage.get("NonCurrentAssets");
        Item currentAssets = IndexesStorage.get("GeneralCurrentAssets");
        box.getChildren().addAll(
                tableName,
                new StructureTable(rootId).get(),
                new AssetStructureAnalyzeStart(
                        getItems(currentAssets.getId()),
                        getItems(nonCurrentAssets.getId()),
                        startKey
                ).get(),
                new AssetStructureChart(endKey).get(),
                new AssetStructureAnalyseEnd(
                        getItems(currentAssets.getId()),
                        getItems(nonCurrentAssets.getId()),
                        endKey
                ).get()
        );
        return box;
    }
}

