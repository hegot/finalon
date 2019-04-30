package finalonWindows.mainScene;

import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.SettingsMenu;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScene extends SceneBase {
    private Stage window;

    public MainScene(Stage windowArg) {
        window = windowArg;
    }

    public Scene getScene() {
        CustomControl customControl = new CustomControl(window);
        SettingsMenu settingsMenu = new SettingsMenu(window);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(settingsMenu.getMenu(), customControl);

        customControl.setPrefHeight(height() - 40);
        Scene scene = baseScene(vBox, 900);

        scene.getStylesheets().add("styles/mainStyle.css");
        return scene;
    }


}
