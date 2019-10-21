package reportGeneration.stepTwo;

import entities.Item;
import finalonWindows.reusableComponents.NumField;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import reportGeneration.storage.Periods;
import reportGeneration.storage.TextFieldStorage;

public class ItemPeriodEditCell extends TableCell<Item, String> {

    private Item item;
    private String colName;
    private NumField textField;

    public ItemPeriodEditCell(String colName) {
        this.colName = colName;
        getStyleClass().add("report-number-cell");
        this.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getClickCount() == 1 && event.getButton().equals(MouseButton.PRIMARY)) {
                getTableView().edit(getTableRow().getIndex(), getTableColumn());
                if (textField != null) {
                    textField.requestFocus();
                }
            }
        });
    }


    private void createNumField() {
        textField = new NumField(item.getStrVal(colName));
        TextFieldStorage.add(item.getShortName() + colName, textField);
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused && !isNowFocused) {
                String text = textField.getText();
                if (text != null && text.length() > 0) {
                    item.updateItem(text, colName);
                    UpdateParentCell.run(item, colName);
                }
            }
        });
        textField.setOnKeyPressed(t -> {
            if (t.getCode() == KeyCode.ENTER || t.getCode() == KeyCode.DOWN) {
                int index = getIndex() + 1;
                selectCell(index, colName);
            } else if (t.getCode() == KeyCode.TAB || t.getCode() == KeyCode.RIGHT) {
                String nextColName = Periods.next(colName);
                if (nextColName.length() > 0) {
                    selectCell(getIndex(), nextColName);
                }
            } else if (t.getCode() == KeyCode.LEFT) {
                String prevColName = Periods.prev(colName);
                if (prevColName.length() > 0) {
                    selectCell(getIndex(), prevColName);
                }
            } else if (t.getCode() == KeyCode.UP) {
                int index = getIndex() - 1;
                selectCell(index, colName);
            }
        });
    }

    private void selectCell(Integer index, String columnName) {
        try {
            Platform.runLater(() -> {
                if (index != null && index > -1 && columnName != null) {
                    ObservableList<Item> items = getTableView().getItems();
                    Item item = items.get(index);
                    if (item != null) {
                        NumField field = TextFieldStorage.get(item.getShortName() + columnName);
                        if (field != null) {
                            field.requestFocus();
                        }
                    }
                }
            });
        } catch (Exception e) {
        }
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
                createNumField();
                setGraphic(textField);
            }
        }
    }

}