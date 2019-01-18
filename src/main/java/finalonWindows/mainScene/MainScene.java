package finalonWindows.mainScene;

import finalonWindows.SceneBase;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScene extends SceneBase {

    private Stage window;

    public MainScene(Stage windowArg) {
        window = windowArg;
    }


    public Scene getScene() {
        CustomControl customControl = new CustomControl(window);
        Scene scene = new Scene(customControl, 800, 500);
        scene.getStylesheets().add("styles/mainStyle.css");
        return scene;
    }


}
