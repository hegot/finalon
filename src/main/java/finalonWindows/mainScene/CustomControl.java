package finalonWindows.mainScene;

import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.SettingsStorage;

import java.io.IOException;

public class CustomControl extends BorderPane {

    public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main/main.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @FXML
    protected void addCompanyAction() {
        SettingsStorage.reInitStorage();
        SettingsStorage.put("reportId", "-1");
        ItemsStorage.emptyItems();
        SceneSwitcher.goTo(SceneName.ADDREPORT);
    }

    @FXML
    protected void settingsAction() {
        SceneSwitcher.goTo(SceneName.SETTINGSMAIN);
    }

    @FXML
    protected void templatesAction() {
        SceneSwitcher.goTo(SceneName.TEMPLATESLIST);
    }

    @FXML
    protected void formulaAction() {
        SceneSwitcher.goTo(SceneName.FORMULA);
    }

    @FXML
    protected void helpAction() {
        SceneSwitcher.goTo(SceneName.FORMULA);
    }

    @FXML
    protected void reportsAction() {
        SceneSwitcher.goTo(SceneName.REPORTLIST);
    }
}