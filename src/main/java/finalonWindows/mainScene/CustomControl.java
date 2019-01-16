package finalonWindows.mainScene;

import java.io.IOException;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomControl extends VBox {
    @FXML private TextField textField;
    private Stage window;

    public CustomControl(Stage window) {
        this.window = window;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
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
        System.out.println("The button was clicked!sss");
    }

    @FXML
    protected void settingsAction() {
        window.setScene(SceneSwitcher.getScenes().get(SceneName.SETTINGSMAIN));
        System.out.println("Accepted");
    }

    @FXML
    protected void helpAction() {
        System.out.println("The button was clicked!");
    }

    @FXML
    protected void exitAction() {
        System.out.println("The button was clicked!");
    }
}