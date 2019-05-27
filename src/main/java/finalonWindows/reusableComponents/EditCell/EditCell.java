package finalonWindows.reusableComponents.EditCell;

import finalonWindows.reusableComponents.NumField;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class EditCell<S, T> extends TreeTableCell<S, T> {
    /**
     * Convenience converter that does nothing (converts Strings to themselves and vice-versa...).
     */
    public static final StringConverter<String> IDENTITY_CONVERTER = new StringConverter<String>() {

        @Override
        public String toString(String object) {
            return object;
        }

        @Override
        public String fromString(String string) {
            return string;
        }

    };
    private final TextField textField;
    private final StringConverter<T> converter;

    /**
     * Creates and initializes an edit cell object.
     *
     * @param converter the converter to convert from and to strings
     */
    public EditCell(StringConverter<T> converter, String type) {
        this.converter = converter;

        if (type.equals("integer")) {
            this.textField = new NumField();
        } else {
            this.textField = new TextField();
        }

        itemProperty().addListener((obx, oldItem, newItem) -> {
            setText(newItem != null ? this.converter.toString(newItem) : null);
        });

        setGraphic(this.textField);
        setContentDisplay(ContentDisplay.TEXT_ONLY);

        this.textField.setOnAction(evt -> {
            commitEdit(this.converter.fromString(this.textField.getText()));
        });
        this.textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                this.getTreeTableView().refresh();
                String val = this.textField.getText();
                if (val != null && val.length() > 0) {
                    commitEdit(this.converter.fromString(this.textField.getText()));
                } else {
                    commitEdit(this.converter.fromString(""));
                }
            }
        });
        this.textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                this.textField.setText(this.converter.toString(getItem()));
                cancelEdit();
                event.consume();
            } else if (event.getCode() == KeyCode.TAB) {
                commitEdit(this.converter.fromString(this.textField.getText()));
                TreeTableColumn<S, ?> nextColumn = getNextColumn(!event.isShiftDown());
                if (nextColumn != null) {
                    getTreeTableView().getSelectionModel().clearAndSelect(getTreeTableRow().getIndex(), nextColumn);
                    getTreeTableView().edit(getTreeTableRow().getIndex(), nextColumn);
                }
            }
        });
        this.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getClickCount() == 1 && event.getButton().equals(MouseButton.PRIMARY)) {
                getTreeTableView().edit(getTreeTableRow().getIndex(), getTableColumn());
            }
        });
    }

    /**
     * Convenience method for creating an EditCell for a String value.
     *
     * @return the edit cell
     */
    public static <S> EditCell<S, String> createStringEditCell(String type) {
        return new EditCell<S, String>(IDENTITY_CONVERTER, type);
    }

    @Override
    public void startEdit() {
        super.startEdit();
        this.textField.setText(this.converter.toString(getItem()));
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.textField.requestFocus();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void commitEdit(T item) {
        if (!isEditing() && !item.equals(getItem())) {
            TreeTableView<S> table = getTreeTableView();
            if (table != null) {
                TreeTableColumn<S, T> column = getTableColumn();
                TreeTableColumn.CellEditEvent<S, T> event = new TreeTableColumn.CellEditEvent<>(table,
                        new TreeTablePosition<S, T>(table, getIndex(), column),
                        TreeTableColumn.editCommitEvent(), item);
                Event.fireEvent(column, event);
            }
        }

        super.commitEdit(item);

        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    /**
     * Finds and returns the next editable column.
     *
     * @param forward indicates whether to search forward or backward from the current column
     * @return the next editable column or {@code null} if there is no next column available
     */
    private TreeTableColumn<S, ?> getNextColumn(boolean forward) {
        List<TreeTableColumn<S, ?>> columns = new ArrayList<>();
        for (TreeTableColumn<S, ?> column : getTreeTableView().getColumns()) {
            columns.addAll(getEditableColumns(column));
        }
        // There is no other column that supports editing.
        if (columns.size() < 2) {
            return null;
        }
        int currentIndex = columns.indexOf(getTableColumn());
        int nextIndex = currentIndex;
        if (forward) {
            nextIndex++;
            if (nextIndex > columns.size() - 1) {
                nextIndex = 0;
            }
        } else {
            nextIndex--;
            if (nextIndex < 0) {
                nextIndex = columns.size() - 1;
            }
        }
        return columns.get(nextIndex);
    }

    /**
     * Returns all editable columns of a table column (supports nested columns).
     *
     * @param root the table column to check for editable columns
     * @return a list of table columns which are editable
     */
    private List<TreeTableColumn<S, ?>> getEditableColumns(TreeTableColumn<S, ?> root) {
        List<TreeTableColumn<S, ?>> columns = new ArrayList<>();
        if (root.getColumns().isEmpty()) {
            // We only want the leaves that are editable.
            if (root.isEditable()) {
                columns.add(root);
            }
            return columns;
        } else {
            for (TreeTableColumn<S, ?> column : root.getColumns()) {
                columns.addAll(getEditableColumns(column));
            }
            return columns;
        }
    }
}