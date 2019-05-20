package interpreter.AssetsReport;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import interpreter.AssetsReport.Outcomes.*;
import interpreter.ReportHelper;
import interpreter.ReusableComponents.IndexChangeTable;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AssetsReport extends ReportHelper {

    private ObservableList<Item> items;
    private int rootId;
    private ObservableMap<String, String> settings;
    private Periods periods;

    public AssetsReport(ObservableList<Item> items, ObservableMap<String, String> settings) {
        super(items);
        this.items = items;
        this.settings = settings;
        Item root = getItemByCode("AssetsGeneral");
        this.rootId = (root != null) ? root.getId() : 0;
        this.periods = new Periods(settings);
    }

    public VBox get() {
        Label tableName = new Label("Table 1. Assets Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency")
        );
        tableName.getStyleClass().add("assets-table-name");
        tableName.setWrapText(true);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Item currentAssets = getItemByCode("GeneralCurrentAssets");
        Item nonCurrentAssets = getItemByCode("NonCurrentAssets");
        String start = periods.getStart();
        String end = periods.getEnd();
        box.getChildren().addAll(
                tableName,
                new IndexChangeTable(
                        items,
                        periods,
                        rootId
                ).get(),
                new TotallAssetsAnalyze(
                        settings,
                        getItemByCode("AssetsGeneral"),
                        start,
                        end
                ).get(),
                new CurrentNonCurrentAssetsAnalyze(
                        currentAssets,
                        nonCurrentAssets,
                        start,
                        end
                ).get(),
                new AssetsCharts(
                        settings,
                        periods,
                        currentAssets,
                        nonCurrentAssets
                ).get(),
                new RelativeAssetsChange(
                        nonCurrentAssets,
                        getItems(nonCurrentAssets.getId()),
                        start,
                        end
                ).get(),
                new RelativeAssetsChange(
                        currentAssets,
                        getItems(currentAssets.getId()),
                        start,
                        end
                ).get()
        );
        return box;
    }
}

