package finalon.reportGeneration.interpreter.ReusableComponents.interfaces;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import finalon.reportGeneration.storage.TwoDList;

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

        if (tableView != null) {
            ObservableList<TableColumn> columns = tableView.getColumns();

            //save headers
            ArrayList<String> headers = new ArrayList<>();
            for (TableColumn column : columns) {
                headers.add(column.getText());
            }
            values.addList(headers);

            if (tableView.getItems().size() > 0) {
                //save rows
                for (Object row : tableView.getItems()) {
                    ArrayList<String> rowVals = new ArrayList<>();
                    for (TableColumn column : columns) {
                        if (column.getCellObservableValue(row) != null) {
                            Object obj = column.getCellObservableValue(row).getValue();
                            if (obj != null) {
                                if (obj.getClass() == Double.class) {
                                    rowVals.add(Double.toString((Double) obj));
                                } else if (obj.getClass() == String.class) {
                                    rowVals.add((String) obj);
                                }
                            }
                        } else {
                            rowVals.add("");
                        }
                    }
                    values.addList(rowVals);
                }
            }
        }
        return values;
    }
}
