package finalonWindows.templateScene.listing;

import database.template.DbItemHandler;
import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.SettingsMenu;
import finalonWindows.templateScene.listing.partials.AddTemplateBtn;
import finalonWindows.templateScene.listing.partials.SettingsMessage;
import finalonWindows.templateScene.listing.partials.TemplateRow;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;



public class TemplatesListing extends SceneBase {

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
                if(item.getId() == 1){
                    tilePane.getChildren().add(defaultTpl());
                }else{
                    tilePane.getChildren().add(new TemplateRow(item));
                }
            }
            vbox.getChildren().addAll(
                    tilePane,
                    new SettingsMessage(),
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

    private VBox defaultTpl(){
        VBox vBox = new VBox();
        vBox.getStyleClass().add("row");
        Label label = new Label("Default Template");
        label.getStyleClass().add("row-label");
        Label label2 = new Label("(can not be edited)");
        label2.getStyleClass().add("row-description");
        vBox.getChildren().addAll(label, label2);
        return vBox;
    }

}
