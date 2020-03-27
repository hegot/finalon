package finalonWindows.mainScene;

import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import globalReusables.AddNewReport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

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
        AddNewReport.run();
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
    protected void helpAction() {
        SceneSwitcher.goTo(SceneName.TEMPLATESLIST);
    }

    @FXML
    protected void reportsAction() {
        SceneSwitcher.goTo(SceneName.REPORTLIST);
    }
}