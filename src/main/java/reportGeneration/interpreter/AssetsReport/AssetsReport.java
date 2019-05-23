package reportGeneration.interpreter.AssetsReport;

import entities.Item;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
    private Item currentAssets;
    private Item nonCurrentAssets;
    private Item assetsGeneral;

    public AssetsReport() {
        Item root = getItemByCode("AssetsGeneral");
        this.rootId = (root != null) ? root.getId() : 0;
        this.currentAssets = getItemByCode("GeneralCurrentAssets");
        this.nonCurrentAssets = getItemByCode("NonCurrentAssets");
        this.assetsGeneral = getItemByCode("AssetsGeneral");
    }

    public VBox getTrend() {
        Label tableName = new Label("Table 1. Assets Trend Analysis, in "
                + SettingsStorage.getSettings().get("amount") + " " + SettingsStorage.getSettings().get("defaultCurrency")
        );
        tableName.getStyleClass().add("assets-table-name");
        tableName.setWrapText(true);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        box.getChildren().addAll(
                tableName,
                new IndexChangeTable(
                        rootId
                ).get(),
                new TotallAssetsAnalyze(
                        assetsGeneral
                ).get(),
                new CurrentNonCurrentAssetsAnalyze(
                        currentAssets,
                        nonCurrentAssets
                ).get(),
                new AssetsCharts(
                        currentAssets,
                        nonCurrentAssets
                ).get(),
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
        box.getChildren().addAll(
                tableName,
                new StructureTable(
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

