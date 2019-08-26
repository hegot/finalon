package finalon.finalonWindows.templateScene.templates;

import finalon.entities.Item;
import finalon.finalonWindows.SceneName;
import finalon.finalonWindows.SceneSwitcher;
import finalon.finalonWindows.templateScene.templates.EventHandlers.SaveHandler;
import finalon.globalReusables.CallTypes;
import finalon.globalReusables.StatTrigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EditTemplate {

    private static ObservableList<Item> items = FXCollections.observableArrayList();
    private TextField templateName;

    public EditTemplate(ObservableList<Item> itemsInput) {
        items = itemsInput;
    }


    public static ObservableList<Item> getItems() {
        return items;
    }



    public VBox getScene() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("template-screen");
        HBox hbox = new HBox(10);
        hbox.getStyleClass().add("whiteBorderedPanel");
        hbox.getChildren().addAll(backButton(), saveTemplateButton());
        vBox.getChildren().addAll(
                hbox,
                templateName(),
                TemplateEditable.getTemplateEditable()
        );
        return vBox;
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
        for (Item item : items) {
            if (item.getParent() == 0) {
                return item;
            }
        }
        return new Item(-2222, "", "", true, false, 0, 0, 0);
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
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((templateName.getText() != null && !templateName.getText().isEmpty())) {
                    Item rootItem = getRoot();
                    rootItem.setName(templateName.getText());
                    SaveHandler updater = new SaveHandler(templateName.getText());
                    updater.updateTpl();
                    StatTrigger.call(CallTypes.templates_customization_times);
                    SceneSwitcher.goTo(SceneName.TEMPLATESLIST);
                } else {
                    System.out.println("err");
                }
            }
        });
        return button;
    }

}



