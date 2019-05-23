package finalonWindows;

import defaultData.DefaultTemplate;
import entities.Item;
import reportGeneration.AddReportScene;
import finalonWindows.formulaScene.FormulaScene;
import finalonWindows.mainScene.MainScene;
import finalonWindows.settingsScene.SettingsScene;
import finalonWindows.templateScene.AddTemplate;
import finalonWindows.templateScene.TemplatesScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;


public class SceneSwitcher {

    /**
     * Holds the various scenes to switch between
     */
    private static Map<SceneName, VBox> scenes = new HashMap<>();
    private static VBox window;


    public SceneSwitcher(VBox windowArg) {
        window = windowArg;

        ObservableList<Item> items = FXCollections.observableArrayList(DefaultTemplate.getTpl());
        scenes.put(SceneName.MAIN, new MainScene().getScene());
        scenes.put(SceneName.SETTINGSMAIN, new SettingsScene().getScene());
        scenes.put(SceneName.TEMPLATESLIST, new TemplatesScene().getScene());
        scenes.put(SceneName.ADDTEMPLATE, new AddTemplate(items).getScene());
        scenes.put(SceneName.FORMULA, new FormulaScene().getScene());
        scenes.put(SceneName.ADDREPORT, new AddReportScene().getScene());
    }

    public static void goTo(SceneName sceneName) {
        window.getChildren().setAll(scenes.get(sceneName));
    }

    public static VBox getWindow() {
        return window;
    }

    /**
     * Returns a Map of the scenes by {@link SceneName}
     */
    public static void refresh(SceneName sceneName) {
        switch (sceneName) {
            case TEMPLATESLIST:
                scenes.put(SceneName.TEMPLATESLIST, new TemplatesScene().getScene());
                break;
            case ADDTEMPLATE:
                ObservableList<Item> items = FXCollections.observableArrayList(DefaultTemplate.getTpl());
                scenes.put(SceneName.ADDTEMPLATE, new AddTemplate(items).getScene());
                break;
            case ADDREPORT:
                scenes.put(SceneName.ADDREPORT, new AddReportScene().getScene());
                break;
            case FORMULA:
                scenes.put(SceneName.FORMULA, new FormulaScene().getScene());
                break;
        }
        window.getChildren().setAll(scenes.get(sceneName));
    }
}
