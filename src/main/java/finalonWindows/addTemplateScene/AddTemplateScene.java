package finalonWindows.addTemplateScene;

import defaultTemplate.DefaultTemplate;
import entities.Item;
import entities.Sheet;
import finalonWindows.ImageButton;
import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import  finalonWindows.addTemplateScene.templates.*;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class AddTemplateScene extends SceneBase {

    private Stage window;

    public AddTemplateScene(Stage windowArg) {
        window = windowArg;
    }


    public Scene getScene() {
        ObservableList<Item> allItems = DefaultTemplate.getAllItems();
        ArrayList<Sheet> sheets = DefaultTemplate.getSheets();
        TemplateEditable templateEditable = new TemplateEditable(allItems, sheets);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(900, 550);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(backButton(), templateEditable.getTemplateEditable(), addTemplateButton());
        scrollPane.setContent(vBox);

        Scene scene = new Scene(scrollPane, 1000, 900);
        scene.getStylesheets().add("/styles/style.css");
        return scene;
    }

    public Button backButton() {
        Button button = new Button("<- Back to Settings");
        button.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
        button.setStyle("-fx-background-color: #69E781");
        button.setStyle("-fx-font-size: 2em; ");
        button.setStyle("-fx-text-fill: #0B2239");
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
        button.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
        button.setStyle("-fx-background-color: #69E781");
        button.setStyle("-fx-font-size: 2em; ");
        button.setStyle("-fx-text-fill: #0B2239");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                window.setScene(SceneSwitcher.getScenes().get(SceneName.SETTINGSMAIN));

            }
        });
        return button;
    }
}
