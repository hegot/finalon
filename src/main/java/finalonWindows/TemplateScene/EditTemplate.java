package finalonWindows.TemplateScene;

import database.TemplateEditor;
import entities.Sheet;
import entities.Template;
import finalonWindows.TemplateScene.templates.TemplateEditable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditTemplate extends TemplateBase {

    protected Stage window;
    private ArrayList<Sheet> sheets;
    private TemplateEditable templateEditable;

    public EditTemplate(Stage windowArg, Template template, ArrayList<Sheet> sheets) {
        super(windowArg, template, sheets);
        this.sheets = sheets;
        this.templateEditable = new TemplateEditable(this.sheets);
    }

    private HBox headerMenu() {
        HBox hbox = new HBox(10);
        hbox.setStyle(whiteBorderedPanelMenu());
        Button saveTemplateButton = saveTemplateButton();
        saveTemplateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((templateName.getText() != null && !templateName.getText().isEmpty())) {
                    template.name = templateName.getText();
                    TemplateEditor updater = new TemplateEditor(template, templateEditable.getSheets());
                    updater.updateTpl();
                    redirectToSettings();
                } else {
                    System.out.println("err");
                }
            }
        });
        hbox.getChildren().addAll(backButton(), saveTemplateButton);
        return hbox;
    }


    public Scene getScene() {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(headerMenu(), templateName(), templateEditable.getTemplateEditable());
        Scene scene = baseScene(vBox);
        return scene;
    }

}



