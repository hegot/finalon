package finalon.reportGeneration.stepTwo;

import finalon.database.setting.DbSettingHandler;
import finalon.entities.Item;
import finalon.globalReusables.Setting;
import finalon.reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import finalon.reportGeneration.stepTwo.hooks.UpdateParentHook;
import finalon.reportGeneration.storage.ItemsStorage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


class Columns {

    TableColumn getNameCol() {
        TableColumn<Item, String> col = new TableColumn<Item, String>("Indicator");
        col.setMinWidth(350);
        col.setEditable(false);
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        col.setCellFactory(column -> {
            return new TableCell<Item, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);
                        Item index = getTableView().getItems().get(getTableRow().getIndex());
                        Integer level = index.getLevel();
                        if (level.equals(1) || level.equals(2) || level.equals(3)) {
                            setStyle("-fx-padding: 5 0 5 50; -fx-font-weight: bold;");
                        }
                    }
                }
            };
        });
        return col;
    }


    TableColumn getCodeCol() {
        TableColumn<Item, String> col = new TableColumn<Item, String>("Indicator Code");
        col.setMinWidth(100);
        col.setEditable(false);
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("shortName"));
        col.setCellFactory(TextFieldTableCell.<Item>forTableColumn());
        return col;
    }


    TableColumn getPeriodCol(String colname) {
        String numberFormat = DbSettingHandler.getSetting(Setting.numberFormat);
        ObservableList<Item> items = ItemsStorage.getItems();
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname.replace("-", "-\n"));
        col.setMinWidth(100);
        col.setCellFactory(column -> new EditCell());
        col.setOnEditCommit(
                (TableColumn.CellEditEvent<Item, String> t) -> {
                    if (t != null && t.getTableView() != null) {
                        String value = t.getNewValue().replace(',', '.');
                        if (value != null) {
                            Item item = ((Item) t.getTableView().getItems()
                                    .get(t.getTablePosition().getRow()));
                            if (item != null) {
                                ObservableMap<String, Double> values = item.getValues();
                                updateItem(item, values, value, colname);
                                UpdateParentHook parentHook = new UpdateParentHook(items);
                                parentHook.run(item, colname);
                                t.getTableView().refresh();
                            }
                        }
                    }
                });


        col.setCellValueFactory(cellData -> {
            Item item = (Item) cellData.getValue();
            if (item != null) {
                if (item.getValues().size() > 0) {
                    Double dob = item.getValues().get(colname);
                    if (dob != null) {
                        String val = Double.toString(dob);

                        if (numberFormat.equals("comma")) {
                            val = val.replace('.', ',');
                        }
                        return new SimpleStringProperty(val);
                    }
                }
            }
            return null;
        });
        return col;
    }


    private void updateItem(Item item, ObservableMap<String, Double> values, String value, String param) {
        if (value.length() > 0) {
            values.put(param, Formatter.parseDouble(value));
        } else {
            values.remove(param);
        }
        item.setValues(values);
    }

}
