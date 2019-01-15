package finalonWindows.addTemplateScene;

import database.TemplateCreator;
import defaultTemplate.DefaultTemplate;
import entities.Sheet;
import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.addTemplateScene.templates.TemplateEditable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddTemplateScene extends SceneBase {

    private Stage window;
    private TextField templateName;

    public AddTemplateScene(Stage windowArg) {
        window = windowArg;
    }


    private HBox headerMenu() {
        HBox hbox = new HBox(10);
        hbox.setStyle(whiteBorderedPanelMenu());
        hbox.getChildren().addAll(backButton(), addTemplateButton());
        return hbox;
    }


    private HBox templateName() {
        HBox hbox = new HBox(10);
        hbox.setStyle(whiteBorderedPanelMenu());
        Label label = new Label("Enter your template name: ");
        label.setPadding(new Insets(3, 0, 0, 0));
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.web("#6a6c6f"));
        templateName = new TextField();
        templateName.setPrefWidth(300);
        hbox.getChildren().addAll(label, templateName);
        return hbox;
    }


    public Scene getScene() {
        ArrayList<Sheet> sheets = DefaultTemplate.getSheets();
        TemplateEditable templateEditable = new TemplateEditable(sheets);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(headerMenu(), templateName(), templateEditable.getTemplateEditable());
        scrollPane.setContent(vBox);

        Scene scene = new Scene(scrollPane, 1000, 800);
        scene.getStylesheets().add("/styles/style.css");
        return scene;
    }

    public Button backButton() {
        Button button = new Button("Back to Settings");
        button.setStyle(blueButonStyle());
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                window.setScene(SceneSwitcher.getScenes().get(SceneName.SETTINGSMAIN));

            }
        });
        return button;
    }

    public Button addTemplateButton() {
        Button button = new Button("Save Template");
        button.setStyle(blueButonStyle());
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((templateName.getText() != null && !templateName.getText().isEmpty())) {
                    TemplateCreator creator = new TemplateCreator(templateName.getText(), DefaultTemplate.getSheets());
                    creator.createTpl();
                    window.setScene(SceneSwitcher.getScenes().get(SceneName.SETTINGSMAIN));
                } else {
                    System.out.println("err");
                }



            }
        });
        return button;
    }


}



