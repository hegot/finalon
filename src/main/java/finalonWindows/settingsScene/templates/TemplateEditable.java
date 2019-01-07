package finalonWindows.settingsScene.templates;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import database.DbItemHandler;
import java.util.UUID;

public class TemplateEditable {

    TableView<Item> table;

    public VBox getTable() {
        //Name column
        TableColumn<Item, String> nameColumn = new TableColumn<Item, String>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));




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
        ObservableList items = getItems();
        table.setItems(getItems());
        table.getColumns().addAll(nameColumn, shortNameColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        return vBox;
    }


    //Get all of the products
    public ObservableList<Item> getItems() {
        DbItemHandler dbItemHandler = new DbItemHandler();
        ObservableList Items = dbItemHandler.getAllItems();
        return Items;
    }

    //Delete button clicked
    public void deleteButtonClicked() {
        ObservableList<Item> ItemsSelected, allItems;
        allItems = table.getItems();
        ItemsSelected = table.getSelectionModel().getSelectedItems();

        ItemsSelected.forEach(allItems::remove);
    }


}
