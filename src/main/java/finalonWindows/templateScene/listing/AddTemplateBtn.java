package finalonWindows.templateScene.listing;

import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class AddTemplateBtn extends HBox {

    public AddTemplateBtn(String text) {
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
        SceneSwitcher.refresh(SceneName.ADDTEMPLATE);
    }

}