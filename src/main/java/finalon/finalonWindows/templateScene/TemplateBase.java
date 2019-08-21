package finalon.finalonWindows.templateScene;

import finalon.entities.Item;
import finalon.finalonWindows.SceneBase;
import finalon.finalonWindows.SceneName;
import finalon.finalonWindows.SceneSwitcher;
import finalon.globalReusables.CallTypes;
import finalon.globalReusables.StatTrigger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TemplateBase extends SceneBase {

    protected ObservableList<Item> items;
    TextField templateName;

    TemplateBase(
            ObservableList<Item> items) {
        this.items = items;
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
        return new Item(1, "", "", true, false, 0, 0, 0);
    }


    Button backButton() {
        Button button = new Button("Back to Settings");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SceneSwitcher.goTo(SceneName.TEMPLATESLIST);
            }
        });
        return button;
    }

    Button saveTemplateButton() {
        Button button = new Button("Save Template");
        button.getStyleClass().add("blue-btn");
        StatTrigger.call(CallTypes.templates_customization_times);
        return button;
    }


}
