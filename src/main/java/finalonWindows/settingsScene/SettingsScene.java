package finalonWindows.settingsScene;

import database.template.DbItemHandler;
import entities.Item;
import finalonWindows.ImageButton;
import finalonWindows.SceneBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SettingsScene extends SceneBase {

    private Stage window;

    public SettingsScene(Stage windowArg) {
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
        ObservableList<Item> iteems = dbItem.getTemplates();
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 20px 30px;");
        TilePane tilePane = new TilePane();
        tilePane.setStyle("-fx-padding:10px");
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        if (iteems.size() > 0) {
            for (int j = 0; j < iteems.size(); j++) {
                tilePane.getChildren().add(new TemplateRow(window, iteems.get(j)));
            }
            vbox.getChildren().addAll(tilePane, new AddTemplateBtn(window, "Add new template: "));
        } else {
            vbox.getChildren().addAll(new SettingsMessage(), new AddTemplateBtn(window, "You have no custom templates yet, you can add one here: "));
        }
        return vbox;
    }


    public ImageButton editTemplateButton(int id) {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/template.png"), new Image("/image/template.png"), 50);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println(id);

            }
        });
        return btn;
    }


}
