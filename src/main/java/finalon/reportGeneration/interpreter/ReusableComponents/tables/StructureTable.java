package finalon.reportGeneration.interpreter.ReusableComponents.tables;

import finalon.entities.Item;
import finalon.globalReusables.ItemsGetter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.Diff;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StructureTable implements JsCalcHelper, Diff {
    private Map<String, Double> totalVals;
    private ObservableList<Item> items;
    private ArrayList<String> periodsArr;

    public StructureTable(
            Item root
    ) {
        ItemsGetter itemsGetter = new ItemsGetter(root, ItemsStorage.getItems(), true);
        this.items = itemsGetter.getItems();
        this.periodsArr = Periods.getPeriodArr();
        this.totalVals = getTotals(root);
    }


    private ObservableList<StructureItem> getStructureItems() {
        ObservableList<StructureItem> StructureItems = FXCollections.observableArrayList();
        for (Item item : items) {
            StructureItem structureItem = new StructureItem(item, totalVals);
            StructureItems.add(structureItem);
        }
        return StructureItems;
    }

    private Map<String, Double> getTotals(Item root) {
        Map<String, Double> totalVals = new HashMap<>();
        for (String col : periodsArr) {
            totalVals.put(col, root.getVal(col));
        }
        return totalVals;
    }


    public TableView<StructureItem> get() {
        TableView<StructureItem> table = new TableView<>();
        table.getItems().addAll(getStructureItems());
        table.getStyleClass().add("report-table");
        table.getColumns().addAll(getNameCol());

        for (String col : periodsArr) {
            table.getColumns().add(structureCol(col));
        }
        int count = periodsArr.size() - 1;
        if (count > 0) {
            for (int j = 0; j < count; j++) {
                String colStart = periodsArr.get(j);
                String colEnd = periodsArr.get(j + 1);
                table.getColumns().add(absoluteChangeCol(colStart, colEnd));
            }
        }
        return table;
    }

    private TableColumn absoluteChangeCol(String colStart, String colEnd) {
        String colname = "Absolute Change\n" + formatDate(colEnd) + " to \n" + formatDate(colStart);
        TableColumn<StructureItem, String> column = new TableColumn<StructureItem, String>(colname);
        column.setMinWidth(150);
        column.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                return diff(
                        values.get(colStart),
                        values.get(colEnd)
                );
            }
            return null;
        });
        return column;
    }

    protected TableColumn getNameCol() {
        TableColumn<StructureItem, String> col = new TableColumn<>("Indicator");
        col.setMinWidth(350);
        col.setCellValueFactory(new PropertyValueFactory<>("name"));
        col.setCellFactory(TextFieldTableCell.<StructureItem>forTableColumn());
        return col;
    }

    private TableColumn structureCol(String col) {
        TableColumn<StructureItem, String> column = new TableColumn<StructureItem, String>(formatDate(col));
        column.setMinWidth(150);
        column.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double itemVAl = values.get(col);
                if (itemVAl != null) {
                    return new SimpleStringProperty(commaFormat(itemVAl) + "%");
                }
            }
            return null;
        });
        return column;
    }


    protected ObservableMap<String, Double> getValues(TableColumn.CellDataFeatures<StructureItem, String> cellData) {
        StructureItem item = (StructureItem) cellData.getValue();
        if (item != null) {
            if (item.getStructureValues().size() > 0) {
                return item.getStructureValues();
            }
        }
        return null;
    }
}
