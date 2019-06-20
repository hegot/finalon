package reportGeneration.interpreter.LiabilitiesReport;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.LiabilitiesReport.Outcomes.*;
import reportGeneration.interpreter.ReusableComponents.RelativeItemsChange;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.IndexChangeTable;
import reportGeneration.interpreter.ReusableComponents.tables.StructureTable;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class LiabilitiesReport  implements TableName {

    private int rootId;
    private ItemsStorage stor = ItemsStorage.getInstance();

    public LiabilitiesReport() {
        Item root = stor.get("EquityAndLiabilities");
        this.rootId = (root != null) ? root.getId() : 0;
    }

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
        Label tableName =  tableName("Table 2. Sources of Finance (Equity and Liabilities) Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency")
        );
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        ItemsStorage stor = ItemsStorage.getInstance();
        Item equityGeneral = stor.get("EquityGeneral");
        Item liabilitiesGeneral = stor.get("LiabilitiesGeneral");
        box.getChildren().addAll(
                tableName,
                new IndexChangeTable(rootId).get(),
                new TotallLiabilitiesAnalyze().get(),
                new RelativeItemsChange(
                        equityGeneral,
                        stor.getItems(equityGeneral.getId()),
                        "sources"
                ).get(),
                new RelativeItemsChange(
                        liabilitiesGeneral,
                        stor.getItemsDeep(liabilitiesGeneral.getId()),
                        "sources"
                ).get(),
                new LiabilitiesCharts().get()
        );
        return box;
    }

    public VBox getStructure() {
        Label tableName = tableName("Table 4. Equity and Liabilities Structure Analysis");
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        Periods p = Periods.getInstance();
        box.getChildren().addAll(
                tableName,
                new StructureTable(rootId).get(),
                new LiabilitiesStructureAnalyzeStart().get(),
                new LiabilitiesStructureChart(p.endKey()).get(),
                new LiabilitiesStructureAnalyzeEnd().get()
        );
        return box;
    }
}

