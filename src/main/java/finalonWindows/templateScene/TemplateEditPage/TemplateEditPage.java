package finalonWindows.templateScene.TemplateEditPage;

import database.template.DbItemHandler;
import entities.Item;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.templateScene.TemplateEditPage.Cells.FormulaStorage;
import finalonWindows.templateScene.TemplateEditPage.EventHandlers.TemplateUpdateHandler;
import globalReusables.CallTypes;
import globalReusables.StatTrigger;
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

public class TemplateEditPage {
    private static TextField templateName;

    public static VBox getScene(int tplId) {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("template-screen");
        HBox hbox = new HBox(10);
        hbox.getStyleClass().add("whiteBorderedPanel");
        hbox.getChildren().addAll(backButton(), saveTemplateButton());
        vBox.getChildren().addAll(
                hbox,
                templateName(tplId),
                TemplateEditTable.getTemplateEditable(tplId)
        );
        return vBox;
    }

    static HBox templateName(int tplId) {
        HBox hbox = new HBox(10);
        Item root = DbItemHandler.getItem(tplId);
        if (root != null) {
            hbox.getStyleClass().add("whiteBorderedPanel");
            Label label = new Label("Enter your template name: ");
            label.setPadding(new Insets(3, 0, 0, 0));
            label.setFont(Font.font("Arial", 14));
            label.setTextFill(Color.web("#6a6c6f"));
            templateName = new TextField();
            templateName.setText(root.getName());
            templateName.setPrefWidth(300);
            hbox.getChildren().addAll(label, templateName);
        }
        return hbox;
    }


    static Button backButton() {
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

    static Button saveTemplateButton() {
        Button button = new Button("Save Template");
        button.getStyleClass().add("blue-btn");
        StatTrigger.call(CallTypes.templates_customization_times);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((templateName.getText() != null && !templateName.getText().isEmpty())) {
                    TemplateUpdateHandler updater = new TemplateUpdateHandler(templateName.getText());
                    updater.updateTpl();
                    FormulaStorage.updateFormulas();
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



