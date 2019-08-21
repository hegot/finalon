package finalon.reportGeneration.stepTwo;

import finalon.entities.Item;
import finalon.finalonWindows.reusableComponents.NumField;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class EditCell extends TableCell<Item, String> {

    private TextField textField;
    private TablePosition<Item, ?> tablePos = null;

    public EditCell() {
        this.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getClickCount() == 1 && event.getButton().equals(MouseButton.PRIMARY)) {
                getTableView().edit(getTableRow().getIndex(), getTableColumn());
            }
        });
    }

    @Override
    public void startEdit() {
        if (!isEditable() || !getTableView().isEditable() || !getTableColumn().isEditable()) {
            return;
        }
        super.startEdit();
        if (isEditing()) {
            if (textField == null) {
                textField = getTextField();
            }
            if (textField != null) {
                textField.setText(getItem());
                setText(null);
                setGraphic(textField);
                textField.selectAll();
                textField.requestFocus();
            }
        }
    }

    private TextField getTextField() {
        final TextField textField = new NumField(getItem());
        textField.setOnAction(event -> {
            this.commitEdit(textField.getText());
            event.consume();
        });

        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                this.getTableView().refresh();
                String val = this.textField.getText();
                if (val != null && val.length() > 0) {
                    commitEdit(textField.getText());
                } else {
                    commitEdit("");
                }
            }
        });
        return textField;

    }

    @Override
    public void cancelEdit() {

        if (textField != null) {
            commitEdit(textField.getText());
            setText(textField.getText());
        }
        super.cancelEdit();
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
        }
    }

    @Override
    public void commitEdit(String item) {
        if (!isEditing() && !item.equals(getItem())) {
            TableView<Item> table = getTableView();
            if (table != null) {
                TableColumn column = getTableColumn();
                TableColumn.CellEditEvent<Item, String> event = new TableColumn.CellEditEvent<Item, String>(
                        table,
                        new TablePosition(getTableView(), getIndex(), column),
                        TableColumn.editCommitEvent(), item
                );
                Event.fireEvent(column, event);
            }
            super.commitEdit(item);
        }
    }

    protected String getString() {
        return getItem() == null ? "" : getItem();
    }
}