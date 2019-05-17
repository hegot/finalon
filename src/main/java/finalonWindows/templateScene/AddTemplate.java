package finalonWindows.templateScene;

import database.template.TemplateCreator;
import entities.Item;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.templateScene.templates.TemplateEditable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddTemplate extends TemplateBase {

    protected ObservableList<Item> items;
    private TemplateEditable templateEditable;

    public AddTemplate(ObservableList<Item> items) {
        super(items);
        this.items = items;
        this.templateEditable = new TemplateEditable(items);
    }

    private HBox headerMenu() {
        HBox hbox = new HBox(10);
        hbox.getStyleClass().add("whiteBorderedPanel");
        Button saveTemplateButton = saveTemplateButton();
        saveTemplateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((templateName.getText() != null && !templateName.getText().isEmpty())) {
                    String tplName = templateName.getText();
                    Item mainItem = new Item(1, tplName, tplName, true, false, 0, 0);
                    ObservableList<Item> items = templateEditable.getItems();
                    items.add(mainItem);
                    TemplateCreator creator = new TemplateCreator(templateName.getText(), items);
                    creator.createTpl();
                    SceneSwitcher.refresh(SceneName.TEMPLATESLIST);
                } else {
                    System.out.println("err");
                }
            }
        });
        hbox.getChildren().addAll(backButton(), saveTemplateButton);
        return hbox;
    }

    public VBox getScene() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("template-screen");
        vBox.getChildren().addAll(
                headerMenu(),
                templateName(),
                templateEditable.getTemplateEditable()
        );
        return vBox;
    }
}



