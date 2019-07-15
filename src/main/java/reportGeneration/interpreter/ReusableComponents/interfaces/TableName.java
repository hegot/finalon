package reportGeneration.interpreter.ReusableComponents.interfaces;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import reportGeneration.storage.TwoDList;

import java.util.ArrayList;

public interface TableName {
    default Label tableName(String str) {
        Label tableName = new Label(str);
        tableName.getStyleClass().add("table-name");
        tableName.setWrapText(true);
        return tableName;
    }

    default TwoDList getTableViewValues(TableView tableView) {
        TwoDList values = new TwoDList();

        ObservableList<TableColumn> columns = tableView.getColumns();
        for (Object row : tableView.getItems()) {
            ArrayList<String> rowVals = new ArrayList<>();
            for (TableColumn column : columns) {
                if (column.getCellObservableValue(row) != null) {
                    rowVals.add((String) column.getCellObservableValue(row).getValue());
                } else {
                    rowVals.add("");
                }
            }
            values.addList(rowVals);
        }
        return values;
    }
}
