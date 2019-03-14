package finalonWindows;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.io.IOException;

public class SceneBase {


    protected CustomMenu topMenu() {
        CustomMenu CustomMenu = new CustomMenu();
        return CustomMenu;
    }

    protected Scene baseScene(VBox vBox, double width) {
        vBox.setMinWidth(800);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setMinWidth(900);
        scrollPane.setPrefHeight(height());
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        Scene scene = new Scene(scrollPane);
        return scene;
    }

    protected double height() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        return primaryScreenBounds.getHeight() - 100;
    }

}

class CustomMenu extends HBox {

    public CustomMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu/menu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}