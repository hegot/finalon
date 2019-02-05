package finalonWindows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneBase {
    public Background background(String color) {
        return new Background(new BackgroundFill(Color.web(color), CornerRadii.EMPTY, Insets.EMPTY));
    }

    protected String whiteBorderedPanel() {
        return "-fx-padding: 15; -fx-border-style: solid outside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 2;" + "-fx-border-color: #e4e5e7; -fx-background-color: #fff;";
    }

    protected String whiteBorderedPanelMenu() {
        return "-fx-padding: 5; -fx-border-style: solid outside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 2;" + "-fx-border-color: #e4e5e7; -fx-background-color: #fff;";
    }

    protected String blueButonStyle() {
        return "-fx-background-color:#3f5872; -fx-font-size: 14px; -fx-text-fill: #FFFFFF; -fx-padding: 5 10 5 10;   -fx-alignment:  baseline-left;";
    }

    protected String sidebarBlueButonStyle() {
        return "-fx-background-color:#3f5872; -fx-font-size: 14px; -fx-text-fill: #FFFFFF; -fx-padding: 10 25 10 25;  -fx-pref-width: 150px; -fx-alignment:  baseline-left;";
    }

    protected CustomMenu topMenu() {
        CustomMenu CustomMenu = new CustomMenu();
        return CustomMenu;
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

    @FXML
    protected void homeAction() {
        Stage window = (Stage) this.getScene().getWindow();
        window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.MAIN));
    }

    @FXML
    protected void addCompanyAction() {
        System.out.println("The button was clicked!sss");
    }

    @FXML
    protected void settingsAction() {
        Stage window = (Stage) this.getScene().getWindow();
        window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.SETTINGSMAIN));
    }

    @FXML
    protected void helpAction() {
        System.out.println("The button was clicked!");
    }

    @FXML
    protected void exitAction() {
        System.out.println("The button was clicked!");
    }
}