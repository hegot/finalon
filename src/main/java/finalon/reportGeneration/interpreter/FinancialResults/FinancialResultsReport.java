package finalon.reportGeneration.interpreter.FinancialResults;

import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import finalon.reportGeneration.interpreter.FinancialResults.Outcomes.CostOfGoods;
import finalon.reportGeneration.interpreter.FinancialResults.Outcomes.FinancialResultTable;
import finalon.reportGeneration.interpreter.FinancialResults.Outcomes.FinancialResultsChart;
import finalon.reportGeneration.interpreter.FinancialResults.Outcomes.NetSalesAnalyze;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.SettingsStorage;
import finalon.reportGeneration.storage.TwoDList;

public class FinancialResultsReport implements TableName {
    private int weight;

    public FinancialResultsReport(int weight) {
        this.weight = weight;
    }

    public VBox getTrend() {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        String title = "Table 7. Financial Results Trend Analysis, in "
                + settings.get("amount") + " " + settings.get("defaultCurrency");
        Label tableName = tableName(title);
        weight++;
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        FinancialResultTable financialResultTable = new FinancialResultTable();
        TableView tbl = financialResultTable.get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items, title);
        VBox NetSalesAnalyze = new NetSalesAnalyze().get(weight);
        weight++;
        VBox CostOfGoods = new CostOfGoods().get(weight);
        weight++;
        VBox FinancialResultsChart = new FinancialResultsChart().get(weight);
        weight++;

        box.getChildren().addAll(
                tableName,
                tbl,
                NetSalesAnalyze,
                CostOfGoods,
                financialResultTable.analyseEbit(),
                FinancialResultsChart
        );
        return box;
    }


}
