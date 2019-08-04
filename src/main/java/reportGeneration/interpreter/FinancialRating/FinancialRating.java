package reportGeneration.interpreter.FinancialRating;

import entities.Formula;
import globalReusables.LabelWrap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.FinancialRating.Outcomes.FinConditionScaleTable;
import reportGeneration.interpreter.FinancialRating.Outcomes.FinancialRatingTable;
import reportGeneration.interpreter.FinancialRating.Outcomes.ScaleItem;
import reportGeneration.interpreter.FinancialRating.Outcomes.ScoreItem;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class FinancialRating implements TableName, LabelWrap {

    private ObservableList<Formula> formulas;
    private int weight;

    public FinancialRating(int weight) {
        this.formulas = FXCollections.observableArrayList();
        this.weight = weight;
        FormulaStorage storage = FormulaStorage.getInstance();
        formulas.add(storage.get("NetProfitMargin"));
        formulas.add(storage.get("ReturnOnAssets"));
        formulas.add(storage.get("DebtEquityRatio"));
        formulas.add(storage.get("CurrentRatio"));
        formulas.add(storage.get("NetSalesChange"));
        formulas.add(storage.get("OperatingIncomeMargin"));
        formulas.add(storage.get("EquityChange"));
        formulas.add(storage.get("QuickRatio"));
        formulas.add(storage.get("DebtRatio"));
        formulas.add(storage.get("TimesInterestEarned"));
    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 13. Financial Rating";
        ResultsStorage.addStr(weight, "tableName", title);
        weight++;
        FinancialRatingTable financialRatingTable = new FinancialRatingTable(formulas);
        TableView tbl = financialRatingTable.get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items);
        weight++;
        String title2 = "Table 14. Financial condition scale";
        ResultsStorage.addStr(weight, "tableName", title2);
        weight++;
        FinConditionScaleTable finConditionScaleTable = new FinConditionScaleTable();
        TableView tbl2 = finConditionScaleTable.get();
        TwoDList items2 = getTableViewValues(tbl2);
        ResultsStorage.addScaleTable(weight, items2);
        weight++;
        String outcome = getOutcome(
                finConditionScaleTable.getItems(),
                financialRatingTable.getTotalScore());
        ResultsStorage.addStr(weight, "text", outcome);
        weight++;
        box.getChildren().addAll(
                tableName(title),
                tbl,
                tableName(title2),
                tbl2,
                labelWrap(outcome)
        );
        return box;
    }

    private String getOutcome(ObservableList<ScaleItem> scales, Double score){
        String out = "";
        for(ScaleItem item : scales){
            Double start = item.getCol1();
            Double end = item.getCol2();
            if(score > end && score <= start){
                out = "As a result we can confirm a " + item.getCol4() + " (" + item.getCol3() + ") financial situation.";
            }
        }
        return out;
    }
}
