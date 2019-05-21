package interpreter.LiabilitiesTrend;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import interpreter.LiabilitiesTrend.Outcomes.LiabilitiesCharts;
import interpreter.LiabilitiesTrend.Outcomes.TotallLiabilitiesAnalyze;
import interpreter.ReportHelper;
import interpreter.ReusableComponents.IndexChangeTable;
import interpreter.ReusableComponents.RelativeItemsChange;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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

    public VBox get() {
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


}

