package reportGeneration.interpreter.FinancialRating.Outcomes;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.interpreter.ReusableComponents.interfaces.RatingWeight;
import reportGeneration.interpreter.ReusableComponents.interfaces.Round;

import java.util.ArrayList;

public class FinancialRatingTable implements JsCalcHelper, ParseDouble, Round, RatingWeight {
    private ObservableList<Formula> formulas;
    private ObservableList<ScoreItem> scores;

    public FinancialRatingTable(ObservableList<Formula> formulas) {
        this.formulas = formulas;
        this.scores = getScores();
    }

    private ObservableList<ScoreItem> getScores() {
        ObservableList<ScoreItem> output = FXCollections.observableArrayList();
        ArrayList<Double> vals = new ArrayList<>();
        for (Formula formula : formulas) {
            ScoreItem item = new ScoreItem(formula);
            vals.add(item.getWeightedScore());
            output.add(item);
        }
        Formula endRow = new Formula(0, "Total Score", "", "", "", "", "", 0);
        ScoreItem item = new ScoreItem(endRow);
        item.setWeight(1.0);
        item.setWeightedScore(2.0);
        output.add(item);
        return output;
    }

    public TableView<ScoreItem> get() {
        TableView table = new TableView<>();
        table.getStyleClass().add("report-table");
        table.setEditable(false);
        table.getColumns().addAll(getNameCol(), getCol("weight", "Weighting \n factor"));
        table.getColumns().add(getCol("score1", "Score \n (Pre-end Period)"));
        table.getColumns().add(getCol("score2", "Score \n (End Period)"));
        table.getColumns().add(getCol("averageScore", "Average \n score"));
        table.getColumns().add(getCol("weightedScore", "Weighted average \n score"));
        table.setItems(scores);
        return table;
    }

    private TableColumn<String, ScoreItem> getNameCol() {
        TableColumn<String, ScoreItem> column = new TableColumn<>("Indicators");
        column.setCellValueFactory(new PropertyValueFactory<>("name"));
        return column;
    }

    private TableColumn<Double, ScoreItem> getCol(String key, String title) {
        TableColumn<Double, ScoreItem> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(key));
        return column;
    }
}
