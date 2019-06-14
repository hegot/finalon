package finalonWindows.templateScene.templates;

import entities.Item;
import finalonWindows.reusableComponents.NumField;
import globalReusables.EditCellBase;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class EditCell extends EditCellBase {

    private TextField textField;

    public EditCell(String type) {
        super(type);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(item);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                    setGraphic(null);
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }

            Item index = getTableView().getItems().get(getTableRow().getIndex());
            Integer level = index.getLevel();
            if (level.equals(1) || level.equals(2) || level.equals(3)) {
                setStyle("-fx-padding: 5 0 5 50; -fx-font-weight: bold;");
            }
        }
    }

}
