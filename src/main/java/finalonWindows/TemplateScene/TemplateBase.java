package finalonWindows.TemplateScene;

import entities.Sheet;
import entities.Template;
import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
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

public class TemplateBase extends SceneBase {

    protected Stage window;
    protected Template template;
    protected TextField templateName;

    TemplateBase(
            Stage windowArg,
            Template template,
            ArrayList<Sheet> sheets) {
        this.window = windowArg;
        this.template = template;

    }

    TemplateBase(Stage windowArg) {
        this.window = windowArg;
        this.template = new Template(0, "");
    }

    HBox templateName() {
        HBox hbox = new HBox(10);
        hbox.setStyle(whiteBorderedPanelMenu());
        Label label = new Label("Enter your template name: ");
        label.setPadding(new Insets(3, 0, 0, 0));
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.web("#6a6c6f"));
        templateName = new TextField();
        templateName.setText(this.template.name);
        templateName.setPrefWidth(300);
        hbox.getChildren().addAll(label, templateName);
        return hbox;
    }


    Button backButton() {
        Button button = new Button("Back to Settings");
        button.setStyle(blueButonStyle());
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.SETTINGSMAIN));

            }
        });
        return button;
    }

    Button saveTemplateButton() {
        Button button = new Button("Save Template");
        button.setStyle(blueButonStyle());
        return button;
    }

    Scene baseScene(VBox vBox) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(vBox);
        Scene scene = new Scene(scrollPane, 1000, 800);
        scene.getStylesheets().add("/styles/templateStyle.css");
        return scene;
    }

    void redirectToSettings() {
        window.setScene(SceneSwitcher.getScenes(SceneName.SETTINGSMAIN).get(SceneName.SETTINGSMAIN));
    }


}
