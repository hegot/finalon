package finalonWindows.templateScene.listing;

import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTemplateBtn extends HBox {
    private Stage window;

    public AddTemplateBtn(Stage window, String text) {
        this.window = window;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings/addTplBtn.fxml"));
        fxmlLoader.getNamespace().put("labelText", text);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @FXML
    protected void addTemplateAction() {
        window.setScene(SceneSwitcher.getScenes(SceneName.ADDTEMPLATE).get(SceneName.ADDTEMPLATE));
    }

}