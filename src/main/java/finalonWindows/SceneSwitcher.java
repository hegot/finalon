package finalonWindows;

import finalonWindows.DeathScreen.DeathScreen;
import finalonWindows.LogsScreen.LogsScreen;
import finalonWindows.PreloaderScene.PreloaderScene;
import finalonWindows.mainScene.MainScene;
import finalonWindows.reportsScene.ReportsListing;
import finalonWindows.settingsScene.SettingsScene;
import finalonWindows.templateScene.FormulaEditPage.FormulaEditPage;
import finalonWindows.templateScene.TemplateEditPage.TemplateEditPage;
import finalonWindows.templateScene.listing.TemplatesListing;
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
            case REPORTLIST:
                vbox = new ReportsListing().getScene();
                break;
            case ADDREPORT:
                vbox = new AddReportScene().getScene();
                break;
            case DEATHSCREEN:
                vbox = new VBox();
                vbox.getChildren().add(new DeathScreen());
                break;
            case LOGS:
                vbox = new LogsScreen().getScene();
                break;

        }
        window.getChildren().setAll(vbox);
    }

    public static void goTo(SceneName sceneName, int id) {
        VBox vbox = new VBox();
        switch (sceneName) {
            case ADDTEMPLATE:
                vbox = TemplateEditPage.getScene(id);
                break;
            case EDITFORMULAS:
                vbox = FormulaEditPage.getScene(id);
                break;
        }
        window.getChildren().setAll(vbox);
    }

    public static void runPreloader(int duration, SceneName redirect) {
        VBox vbox = PreloaderScene.getScene(duration, redirect);
        window.getChildren().setAll(vbox);
    }

    public static VBox getWindow() {
        return window;
    }

}
