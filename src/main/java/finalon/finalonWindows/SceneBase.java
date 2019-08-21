package finalon.finalonWindows;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class SceneBase {
    protected double height() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        return primaryScreenBounds.getHeight() - 100;
    }

}