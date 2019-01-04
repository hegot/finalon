package finalonWindows.settingsScene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import finalonWindows.SceneBase;
import finalonWindows.settingsScene.templates.TemplateEditable;

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

    public Scene getSettingsScene() {
        BorderPane root = new BorderPane();
        TemplateEditable templateEditable = new TemplateEditable();
        root.setTop(templateEditable.getTable());
        return new Scene(root, 1000, 600);
    }
}
