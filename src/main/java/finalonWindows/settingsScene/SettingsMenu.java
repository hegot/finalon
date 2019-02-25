package finalonWindows.settingsScene;

import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SettingsMenu {

    private Stage window;

    public SettingsMenu(Stage window) {
        this.window = window;
    }

    private String blueButonStyle() {
        return "-fx-background-color:#3f5872; -fx-font-size: 14px; -fx-text-fill: #FFFFFF; -fx-padding: 5 10 5 10;   -fx-alignment:  baseline-left;";
    }

    public HBox getMenu() {
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(5, 10, 10, 10));
        hbox.getChildren().addAll(HomeButton(), TemplateButton(), FormulaButton());
        return hbox;
    }

    private double height() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        return primaryScreenBounds.getHeight();
    }

    private Button TemplateButton() {
        Button btn = new Button("Template Customization");
        btn.setStyle(blueButonStyle());
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.SETTINGSMAIN));
                window.setHeight(600);
            }
        });
        return btn;
    }

    private Button FormulaButton() {
        Button btn = new Button("Formula Customization");
        btn.setStyle(blueButonStyle());
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.FORMULA));
                window.setHeight(height());
            }
        });
        return btn;
    }

    private Button HomeButton() {
        Button btn = new Button("Home");
        btn.setStyle(blueButonStyle());
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.MAIN));
                window.setHeight(600);
            }
        });
        return btn;
    }
}
