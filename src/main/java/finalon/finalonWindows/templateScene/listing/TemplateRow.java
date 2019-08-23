package finalon.finalonWindows.templateScene.listing;

import finalon.database.template.DbItemHandler;
import finalon.database.template.TemplateEditor;
import finalon.entities.Item;
import finalon.finalonWindows.SceneName;
import finalon.finalonWindows.SceneSwitcher;
import finalon.finalonWindows.templateScene.EditTemplate;
import finalon.finalonWindows.templateScene.PreviewTemplate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.Optional;

public class TemplateRow extends VBox {
    @FXML
    private Item item;

    public TemplateRow(Item item) {
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

    private double height() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        return primaryScreenBounds.getHeight();
    }


    @FXML
    protected void viewRowAction() {
        ObservableList<Item> items = DbItemHandler.getItems(item.getId());
        items.add(this.item);
        PreviewTemplate previewTpl = new PreviewTemplate(items);
        SceneSwitcher.getWindow().getChildren().setAll(previewTpl.getScene());
    }

    @FXML
    protected void editRowAction() {
        ObservableList<Item> items = DbItemHandler.getItems(item.getId());
        items.add(this.item);
        EditTemplate editTpl = new EditTemplate(items);
        SceneSwitcher.getWindow().getChildren().setAll(editTpl.getScene());
    }

    @FXML
    protected void deleteRowAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Template");
        alert.setHeaderText("Are you Sure you want to delete template? This Action can not be undone.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int id = item.getId();
            String name = item.getName();
            ObservableList<Item> items = DbItemHandler.getItems(id);
            TemplateEditor templateEditor = new TemplateEditor(name, items);
            templateEditor.deleteItem(id);
            System.out.println("Template " + name + " deleted successfully");
            SceneSwitcher.goTo(SceneName.TEMPLATESLIST);
        }
    }

}