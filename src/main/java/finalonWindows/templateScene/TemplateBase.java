package finalonWindows.templateScene;

import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.collections.ObservableList;
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

public class TemplateBase extends SceneBase {

    protected Stage window;
    protected ObservableList<Item> items;
    TextField templateName;

    TemplateBase(
            Stage windowArg,
            ObservableList<Item> items) {
        this.window = windowArg;
        this.items = items;
    }

    TemplateBase(Stage windowArg) {
        this.window = windowArg;
    }

    HBox templateName() {
        HBox hbox = new HBox(10);
        hbox.getStyleClass().add("whiteBorderedPanel");
        Label label = new Label("Enter your template name: ");
        label.setPadding(new Insets(3, 0, 0, 0));
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.web("#6a6c6f"));
        templateName = new TextField();
        Item rootItem = getRoot();
        templateName.setText(rootItem.getName());
        templateName.setPrefWidth(300);
        hbox.getChildren().addAll(label, templateName);
        return hbox;
    }

    Item getRoot() {
        for (Item item : this.items) {
            if (item.getParent() == 0) {
                return item;
            }
        }
        return new Item(1, "", "", true, false, 0, 0);
    }


    Button backButton() {
        Button button = new Button("Back to Settings");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.TEMPLATESLIST));
                window.setHeight(600);
                window.setWidth(900);
            }
        });
        return button;
    }

    Button saveTemplateButton() {
        Button button = new Button("Save Template");
        button.getStyleClass().add("blue-btn");
        return button;
    }

    Scene baseScene(VBox vBox) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(vBox);
        Scene scene = new Scene(scrollPane, 900, 800);
        scene.getStylesheets().add("/styles/templateStyle.css");
        return scene;
    }

    void redirectToSettings() {
        window.setScene(SceneSwitcher.getScenes(SceneName.TEMPLATESLIST).get(SceneName.TEMPLATESLIST));
    }


}
