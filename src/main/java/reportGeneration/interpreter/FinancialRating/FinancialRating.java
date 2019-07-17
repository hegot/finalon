package reportGeneration.interpreter.FinancialRating;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.FinancialRating.Outcomes.FinConditionScaleTable;
import reportGeneration.interpreter.FinancialRating.Outcomes.FinancialRatingTable;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.TwoDList;

public class FinancialRating implements TableName {

    private ObservableList<Formula> formulas;

    public FinancialRating() {
        this.formulas = FXCollections.observableArrayList();
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
        ResultsStorage.addStr(121, "tableName", title);
        TableView tbl = new FinancialRatingTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(122, items);

        String title2 = "Table 14. Financial condition scale";
        ResultsStorage.addStr(123, "tableName", title2);
        TableView tbl2 = new FinConditionScaleTable().get();
        TwoDList items2 = getTableViewValues(tbl2);
        ResultsStorage.addTable(124, items2);

        box.getChildren().addAll(
                tableName(title),
                tbl,
                tableName(title2),
                tbl2
        );
        return box;
    }
}
