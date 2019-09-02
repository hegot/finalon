package finalonWindows;

import defaultData.DefaultTemplate;
import entities.Item;
import finalonWindows.formulaScene.FormulaScene;
import finalonWindows.mainScene.MainScene;
import finalonWindows.settingsScene.SettingsScene;
import finalonWindows.templateScene.listing.TemplatesListing;
import finalonWindows.templateScene.templates.TemplateEditPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.AddReportScene;


public class SceneSwitcher {

    /**
     * Holds the various scenes to switch between
     */
    private static VBox window;


    public SceneSwitcher(VBox windowArg) {
        window = windowArg;
    }

    public static void goTo(SceneName sceneName) {
        VBox vbox = new VBox();
        switch (sceneName) {
            case MAIN:
                vbox = new MainScene().getScene();
                break;
            case SETTINGSMAIN:
                vbox = new SettingsScene().getScene();
                break;
            case TEMPLATESLIST:
                vbox = new TemplatesListing().getScene();
                break;
            case ADDTEMPLATE:
                ObservableList<Item> items = FXCollections.observableArrayList(DefaultTemplate.getTpl());
                vbox = new TemplateEditPage(items).getScene();
                break;
            case FORMULA:
                vbox = new FormulaScene().getScene();
                break;
            case ADDREPORT:
                vbox = new AddReportScene().getScene();
                break;
        }
        window.getChildren().setAll(vbox);
    }

    public static void goTo(SceneName sceneName, int id) {
        VBox vbox = new VBox();
        switch (sceneName) {
            case ADDTEMPLATE:
                ObservableList<Item> items = FXCollections.observableArrayList(DefaultTemplate.getTpl());
                vbox = new TemplateEditPage(items, id).getScene();
                break;
        }
        window.getChildren().setAll(vbox);
    }

    public static VBox getWindow() {
        return window;
    }

}
