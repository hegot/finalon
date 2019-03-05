package finalonWindows.templateScene;

import database.template.DbItemHandler;
import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.ImageButton;
import finalonWindows.templateScene.listing.AddTemplateBtn;
import finalonWindows.reusableComponents.SettingsMenu;
import finalonWindows.templateScene.listing.SettingsMessage;
import finalonWindows.templateScene.listing.TemplateRow;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TemplatesScene extends SceneBase {

    private Stage window;

    public TemplatesScene(Stage windowArg) {
        window = windowArg;
    }


    public Scene getScene() {
        VBox vbox = new VBox(0);
        vbox.setPrefSize(900, 600);
        vbox.setPrefHeight(600);

        SettingsMenu settingsMenu = new SettingsMenu(window);
        vbox.getChildren().addAll(settingsMenu.getMenu(), getTemplates());
        Scene scene = new Scene(vbox, 900, 600);
        scene.getStylesheets().add("styles/settingsStyle.css");
        return scene;
    }


    private VBox getTemplates() {
        DbItemHandler dbItem = new DbItemHandler();
        ObservableList<Item> items = dbItem.getTemplates();
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 20px 30px;");
        TilePane tilePane = new TilePane();
        tilePane.setStyle("-fx-padding:10px");
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        if (items.size() > 0) {
            for (Item item : items) {
                tilePane.getChildren().add(new TemplateRow(window, item));
            }
            vbox.getChildren().addAll(tilePane, new AddTemplateBtn(window, "Add new template: "));
        } else {
            vbox.getChildren().addAll(new SettingsMessage(), new AddTemplateBtn(window, "You have no custom templates yet, you can add one here: "));
        }
        return vbox;
    }


    public ImageButton editTemplateButton(int id) {
        ImageButton btn = new ImageButton("/image/template.png", 50);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println(id);

            }
        });
        return btn;
    }


}