package reportGeneration.interpreter.FinancialResults;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.FinancialResults.Outcomes.CostOfGoods;
import reportGeneration.interpreter.FinancialResults.Outcomes.FinancialResultTable;
import reportGeneration.interpreter.FinancialResults.Outcomes.FinancialResultsChart;
import reportGeneration.interpreter.FinancialResults.Outcomes.NetSalesAnalyze;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;
import reportGeneration.storage.TwoDList;

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
