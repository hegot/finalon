package finalonWindows.templateScene.listing;

import database.template.DbItemHandler;
import entities.Item;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.templateScene.templates.EventHandlers.TemplateDeleteHandler;
import finalonWindows.templateScene.templates.TemplateEditPage;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class TemplateRow extends VBox {
    @FXML
    private Item item;

    public TemplateRow(Item item) {
        this.item = item;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listing/templateRow.fxml"));
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
    protected void editRowAction() {
        ObservableList<Item> items = DbItemHandler.getItems(item.getId());
        items.add(this.item);
        SceneSwitcher.getWindow().getChildren().setAll(TemplateEditPage.getScene(items));
    }

    @FXML
    protected void deleteRowAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Template");
        alert.setHeaderText("Are you Sure you want to delete template? This Action can not be undone.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            TemplateDeleteHandler.deleteItems(item.getId());
            SceneSwitcher.goTo(SceneName.TEMPLATESLIST);
        }
    }
}