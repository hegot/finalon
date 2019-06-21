package reportGeneration.interpreter.FinancialResults;

import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.FinancialResults.Outcomes.CostOfGoods;
import reportGeneration.interpreter.FinancialResults.Outcomes.FinancialResultTable;
import reportGeneration.interpreter.FinancialResults.Outcomes.FinancialResultsChart;
import reportGeneration.interpreter.FinancialResults.Outcomes.NetSalesAnalyze;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.storage.SettingsStorage;

public class FinancialResultsReport implements TableName {

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
        Label tableName = tableName("Table 7. Financial Results Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency")
        );
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        FinancialResultTable financialResultTable = new FinancialResultTable();
        box.getChildren().addAll(
                tableName,
                financialResultTable.get(),
                new NetSalesAnalyze().get(),
                new CostOfGoods().get(),
                financialResultTable.analyseEbit(),
                new FinancialResultsChart().get()
        );
        return box;
    }


}
