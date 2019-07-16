package reportGeneration.interpreter.LiabilitiesReport;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.LiabilitiesReport.Outcomes.*;
import reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.IndexChangeTable;
import reportGeneration.interpreter.ReusableComponents.tables.StructureTable;
import reportGeneration.storage.*;

public class LiabilitiesReport implements TableName {

    private int rootId;
    private ItemsStorage stor = ItemsStorage.getInstance();

    public LiabilitiesReport() {
        Item root = stor.get("EquityAndLiabilities");
        this.rootId = (root != null) ? root.getId() : 0;
    }

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
        String tblName = "Table 2. Sources of Finance (Equity and Liabilities) Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency");
        Label tableName = tableName(tblName);
        ResultsStorage.addStr(19, "tableName", tblName);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        ItemsStorage stor = ItemsStorage.getInstance();
        Item equityGeneral = stor.get("EquityGeneral");
        Item liabilitiesGeneral = stor.get("LiabilitiesGeneral");

        TableView<Item> tbl = new IndexChangeTable(rootId).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(20, items);

        box.getChildren().addAll(
                tableName,
                tbl,
                new TotallLiabilitiesAnalyze().get(),
                new RelativeItemsChange(
                        equityGeneral,
                        stor.getItems(equityGeneral.getId()),
                        "sources"
                ).get(22),
                new RelativeItemsChange(
                        liabilitiesGeneral,
                        stor.getItemsDeep(liabilitiesGeneral.getId()),
                        "sources"
                ).get(23),
                new LiabilitiesCharts().get()
        );
        return box;
    }

    public VBox getStructure() {
        String title = "Table 4. Equity and Liabilities Structure Analysis";
        Label tableName = tableName(title);
        ResultsStorage.addStr(26, "tableName", title);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Periods p = Periods.getInstance();

        TableView<Item> tbl = new StructureTable(rootId).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(27, items);

        box.getChildren().addAll(
                tableName,
                tbl,
                new LiabilitiesStructureAnalyzeStart().get(),
                new LiabilitiesStructureChart(p.endKey()).get(),
                new LiabilitiesStructureAnalyzeEnd().get()
        );
        return box;
    }
}

