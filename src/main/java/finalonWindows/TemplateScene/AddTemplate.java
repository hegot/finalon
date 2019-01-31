package finalonWindows.TemplateScene;

import database.TemplateCreator;
import entities.Item;
import finalonWindows.TemplateScene.templates.TemplateEditable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddTemplate extends TemplateBase {

    protected ObservableList<Item> items;
    private TemplateEditable templateEditable;

    public AddTemplate(Stage windowArg, ObservableList<Item> items) {
        super(windowArg, items);
        this.items = items;
        this.templateEditable = new TemplateEditable(items);
        this.window = windowArg;
    }

    private HBox headerMenu() {
        HBox hbox = new HBox(10);
        hbox.setStyle(whiteBorderedPanelMenu());
        Button saveTemplateButton = saveTemplateButton();
        saveTemplateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((templateName.getText() != null && !templateName.getText().isEmpty())) {
                    String tplName = templateName.getText();
                    Item mainItem = new Item(1, tplName, tplName, true, 0, 0);
                    ObservableList<Item> items = templateEditable.getItems();
                    items.add(mainItem);
                    TemplateCreator creator = new TemplateCreator(templateName.getText(), items);
                    creator.createTpl();
                    redirectToSettings();
                    window.setHeight(600);
                } else {
                    System.out.println("err");
                }
            }
        });
        hbox.getChildren().addAll(backButton(), saveTemplateButton);
        return hbox;
    }

    public Scene getScene() {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(headerMenu(), templateName(), templateEditable.getTemplateEditable());
        Scene scene = baseScene(vBox);
        return scene;
    }
}



