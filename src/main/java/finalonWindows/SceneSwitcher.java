package finalonWindows;

import finalonWindows.addTemplateScene.AddTemplateScene;
import finalonWindows.mainScene.MainScene;
import finalonWindows.settingsScene.SettingsScene;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class SceneSwitcher {

    /**
     * Holds the various scenes to switch between
     */
    private static Map<SceneName, Scene> scenes = new HashMap<>();
    private Stage window;
    private Scene mainScene;
    private Scene settingsScene;

    public SceneSwitcher(Stage windowArg) {
        window = windowArg;
        scenes.put(SceneName.MAIN, new MainScene(window).getScene());
        scenes.put(SceneName.SETTINGSMAIN, new SettingsScene(window).getScene());
        scenes.put(SceneName.ADDTEMPLATE, new AddTemplateScene(window).getScene());

    }


    /**
     * Returns a Map of the scenes by {@link SceneName}
     */
    public static Map<SceneName, Scene> getScenes() {
        return scenes;
    }


}
