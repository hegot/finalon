package finalonWindows.settingsScene;

import database.DbItemHandler;
import entities.Item;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.TemplateScene.EditTemplate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TemplateRow extends VBox {
    @FXML
    private Item item;
    private Stage window;

    public TemplateRow(Stage window, Item item) {
        this.window = window;
        this.item = item;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings/templateRow.fxml"));
        fxmlLoader.getNamespace().put("labelText", item.getName());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @FXML
    protected void viewRowAction() {
        window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.ADDTEMPLATE));
        System.out.println("The button was clicked!sss");
    }

    @FXML
    protected void editRowAction() {
        DbItemHandler itemsHandler = new DbItemHandler();
        ObservableList<Item> items = itemsHandler.getItems(item.getId());
        items.add(this.item);
        EditTemplate editTpl = new EditTemplate(window, items);
        window.setScene(editTpl.getScene());
    }

    @FXML
    protected void deleteRowAction() {

        System.out.println("Delete row");
    }

}