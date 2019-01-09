package finalonWindows.settingsScene.templates;

import entities.Item;
import entities.Sheet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;


import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TemplateEditable {


    private TableView<Item> table;
    private ObservableList<Item> items;
    private ArrayList<Sheet> sheets;
    private ArrayList<String> mainCategories;
    private ArrayList<String> subCategories;

    public TemplateEditable(ObservableList<Item> items, ArrayList<Sheet> sheets) {
        this.items = items;
        this.sheets = sheets;
        populateCategories();
    }

    public VBox getTemplateEditable() {
        // Button deleteButton = new Button("Delete");
        //  deleteButton.setOnAction(e -> deleteButtonClicked());

        // HBox hBox = new HBox();
        //   hBox.setPadding(new Insets(10, 10, 10, 10));
        //  hBox.setSpacing(10);
        //hBox.getChildren().addAll(deleteButton);

        VBox vBox = new VBox();
        vBox.setMinHeight(300);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        for (int i = 0; i < this.sheets.size(); i++) {
            VBox vBox2 = getSheetRendered(this.sheets.get(i));
            vBox.getChildren().add(vBox2);
        }
        return vBox;
    }

    private VBox getSheetRendered(Sheet sheet) {
        VBox sheetBox = new VBox();
        sheetBox.setPadding(new Insets(20, 0, 10, 0));
        sheetBox.setMinHeight(300);
        Text sheetHeader = new Text(sheet.name);
        sheetBox.getChildren().add(sheetHeader);
        for (int i = 0; i < this.mainCategories.size(); i++) {
            String mainCategory = this.mainCategories.get(i);
            Text mainCatHeader = new Text(mainCategory);
            mainCatHeader.setFont(Font.font("Verdana", 15));
            mainCatHeader.setFill(Color.OLIVEDRAB);
            sheetBox.getChildren().add(mainCatHeader);
            for (int j = 0; j < this.subCategories.size(); j++) {
                String subCategory = this.subCategories.get(j);
                Text subCatHeader = new Text(subCategory);
                subCatHeader.setFont(Font.font("Verdana", 13));
                subCatHeader.setFill(Color.BROWN);
                TableView table = getSingleTable(sheet.id, mainCategory, subCategory);
                table.setFixedCellSize(25);
                sheetBox.getChildren().addAll(subCatHeader, table);
            }
        }

        return sheetBox;
    }


    private TableView getSingleTable(int sheetId, String mainCategory, String subCategory) {
        TableColumn<Item, String> nameColumn = new TableColumn<Item, String>("Indicator");
        nameColumn.setMinWidth(400);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Item, String> shortNameColumn = new TableColumn<Item, String>("Indicator Code");
        shortNameColumn.setMinWidth(300);
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(nameColumn, shortNameColumn);
        table.setItems(getItems(sheetId, mainCategory, subCategory));
        return table;
    }

    public ObservableList<Item> getItems(int SheetId, String mainCategory, String subCategory) {
        ObservableList<Item> returnItems = FXCollections.observableArrayList();
        for (int i = 0; i < this.items.size(); i++) {
            Item item = this.items.get(i);
            int itemSheetId = item.getParentSheet();
            String itemMainCategory = item.getMainCategory();
            String itemSubCategory = item.getSubCategory();

            if (itemSheetId == SheetId
                    && itemMainCategory == mainCategory
                    && itemSubCategory == subCategory) {
                returnItems.add(item);
                //this.items.remove(item);
            }
        }
        return returnItems;
    }

    private void populateCategories() {
        ArrayList<String> mainCategories = new ArrayList<String>();
        ArrayList<String> subCategories = new ArrayList<String>();
        for (int i = 0; i < this.items.size(); i++) {
            Item item = this.items.get(i);
            String mainCat = item.getMainCategory();
            String subCat = item.getSubCategory();
            if (!mainCategories.contains(mainCat)) {
                if (mainCat != null && !mainCat.isEmpty()) {
                    mainCategories.add(mainCat);
                }
            }
            if (!subCategories.contains(subCat)) {
                if (subCat != null && !subCat.isEmpty()) {
                    subCategories.add(subCat);
                }
            }
        }
        this.mainCategories = mainCategories;
        this.subCategories = subCategories;
    }


    //Delete button clicked
    public void deleteButtonClicked() {
        ObservableList<Item> ItemsSelected, allItems;
        allItems = table.getItems();
        ItemsSelected = table.getSelectionModel().getSelectedItems();

        ItemsSelected.forEach(allItems::remove);
    }


}
