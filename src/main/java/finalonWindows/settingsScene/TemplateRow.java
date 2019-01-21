package finalonWindows.settingsScene;

import database.DbSheetHandler;
import entities.Sheet;
import entities.Template;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.TemplateScene.EditTemplate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TemplateRow extends VBox {
    @FXML
    private int tplId;
    private Stage window;
    private Template template;

    public TemplateRow(Stage window, Template template) {
        this.window = window;
        this.template = template;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings/templateRow.fxml"));
        fxmlLoader.getNamespace().put("labelText", template.name);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @FXML
    protected void viewRowAction() {
        window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.ADDTEMPLATE));
        System.out.println("The button was clicked!sss");
    }

    @FXML
    protected void editRowAction() {
        DbSheetHandler sheetHandler = new DbSheetHandler();
        ArrayList<Sheet> sheets = sheetHandler.getSheets(this.template.id);
        EditTemplate editTpl = new EditTemplate(window, template, sheets);
        window.setScene(editTpl.getScene());
    }

    @FXML
    protected void deleteRowAction() {
        System.out.println("Delete row");
    }

}