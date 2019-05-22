package interpreter.LiabilitiesReport;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import interpreter.AssetsReport.Outcomes.AssetStructureAnalyseEnd;
import interpreter.AssetsReport.Outcomes.AssetStructureAnalyzeStart;
import interpreter.AssetsReport.Outcomes.AssetStructureChart;
import interpreter.LiabilitiesReport.Outcomes.*;
import interpreter.ReusableComponents.IndexChangeTable;
import interpreter.ReusableComponents.RelativeItemsChange;
import interpreter.ReusableComponents.ReportHelper;
import interpreter.ReusableComponents.StructureTable;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class LiabilitiesReport extends ReportHelper {

    private ObservableList<Item> items;
    private int rootId;
    private ObservableMap<String, String> settings;
    private Periods periods;

    public LiabilitiesReport(ObservableList<Item> items, ObservableMap<String, String> settings) {
        super(items);
        this.items = items;
        this.settings = settings;
        Item root = getItemByCode("EquityAndLiabilities");
        this.rootId = (root != null) ? root.getId() : 0;
        this.periods = new Periods(settings);
    }

    public VBox getTrend() {
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
        String start = periods.getStart();
        String end = periods.getEnd();
        box.getChildren().addAll(
                tableName,
                new IndexChangeTable(
                        items,
                        periods,
                        rootId
                ).get(),
                new TotallLiabilitiesAnalyze(
                        settings,
                        getItemByCode("EquityAndLiabilities"),
                        start,
                        end
                ).get(),
                new RelativeItemsChange(
                        equityGeneral,
                        getItems(equityGeneral.getId()),
                        start,
                        end,
                        "sources"
                ).get(),
                new RelativeItemsChange(
                        liabilitiesGeneral,
                        getItemsDeep(liabilitiesGeneral.getId()),
                        start,
                        end,
                        "sources"
                ).get(),
                new LiabilitiesCharts(
                        settings,
                        periods,
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
                new LiabilitiesStructureAnalyzeStart(
                        settings,
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
                        settings,
                        equityAndLiabilities,
                        equityGeneral,
                        nonCurrentLiabilities,
                        currentLiabilities,
                        endKey
                ).get(),
                new LiabilitiesStructureAnalyzeEnd(
                        settings,
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

