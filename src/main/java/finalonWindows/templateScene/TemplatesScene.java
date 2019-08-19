package finalonWindows.templateScene;

import database.template.DbItemHandler;
import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.SettingsMenu;
import finalonWindows.templateScene.listing.AddTemplateBtn;
import finalonWindows.templateScene.listing.SettingsMessage;
import finalonWindows.templateScene.listing.TemplateRow;
import javafx.collections.ObservableList;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class TemplatesScene extends SceneBase {

    public VBox getScene() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("templates-listing");
        vbox.setPrefSize(900, 600);
        vbox.setPrefHeight(600);
        SettingsMenu settingsMenu = new SettingsMenu();
        vbox.getChildren().addAll(settingsMenu.getMenu(), getTemplates());
        return vbox;
    }

    private VBox getTemplates() {
        ObservableList<Item> items = DbItemHandler.getTemplates();
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 20px 30px;");
        TilePane tilePane = new TilePane();
        tilePane.setStyle("-fx-padding:10px");
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        if (items.size() > 0) {
            for (Item item : items) {
                tilePane.getChildren().add(new TemplateRow(item));
            }
            vbox.getChildren().addAll(
                    tilePane,
                    new AddTemplateBtn("Add new template: ")
            );
        } else {
            vbox.getChildren().addAll(
                    new SettingsMessage(),
                    new AddTemplateBtn("You have no custom templates yet, you can add one here: ")
            );
        }
        return vbox;
    }
}
