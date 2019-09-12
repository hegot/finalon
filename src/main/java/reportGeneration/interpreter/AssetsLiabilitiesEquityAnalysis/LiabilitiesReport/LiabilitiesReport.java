package reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.LiabilitiesReport;

import entities.Item;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.LiabilitiesReport.Outcomes.*;
import reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.IndexChangeTable;
import reportGeneration.interpreter.ReusableComponents.tables.StructureItem;
import reportGeneration.interpreter.ReusableComponents.tables.StructureTable;
import reportGeneration.storage.*;

public class LiabilitiesReport {

    private int rootId;
    private Item root;

    public LiabilitiesReport() {
        this.root = ItemsStorage.get("EquityAndLiabilities");
        this.rootId = (root != null) ? root.getId() : 0;
    }

    public VBox getTrend(int weight) {
        String tblName = "Table 2. Sources of Finance (Equity and Liabilities) Trend Analysis, in "
                + SettingsStorage.get("amount") + " " + SettingsStorage.get("defaultCurrency");
        Label tableName = TableName.name(tblName);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Item equityGeneral = ItemsStorage.get("EquityGeneral");
        Item liabilitiesGeneral = ItemsStorage.get("LiabilitiesGeneral");

        TableView<Item> tbl = new IndexChangeTable(rootId).get();
        TwoDList items = TableName.getTableViewValues(tbl);
        ResultsStorage.addTable(++weight, items, tblName);

        box.getChildren().addAll(
                tableName,
                tbl,
                new TotallLiabilitiesAnalyze().get(++weight),
                new RelativeItemsChange(
                        equityGeneral,
                        ItemsStorage.getItems(equityGeneral.getId()),
                        "sources"
                ).get(++weight),
                new LiabilitiesCharts().get(++weight),
                new RelativeItemsChange(
                        liabilitiesGeneral,
                        ItemsStorage.getItemsDeep(liabilitiesGeneral.getId()),
                        "sources"
                ).get(++weight)
        );
        return box;
    }

    public VBox getStructure(int weight) {
        String title = "Table 4. Equity and Liabilities Structure Analysis %";
        Label tableName = TableName.name(title);
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");

        TableView<StructureItem> tbl = new StructureTable(root).get();
        TwoDList items = TableName.getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items, title);

        box.getChildren().addAll(
                tableName,
                tbl,
                new LiabilitiesStructureAnalyzeStart().get(++weight),
                new LiabilitiesStructureChart(Periods.endKey()).get(++weight),
                new LiabilitiesStructureAnalyzeEnd().get(++weight)
        );
        return box;
    }
}

