package finalonWindows.TemplateScene.templates;

import entities.Item;
import entities.Sheet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class TemplateEditable {


    private ObservableList<Item> items;
    private ArrayList<Sheet> sheets;

    public TemplateEditable(ArrayList<Sheet> sheets) {
        this.sheets = sheets;
        this.items = getAllItems();
    }

    private ObservableList<Item> getAllItems() {
        ObservableList<Item> combined = FXCollections.observableArrayList();
        for (int i = 0; i < this.sheets.size(); i++) {
            combined.addAll(this.sheets.get(i).items);
        }
        return combined;
    }

    public TabPane getTemplateEditable() {
        TabPane tabs = new TabPane();
        for (int i = 0; i < this.sheets.size(); i++) {
            Tab tab = getSheetRendered(this.sheets.get(i));
            tabs.getTabs().add(tab);
        }
        return tabs;
    }

    private Tab getSheetRendered(Sheet sheet) {
        Tab tab = new Tab();
        tab.setText(sheet.name);

        ArrayList<String> mainCategories = getMainCategories(sheet.id);

        if (mainCategories.isEmpty()) {
            TableView table = getSingleTable(sheet, "", "");
            tab.setContent(table);
        } else {
            TabPane tabs = new TabPane();
            for (int i = 0; i < mainCategories.size(); i++) {
                String mainCategory = mainCategories.get(i);
                Tab categoryTab = getMCatRendered(mainCategory, sheet);
                tabs.getTabs().add(categoryTab);
            }
            tab.setContent(tabs);
        }

        return tab;
    }

    private Tab getMCatRendered(String mainCategory, Sheet sheet) {
        VBox vBox = new VBox();
        ArrayList<String> subCategories = getSubCategories(sheet, mainCategory);
        for (int j = 0; j < subCategories.size(); j++) {
            String subCategory = subCategories.get(j);
            TableView table = getSingleTable(sheet, mainCategory, subCategory);
            table.setFixedCellSize(25);
            vBox.getChildren().addAll(greyLabel(subCategory), table);
        }
        Tab tab = new Tab();
        tab.setText(mainCategory);
        tab.setContent(vBox);
        return tab;
    }

    private Label greyLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-weight: bold");
        label.setPadding(new Insets(10, 5, 10, 5));
        label.setFont(Font.font("Arial", 15));
        label.setTextFill(Color.web("#6a6c6f"));
        return label;
    }


    private void updateSheets(int id, String val, String param) {

    }


    private void updateCell(TableColumn.CellEditEvent<Item, String> t, String param) {
        String value = t.getNewValue();
        Item item = t.getTableView().getItems().get(
                t.getTablePosition().getRow());
        if (param.equals("name")) {
            item.setName(value);
        }
        if (param.equals("shortName")) {
            item.setShortName(value);
        }
        updateSheets(item.id, value, param);
    }


    private void setColumnEventHandlers(TableColumn column, String param) {
        column.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, String> t) {
                        updateCell(t, param);
                    }
                }
        );
        column.setOnEditCancel(
                new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, String> t) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("To save changes press 'Enter'");
                        alert.show();
                    }
                }
        );

    }


    private TableView getSingleTable(Sheet sheet, String mainCategory, String subCategory) {
        TableView<Item> table = new TableView<Item>();
        table.setEditable(true);


        TableColumn<Item, String> nameColumn = new TableColumn<Item, String>("Indicator");
        nameColumn.setMinWidth(400);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        setColumnEventHandlers(nameColumn, "name");


        TableColumn<Item, String> shortNameColumn = new TableColumn<Item, String>("Indicator Code");
        shortNameColumn.setMinWidth(300);
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("shortName"));
        shortNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        setColumnEventHandlers(shortNameColumn, "shortName");


        table.getColumns().addAll(nameColumn, shortNameColumn);
        ObservableList<Item> items = getItems(sheet.items, mainCategory, subCategory);
        table.setItems(items);
        return table;
    }


    public ObservableList<Item> getItems(ObservableList<Item> items, String mainCategory, String subCategory) {
        ObservableList<Item> returnItems = FXCollections.observableArrayList();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String itemMainCategory = item.getMainCategory();
            String itemSubCategory = item.getSubCategory();
            if (itemMainCategory.equals(mainCategory) && itemSubCategory.equals(subCategory)) {
                returnItems.add(item);
            }
        }
        return returnItems;
    }

    private ArrayList<String> getMainCategories(int sheetId) {
        ArrayList<String> mainCategories = new ArrayList<String>();
        for (int i = 0; i < this.items.size(); i++) {
            Item item = this.items.get(i);
            String mainCat = item.getMainCategory();
            int itemSheetId = item.getParentSheet();
            if (!mainCategories.contains(mainCat) && itemSheetId == sheetId) {
                if (mainCat != null && !mainCat.isEmpty()) {
                    mainCategories.add(mainCat);
                }
            }
        }
        return mainCategories;
    }

    private ArrayList<String> getSubCategories(Sheet sheet, String mainCategory) {
        ArrayList<String> subCategories = new ArrayList<String>();
        for (int i = 0; i < sheet.items.size(); i++) {
            Item item = sheet.items.get(i);
            String subCat = item.getSubCategory();
            String mainCat = item.getMainCategory();
            if (!subCategories.contains(subCat) && mainCategory.equals(mainCat)) {
                if (subCat != null && !subCat.isEmpty()) {
                    subCategories.add(subCat);
                }
            }
        }
        return subCategories;
    }


    public ArrayList<Sheet> getSheets() {
        return this.sheets;
    }


    //Delete button clicked

}
