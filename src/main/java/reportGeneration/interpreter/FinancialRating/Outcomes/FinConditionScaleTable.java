package reportGeneration.interpreter.FinancialRating.Outcomes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class FinConditionScaleTable {
    public ObservableList<ScaleItem> getItems() {
        ObservableList<ScaleItem> output = FXCollections.observableArrayList();
        output.add(new ScaleItem(1.0, 0.8, "AAA", "Excellent", "#0C9500"));
        output.add(new ScaleItem(0.8, 0.6, "AA", "Very good", "#72FD37"));
        output.add(new ScaleItem(0.6, 0.4, "A", "Good", "#8FFC60"));
        output.add(new ScaleItem(0.4, 0.2, "BBB", "Positive", "#9DFE72"));
        output.add(new ScaleItem(0.2, 0.0, "BB", "Normal", "#BEFDA3"));
        output.add(new ScaleItem(0.0, -0.2, "B", "Satisfactory", "#EDFCE7"));
        output.add(new ScaleItem(-0.2, -0.4, "CCC", "Unsatisfactory", "#FCE8E7"));
        output.add(new ScaleItem(-0.4, -0.6, "CC", "Adverse", "#FC6666"));
        output.add(new ScaleItem(-0.6, -0.8, "C", "Bad", "#FE1F1F"));
        output.add(new ScaleItem(-0.8, -1.0, "D", "Critical", "#FE0000"));
        return output;
    }

    public TableView<ScoreItem> get() {
        TableView table = new TableView<ScoreItem>();
        table.getStyleClass().addAll("report-table", "scale-table");
        table.setEditable(false);
        table.getColumns().addAll(
                getCol("col1", "Score from (inclusive)"),
                getCol("col2", "to"),
                getSignCol("col3", "Sign"),
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

    private TableColumn<ScaleItem, String> getSignCol(String key, String title) {
        TableColumn<ScaleItem, String> column = new TableColumn<>(title);
        column.setMinWidth(200);
        column.setCellValueFactory(new PropertyValueFactory<>(key));
        column.setCellFactory(col -> {
            return new TableCell<ScaleItem, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        ScaleItem scaleItem = (ScaleItem) getTableRow().getItem();
                        setText(scaleItem.getCol3());
                        setTextFill(Color.BLACK);
                        setStyle("-fx-background-color:" + scaleItem.getColor());
                    }
                }
            };
        });
        return column;
    }


    private TableColumn<ScaleItem, Double> getCol(String key, String title) {
        TableColumn<ScaleItem, Double> column = new TableColumn<>(title);
        column.setMinWidth(200);
        column.setCellValueFactory(new PropertyValueFactory<>(key));
        return column;
    }
}

