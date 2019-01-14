package finalonWindows.settingsScene;

import defaultTemplate.DefaultTemplate;
import entities.Item;
import entities.Sheet;
import finalonWindows.ImageButton;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import finalonWindows.SceneBase;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class SettingsScene extends SceneBase {

    private Stage window;

    public SettingsScene(Stage windowArg) {
        window = windowArg;
    }

    private HBox getHeader() {
        HBox header = new HBox(10);
        header.setMaxHeight(180);
        header.setPrefHeight(180);
        header.setFillHeight(false);
        header.setBackground(background("#000000"));
        return header;
    }

    public Scene getScene() {
        Label label2= new Label("This is the second scene");
        HBox layout2= new HBox(20);
        layout2.getChildren().addAll(label2,  addSettingsButton());
        Scene scene = new Scene(layout2,300,250);
        return scene;
    }

    public ImageButton addSettingsButton() {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/settings.png"), new Image("/image/settings1.png"));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                window.setScene(SceneSwitcher.getScenes().get(SceneName.ADDTEMPLATE));

            }
        });
        return btn;
    }
}
