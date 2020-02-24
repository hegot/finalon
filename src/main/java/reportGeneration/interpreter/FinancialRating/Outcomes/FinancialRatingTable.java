package reportGeneration.interpreter.FinancialRating.Outcomes;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.Periods;
import services.Logger;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FinancialRatingTable {
    private ObservableList<Formula> formulas;
    private ObservableList<ScoreItem> scores;
    private Double totalScore;

    public FinancialRatingTable(ObservableList<Formula> formulas) {
        this.formulas = formulas;
        this.totalScore = 0.0;
        this.scores = getScores();
    }

    private ObservableList<ScoreItem> getScores() {
        ObservableList<ScoreItem> output = FXCollections.observableArrayList();
        ArrayList<Double> vals = new ArrayList<>();
        Double sum = 0.0;
        for (Formula formula : formulas) {
            if (formula != null) {
                try {
                    ScoreItem item = new ScoreItem(formula);
                    Double weightedScore = item.getWeightedScore();
                    if (weightedScore != null) {
                        sum += weightedScore;
                        vals.add(weightedScore);
                        output.add(item);
                    }
                } catch (Exception e) {
                    Logger.log("FIN RATING, Formula: " + formula.getShortName() + ", " + e.getMessage());
                }

            }
        }

        Formula endRow = new Formula(0, "Total Score", "", "", "", "", "", 0);
        ScoreItem item = new ScoreItem(endRow);
        item.setWeight(1.0);
        String formatted = Formatter.clean(new DecimalFormat("#.##").format(sum));
        sum = Double.valueOf(formatted);
        item.setWeightedScore(sum);
        output.add(item);
        this.totalScore = sum;
        return output;
    }

    public TableView<ScoreItem> get() {
        TableView table = new TableView<>();
        table.getStyleClass().add("report-table");
        table.setEditable(false);

        table.getColumns().addAll(
                getNameCol(),
                getCol("weight", "Weighting \n factor")
        );
        if (Periods.getPeriodArr().size() > 2) {
            table.getColumns().add(
                    getCol("score1", "Score \n (Pre-end Period)")
            );
        }
        table.getColumns().addAll(
                getCol("score2", "Score \n (End Period)"),
                getCol("averageScore", "Average \n score"),
                getCol("weightedScore", "Weighted average \n score")
        );
        table.setItems(scores);
        return table;
    }

    private TableColumn<String, ScoreItem> getNameCol() {
        TableColumn<String, ScoreItem> column = new TableColumn<>("Indicators");
        column.setId("name-column");
        column.setCellValueFactory(new PropertyValueFactory<>("name"));
        return column;
    }

    private TableColumn<Double, ScoreItem> getCol(String key, String title) {
        TableColumn<Double, ScoreItem> column = new TableColumn<>(title);
        column.getStyleClass().add("period-col");
        column.setCellValueFactory(new PropertyValueFactory<>(key));
        return column;
    }

    public Double getTotalScore() {
        return this.totalScore;
    }
}
