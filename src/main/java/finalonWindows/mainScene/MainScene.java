package finalonWindows.mainScene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import finalonWindows.ImageButton;
import finalonWindows.SceneBase;
import finalonWindows.SceneSwitcher;
import finalonWindows.SceneName;


public class MainScene extends SceneBase {

    private Stage window;

    public MainScene(Stage windowArg) {
        window = windowArg;
    }

    private HBox getHeader() {
        HBox header = new HBox(10);
        header.setMaxHeight(180);
        header.setPrefHeight(180);
        header.setFillHeight(false);
        header.setBackground(background("#0095CD"));
        return header;
    }

    public Scene getScene() {
        BorderPane root = new BorderPane();
        root.setTop(getHeader());
        VBox pane2 = new VBox();
        pane2.setBackground(background("#222C3C"));
        pane2.setPadding(new Insets(10, 10, 10, 10));
        pane2.setSpacing(10);
        pane2.getChildren().add(addCompanyButton());
        pane2.getChildren().add(addSettingsButton());
        pane2.getChildren().add(addHelpButton());
        pane2.getChildren().add(addExitButton());
        root.setLeft(pane2);
        return new Scene(root, 900, 600);
    }


    public ImageButton addCompanyButton() {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/add.png"), new Image("/image/add1.png"), 50);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Accepted");
            }
        });
        return btn;
    }

    public ImageButton addSettingsButton() {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/settings.png"), new Image("/image/settings1.png"), 50);
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

    public ImageButton addHelpButton() {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/info.png"), new Image("/image/info1.png"), 50);
        return btn;
    }

    public ImageButton addExitButton() {
        ImageButton btn = new ImageButton();
        btn.updateImages(new Image("/image/exit.png"), new Image("/image/exit1.png"), 50);
        return btn;
    }
}
