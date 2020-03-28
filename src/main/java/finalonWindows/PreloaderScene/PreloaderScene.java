package finalonWindows.PreloaderScene;

import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.reusableComponents.SettingsMenu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PreloaderScene {
    public static VBox getScene(int duration, SceneName redirect) {
        VBox vbox = new VBox(0);
        vbox.getChildren().addAll(new SettingsMenu().getMenu(), getThrobber());
        if (redirect != null) {
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(duration),
                    ae -> SceneSwitcher.goTo(redirect)));
            timeline.play();
        }
        return vbox;
    }

    public static VBox getThrobber() {
        Image img = new Image("image/loading.gif");
        ImageView iv = new ImageView(img);
        iv.setFitHeight(400);
        iv.setFitWidth(400);
        VBox vBox = new VBox(iv);
        vBox.setPrefHeight(730);
        vBox.setFillWidth(true);
        vBox.setAlignment(Pos.TOP_CENTER);
        return vBox;
    }
}
