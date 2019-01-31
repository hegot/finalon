package finalonWindows;

import defaultTemplate.DefaultTemplate;
import entities.Item;
import finalonWindows.TemplateScene.AddTemplate;
import finalonWindows.mainScene.MainScene;
import finalonWindows.settingsScene.SettingsScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class SceneSwitcher {

    /**
     * Holds the various scenes to switch between
     */
    private static Map<SceneName, Scene> scenes = new HashMap<>();
    private static Stage window;
    private final static ObservableList<Item> items = FXCollections.observableArrayList(DefaultTemplate.getTpl());

    public SceneSwitcher(Stage windowArg) {
        window = windowArg;
        scenes.put(SceneName.MAIN, new MainScene(window).getScene());
        scenes.put(SceneName.SETTINGSMAIN, new SettingsScene(window).getScene());
        scenes.put(SceneName.ADDTEMPLATE, new AddTemplate(window, items).getScene());
    }


    /**
     * Returns a Map of the scenes by {@link SceneName}
     */
    public static Map<SceneName, Scene> getScenes(SceneName sceneName) {
        if (sceneName == SceneName.SETTINGSMAIN) {
            scenes.put(SceneName.SETTINGSMAIN, new SettingsScene(window).getScene());
        }
        if (sceneName == SceneName.ADDTEMPLATE) {
            scenes.put(SceneName.ADDTEMPLATE, new AddTemplate(window, items).getScene());
        }
        return scenes;
    }
}
