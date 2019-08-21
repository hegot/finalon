package finalon.reportGeneration.interpreter.LiabilitiesReport;

import finalon.entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import finalon.reportGeneration.interpreter.LiabilitiesReport.Outcomes.*;
import finalon.reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.IndexChangeTable;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.StructureItem;
import finalon.reportGeneration.interpreter.ReusableComponents.tables.StructureTable;
import finalon.reportGeneration.storage.*;

public class LiabilitiesReport implements TableName {

    private int rootId;
    private Item root;

    public LiabilitiesReport() {
        this.root = ItemsStorage.get("EquityAndLiabilities");
        this.rootId = (root != null) ? root.getId() : 0;
    }

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        String tblName = "Table 2. Sources of Finance (Equity and Liabilities) Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency");
        Label tableName = tableName(tblName);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Item equityGeneral = ItemsStorage.get("EquityGeneral");
        Item liabilitiesGeneral = ItemsStorage.get("LiabilitiesGeneral");

        TableView<Item> tbl = new IndexChangeTable(rootId).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(20, items, tblName);

        box.getChildren().addAll(
                tableName,
                tbl,
                new TotallLiabilitiesAnalyze().get(),
                new RelativeItemsChange(
                        equityGeneral,
                        ItemsStorage.getItems(equityGeneral.getId()),
                        "sources"
                ).get(22),
                new RelativeItemsChange(
                        liabilitiesGeneral,
                        ItemsStorage.getItemsDeep(liabilitiesGeneral.getId()),
                        "sources"
                ).get(23),
                new LiabilitiesCharts().get()
        );
        return box;
    }

    public VBox getStructure() {
        String title = "Table 4. Equity and Liabilities Structure Analysis %";
        Label tableName = tableName(title);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");

        TableView<StructureItem> tbl = new StructureTable(root).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(27, items, title);

        box.getChildren().addAll(
                tableName,
                tbl,
                new LiabilitiesStructureAnalyzeStart().get(),
                new LiabilitiesStructureChart(Periods.endKey()).get(),
                new LiabilitiesStructureAnalyzeEnd().get()
        );
        return box;
    }
}

