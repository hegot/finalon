package finalonWindows.mainScene;

import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.SettingsMenu;
import javafx.scene.layout.VBox;

public class MainScene extends SceneBase {

    public VBox getScene() {
        CustomControl customControl = new CustomControl();
        SettingsMenu settingsMenu = new SettingsMenu();
        VBox vBox = new VBox();
        vBox.getStyleClass().add("main-screen");
        vBox.getChildren().addAll(settingsMenu.getMenu(), customControl);
        customControl.setPrefHeight(height() - 40);
        return vBox;
    }


}
