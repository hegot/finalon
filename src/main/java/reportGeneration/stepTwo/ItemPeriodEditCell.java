package reportGeneration.stepTwo;

import entities.Item;
import finalonWindows.reusableComponents.NumField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ItemPeriodEditCell extends TableCell<Item, String> {

    private TextField textField;
    private Item item;
    private String colName;

    public ItemPeriodEditCell(String colName) {
        this.colName = colName;
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
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (item != null) {
                item.updateItem(newValue, colName);
            }
        });
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                this.getTableView().refresh();
                UpdateParentCell.run(item, colName);
            }
        });
        return textField;
    }


    @Override
    public void updateItem(String val, boolean empty) {
        super.updateItem(val, empty);
        if (empty) {
            setText(val);
            setGraphic(null);
        } else {
            item = (Item) getTableRow().getItem();
            if (item != null) {
                String itemVal = item.getStrVal(colName);
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(itemVal);
                        setGraphic(null);
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(itemVal);
                    setGraphic(null);
                }
            }
        }
    }

}