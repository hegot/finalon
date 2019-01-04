package finalonWindows.settingsScene.templates;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.UUID;

public class TemplateEditable {

    TableView<Item> table;

    public VBox getTable() {
        //Name column
        TableColumn<Item, String> nameColumn = new TableColumn<Item, String>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<Item, Integer> codeColumn = new TableColumn<Item, Integer>("Code");
        codeColumn.setMinWidth(100);
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));


        TableColumn<Item, String> shortNameColumn = new TableColumn<Item, String>("Short Name");
        shortNameColumn.setMinWidth(100);
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));


        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(deleteButton);

        table = new TableView<>();
        ObservableList products = getProduct();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, codeColumn, shortNameColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        return vBox;
    }


    //Get all of the products
    public ObservableList<Item> getProduct() {
        ObservableList<Item> products = FXCollections.observableArrayList();
        Item item = new Item("sdf", "Property, plant and equipment", 678, "PPE", true, "", 0.0);
        products.add(item);
        products.add(new Item(UUID.randomUUID().toString(), "Construction in progress", 456, "CIP", true, "", 0.0));
        products.add(new Item(UUID.randomUUID().toString(), "Investment property", 345, "IP", true, "", 0.0));
        products.add(new Item(UUID.randomUUID().toString(), "Intangible assets other than goodwill ", 567, "IAOTG", true, "", 0.0));
        products.add(new Item(UUID.randomUUID().toString(), "Non-current biological assets ", 123, "NCBA", true, "", 0.0));
        return products;
    }

    //Delete button clicked
    public void deleteButtonClicked() {
        ObservableList<Item> ItemsSelected, allItems;
        allItems = table.getItems();
        ItemsSelected = table.getSelectionModel().getSelectedItems();

        ItemsSelected.forEach(allItems::remove);
    }


}
