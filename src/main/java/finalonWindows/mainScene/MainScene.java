package finalonWindows.mainScene;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import finalonWindows.ImageButton;
import finalonWindows.SceneBase;

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

    public Scene getMainScene(
            ImageButton CompanyButton,
            ImageButton SettingsButton,
            ImageButton HelpButton,
            ImageButton ExitButton
    ) {
        BorderPane root = new BorderPane();
        root.setTop(getHeader());
        VBox pane2 = new VBox();
        pane2.setBackground(background("#222C3C"));
        pane2.setPadding(new Insets(10, 10, 10, 10));
        pane2.setSpacing(10);
        pane2.getChildren().add(CompanyButton);
        pane2.getChildren().add(SettingsButton);
        pane2.getChildren().add(HelpButton);
        pane2.getChildren().add(ExitButton);
        root.setLeft(pane2);
        return new Scene(root, 900, 600);
    }
}
