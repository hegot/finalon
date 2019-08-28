package finalon.finalonWindows.templateScene.listing;

import finalon.database.template.DbItemHandler;
import finalon.entities.Item;
import finalon.finalonWindows.SceneName;
import finalon.finalonWindows.SceneSwitcher;
import finalon.finalonWindows.templateScene.templates.EventHandlers.SaveHandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

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
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setWidth(400);
        dialog.setTitle("Select Industry");
        DialogPane dialogPane = dialog.getDialogPane();

        //dialogPane.setContent();
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE, ButtonType.OK);
        dialog.showAndWait();
        SceneSwitcher.goTo(SceneName.ADDTEMPLATE);
    }



}