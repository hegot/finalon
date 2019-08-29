package finalon.finalonWindows.templateScene.templates;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Formula;
import finalon.entities.Item;
import finalon.finalonWindows.SceneName;
import finalon.finalonWindows.SceneSwitcher;
import finalon.finalonWindows.templateScene.templates.EventHandlers.TemplateSaveHandler;
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

public class TemplateEditPage {

    private static ObservableList<Item> items = FXCollections.observableArrayList();
    private TextField templateName;
    private Integer industry;

    public TemplateEditPage(ObservableList<Item> itemsInput, int industryId) {
        items = itemsInput;
        industry = industryId;
    }

    public TemplateEditPage(ObservableList<Item> itemsInput) {
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
                TemplateEditTable.getTemplateEditable()
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
        if(industry != null){
            Formula industryForm = DbFormulaHandler.findById(industry);
            if(industryForm != null){
                String name = "Template for " + industryForm.getName() + " industry";
                rootItem.setName(name);
            }
        }
        templateName.setText(rootItem.getName());
        templateName.setPrefWidth(300);
        hbox.getChildren().addAll(label, templateName);
        return hbox;
    }

    Item getRoot() {
        for (Item item : items) {
            if (item.getParent() == 0) {
                if(industry != null){
                    item.setParentSheet(industry);
                }
                return item;
            }
        }
        return new Item(-2, "", "", true, false, 0, 0, 0);
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
                    TemplateSaveHandler updater = new TemplateSaveHandler(templateName.getText());
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



