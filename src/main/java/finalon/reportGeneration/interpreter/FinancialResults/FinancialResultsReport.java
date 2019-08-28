package finalon.reportGeneration.interpreter.FinancialResults;

import finalon.reportGeneration.interpreter.FinancialResults.Outcomes.CostOfGoods;
import finalon.reportGeneration.interpreter.FinancialResults.Outcomes.FinancialResultTable;
import finalon.reportGeneration.interpreter.FinancialResults.Outcomes.FinancialResultsChart;
import finalon.reportGeneration.interpreter.FinancialResults.Outcomes.NetSalesAnalyze;
import finalon.reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.SettingsStorage;
import finalon.reportGeneration.storage.TwoDList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class FinancialResultsReport {
    private int weight;

    public FinancialResultsReport(int weight) {
        this.weight = weight;
    }

    public VBox getTrend() {
        String title = "Table 7. Financial Results Trend Analysis, in "
                + SettingsStorage.get("amount") + " " + SettingsStorage.get("defaultCurrency");
        Label tableName = TableName.name(title);
        weight++;
        VBox box = new VBox(8);
        box.setStyle("-fx-padding: 0 0 30px 0");
        FinancialResultTable financialResultTable = new FinancialResultTable();
        TableView tbl = financialResultTable.get();
        TwoDList items = TableName.getTableViewValues(tbl);
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
