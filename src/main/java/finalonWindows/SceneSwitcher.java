package finalonWindows;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import finalonWindows.mainScene.MainScene;
import finalonWindows.settingsScene.SettingsScene;
import finalonWindows.addTemplateScene.AddTemplateScene;
import java.util.HashMap;
import java.util.Map;


public class SceneSwitcher {

    private Stage window;
    private Scene mainScene;
    private Scene settingsScene;


    /** Holds the various scenes to switch between */
    private static Map<SceneName, Scene> scenes = new HashMap<>();

    public SceneSwitcher(Stage windowArg) {
        window = windowArg;
        scenes.put(SceneName.MAIN,  new MainScene(window).getScene());
        scenes.put(SceneName.SETTINGSMAIN, new SettingsScene(window).getScene());
        scenes.put(SceneName.ADDTEMPLATE, new AddTemplateScene(window).getScene());

    }


    /** Returns a Map of the scenes by {@link SceneName} */
    public static Map<SceneName, Scene> getScenes() {
        return scenes;
    }





}
