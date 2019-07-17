package reportGeneration.interpreter.FinancialRating.Outcomes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FinConditionScaleTable {
    private ObservableList<ScaleItem> getItems() {
        ObservableList<ScaleItem> output = FXCollections.observableArrayList();
        output.add(new ScaleItem(1.0, 0.8, "AAA", "Excellent", "#0C9500"));
        output.add(new ScaleItem(0.8, 0.6, "AA", "Very good", ""));
        output.add(new ScaleItem(0.6, 0.4, "A", "Good", ""));
        output.add(new ScaleItem(0.4, 0.2, "BBB", "Positive", ""));
        output.add(new ScaleItem(0.2, 0.0, "BB", "Normal", ""));
        output.add(new ScaleItem(0.0, -0.2, "B", "Satisfactory", ""));
        output.add(new ScaleItem(-0.2, -0.4, "CCC", "Unsatisfactory", ""));
        output.add(new ScaleItem(-0.4, -0.6, "CC", "Adverse", ""));
        output.add(new ScaleItem(-0.6, -0.8, "C", "Bad", ""));
        output.add(new ScaleItem(-0.8, -1.0, "D", "Critical", ""));
        return output;
    }

    public TableView<ScoreItem> get() {
        TableView table = new TableView<ScoreItem>();
        table.getStyleClass().add("report-table");
        table.setEditable(false);
        table.getColumns().addAll(
                getCol("col1", "Score from (inclusive)"),
                getCol("col2", "to"),
                getStrCol("col3", "Sign"),
                getStrCol("col4", "Current financial \n condition")
        );
        table.setItems(getItems());
        return table;
    }

    private TableColumn<String, ScaleItem> getStrCol(String key, String title) {
        TableColumn<String, ScaleItem> column = new TableColumn<>(title);
        column.setMinWidth(200);
        column.setCellValueFactory(new PropertyValueFactory<>(key));
        return column;
    }

    private TableColumn<Double, ScaleItem> getCol(String key, String title) {
        TableColumn<Double, ScaleItem> column = new TableColumn<>(title);
        column.setMinWidth(200);
        column.setCellValueFactory(new PropertyValueFactory<>(key));
        return column;
    }
}

