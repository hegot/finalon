package reportGeneration.interpreter.LiabilitiesReport;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.Periods;
import reportGeneration.SettingsStorage;
import reportGeneration.interpreter.LiabilitiesReport.Outcomes.*;
import reportGeneration.interpreter.ReusableComponents.IndexChangeTable;
import reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import reportGeneration.interpreter.ReusableComponents.ReportHelper;
import reportGeneration.interpreter.ReusableComponents.StructureTable;

import java.util.ArrayList;

public class LiabilitiesReport extends ReportHelper {

    private int rootId;

    public LiabilitiesReport() {
        Item root = getItemByCode("EquityAndLiabilities");
        this.rootId = (root != null) ? root.getId() : 0;
    }

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        Label tableName = new Label("Table 2. Sources of Finance (Equity and Liabilities) Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency")
        );
        tableName.getStyleClass().add("assets-table-name");
        tableName.setWrapText(true);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Item equityGeneral = getItemByCode("EquityGeneral");
        Item liabilitiesGeneral = getItemByCode("LiabilitiesGeneral");
        Item liabilitiesCurent = getItemByCode("CurrentLiabilities");
        Item liabilitiesNonCurrent = getItemByCode("NonCurrentLiabilities");
        box.getChildren().addAll(
                tableName,
                new IndexChangeTable(
                        rootId
                ).get(),
                new TotallLiabilitiesAnalyze(
                        getItemByCode("EquityAndLiabilities")
                ).get(),
                new RelativeItemsChange(
                        equityGeneral,
                        getItems(equityGeneral.getId()),
                        "sources"
                ).get(),
                new RelativeItemsChange(
                        liabilitiesGeneral,
                        getItemsDeep(liabilitiesGeneral.getId()),
                        "sources"
                ).get(),
                new LiabilitiesCharts(
                        liabilitiesCurent,
                        liabilitiesNonCurrent,
                        equityGeneral
                ).get()
        );
        return box;
    }

    public VBox getStructure() {
        Label tableName = new Label("Table 4. Equity and Liabilities Structure Analysis");
        tableName.getStyleClass().add("assets-table-name");
        tableName.setWrapText(true);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Item equityAndLiabilities = getItemByCode("EquityAndLiabilities");
        Item equityGeneral = getItemByCode("EquityGeneral");
        Item nonCurrentLiabilities = getItemByCode("NonCurrentLiabilities");
        Item currentLiabilities = getItemByCode("CurrentLiabilities");
        ArrayList<String> periodArr = Periods.getInstance().getPeriodArr();
        String startKey = periodArr.get(0);
        String endKey = periodArr.get(periodArr.size() - 1);
        box.getChildren().addAll(
                tableName,
                new StructureTable(
                        rootId
                ).get(),
                new LiabilitiesStructureAnalyzeStart(
                        equityAndLiabilities,
                        equityGeneral,
                        nonCurrentLiabilities,
                        currentLiabilities,
                        getItems(equityGeneral.getId()),
                        getItems(nonCurrentLiabilities.getId()),
                        getItems(currentLiabilities.getId()),
                        startKey
                ).get(),
                new LiabilitiesStructureChart(
                        equityAndLiabilities,
                        equityGeneral,
                        nonCurrentLiabilities,
                        currentLiabilities,
                        endKey
                ).get(),
                new LiabilitiesStructureAnalyzeEnd(
                        equityAndLiabilities,
                        equityGeneral,
                        nonCurrentLiabilities,
                        currentLiabilities,
                        getItems(equityGeneral.getId()),
                        getItems(nonCurrentLiabilities.getId()),
                        getItems(currentLiabilities.getId()),
                        startKey
                ).get()
        );
        return box;
    }
}

