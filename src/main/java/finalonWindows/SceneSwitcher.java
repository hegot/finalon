package finalonWindows;

import defaultData.DefaultTemplate;
import entities.Item;
import finalonWindows.addReport.AddReportScene;
import finalonWindows.formulaScene.FormulaScene;
import finalonWindows.mainScene.MainScene;
import finalonWindows.settingsScene.SettingsScene;
import finalonWindows.templateScene.AddTemplate;
import finalonWindows.templateScene.TemplatesScene;
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


    public SceneSwitcher(Stage windowArg) {
        window = windowArg;
        ObservableList<Item> items = FXCollections.observableArrayList(DefaultTemplate.getTpl());
        scenes.put(SceneName.MAIN, new MainScene(window).getScene());
        scenes.put(SceneName.SETTINGSMAIN, new SettingsScene(window).getScene());
        scenes.put(SceneName.TEMPLATESLIST, new TemplatesScene(window).getScene());
        scenes.put(SceneName.ADDTEMPLATE, new AddTemplate(window, items).getScene());
        scenes.put(SceneName.FORMULA, new FormulaScene(window).getScene());
        scenes.put(SceneName.ADDREPORT, new AddReportScene(window).getScene());
    }


    /**
     * Returns a Map of the scenes by {@link SceneName}
     */
    public static Map<SceneName, Scene> getScenes(SceneName sceneName) {
        if (sceneName == SceneName.TEMPLATESLIST) {
            scenes.put(SceneName.TEMPLATESLIST, new TemplatesScene(window).getScene());
        }
        if (sceneName == SceneName.ADDTEMPLATE) {
            ObservableList<Item> items = FXCollections.observableArrayList(DefaultTemplate.getTpl());
            scenes.put(SceneName.ADDTEMPLATE, new AddTemplate(window, items).getScene());
        }
        return scenes;
    }
}
