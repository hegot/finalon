package finalonWindows.mainScene;

import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class MainScene extends SceneBase {

    private Stage window;

    public MainScene(Stage windowArg) {
        window = windowArg;
    }

    private HBox getHeader() {
        HBox header = new HBox(10);
        header.setMaxHeight(100);
        header.setPrefHeight(100);
        header.setStyle(whiteBorderedPanel());
        Label label = new Label("Financial Statement Analysis Application");
        label.setPadding(new Insets(7, 0, 0, 0));
        label.setFont(Font.font("Arial", 20));
        label.setTextFill(Color.web("#6a6c6f"));
        header.getChildren().add(label);
        return header;
    }

    public Scene getScene() {
        BorderPane root = new BorderPane();
        root.setTop(getHeader());
        root.setLeft(getSidebar());
        return new Scene(root, 900, 600);
    }

    private VBox getSidebar() {
        VBox sidebar = new VBox(10);
        sidebar.setStyle(whiteBorderedPanel());
        sidebar.getChildren().add(addCompanyButton());
        sidebar.getChildren().add(addSettingsButton());
        sidebar.getChildren().add(addHelpButton());
        sidebar.getChildren().add(addExitButton());
        return sidebar;
    }


    public Button addCompanyButton() {
        Button btn = new Button("Add Company");
        btn.setStyle(sidebarBlueButonStyle());
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Accepted");
            }
        });
        return btn;
    }

    public Button addSettingsButton() {
        Button btn = new Button("Settings");
        btn.setStyle(sidebarBlueButonStyle());
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //setSettingsScene();

                window.setScene(SceneSwitcher.getScenes().get(SceneName.SETTINGSMAIN));
                System.out.println("Accepted");

            }
        });
        return btn;
    }

    public Button addHelpButton() {
        Button btn = new Button("Help");
        btn.setStyle(sidebarBlueButonStyle());
        return btn;
    }

    public Button addExitButton() {
        Button btn = new Button("Exit");
        btn.setStyle(sidebarBlueButonStyle());
        return btn;
    }
}
