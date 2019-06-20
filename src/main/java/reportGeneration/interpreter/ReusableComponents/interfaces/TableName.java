package reportGeneration.interpreter.ReusableComponents.interfaces;

import javafx.scene.control.Label;

public interface TableName {
    default Label tableName(String str) {
        Label tableName = new Label(str);
        tableName.getStyleClass().add("table-name");
        tableName.setWrapText(true);
        return tableName;
    }
}
