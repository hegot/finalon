package finalonWindows.mainScene;

import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomControl extends BorderPane {
    @FXML
    private TextField textField;
    private Stage window;

    public CustomControl(Stage window) {
        this.window = window;
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
        window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.ADDREPORT));
    }

    @FXML
    protected void settingsAction() {
        window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.SETTINGSMAIN));
    }

    @FXML
    protected void templatesAction() {
        window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.TEMPLATESLIST));
    }

    @FXML
    protected void formulaAction() {
        window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.FORMULA));
    }

    @FXML
    protected void helpAction() {
        window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.FORMULA));
    }

}