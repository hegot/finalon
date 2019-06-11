package finalonWindows.templateScene.templates;

import entities.Item;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class EditCell extends TableCell<Item, String> {

    private TextField textField;

    public EditCell() {
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
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        if (textField != null) {
            commitEdit(textField.getText());
            setText(textField.getText());
            getTableView().getItems().get(getIndex()).setName(textField.getText());
        }
        setGraphic(null);
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

    private void createTextField() {
        textField = new TextField(getString());
        textField.getStyleClass().add("textField");
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnAction((e) -> commitEdit(textField.getText()));
        this.textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
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
