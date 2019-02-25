package finalonWindows.templateScene;

import database.template.TemplateEditor;
import entities.Item;
import finalonWindows.templateScene.templates.TemplateEditable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditTemplate extends TemplateBase {

    protected Stage window;
    protected ObservableList<Item> items;
    private TemplateEditable templateEditable;

    public EditTemplate(Stage windowArg, ObservableList<Item> items) {
        super(windowArg, items);
        this.items = items;
        this.templateEditable = new TemplateEditable(items);
        this.window = windowArg;
    }

    private HBox headerMenu() {
        HBox hbox = new HBox(10);
        hbox.getStyleClass().add("whiteBorderedPanel");
        Button saveTemplateButton = saveTemplateButton();
        saveTemplateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((templateName.getText() != null && !templateName.getText().isEmpty())) {
                    Item rootItem = getRoot();
                    rootItem.setName(templateName.getText());
                    ObservableList<Item> items = templateEditable.getItems();
                    items.add(rootItem);
                    TemplateEditor updater = new TemplateEditor(templateName.getText(), items);
                    updater.updateTpl();
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
        scene.getStylesheets().add("styles/templateStyle.css");
        return scene;
    }

}



