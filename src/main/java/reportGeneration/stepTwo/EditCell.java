package reportGeneration.stepTwo;

import entities.Item;
import finalonWindows.reusableComponents.NumField;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class EditCell extends TableCell<Item, String> {

    private TextField textField;
    private String type;

    public EditCell(String type) {
        this.type = type;
        this.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getClickCount() == 1 && event.getButton().equals(MouseButton.PRIMARY)) {
                getTableView().edit(getTableRow().getIndex(), getTableColumn());

            }
        });
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
            textField.requestFocus();
        }
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

    private void createTextField() {
        if (type.equals("string")) {
            textField = new TextField();
        } else {
            textField = new NumField();
        }
        textField.setText(getString());
        textField.getStyleClass().add("textField");
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnAction((e) -> commitEdit(textField.getText()));
        this.textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
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
    }

    private String getString() {
        return getItem() == null ? "" : getItem();
    }
}