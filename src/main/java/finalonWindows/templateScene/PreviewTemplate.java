package finalonWindows.templateScene;

import entities.Item;
import finalonWindows.templateScene.templates.TemplatePreview;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PreviewTemplate extends TemplateBase {

    protected Stage window;
    protected ObservableList<Item> items;
    private TemplatePreview templatePreview;

    public PreviewTemplate(Stage windowArg, ObservableList<Item> items) {
        super(windowArg, items);
        this.items = items;
        this.templatePreview = new TemplatePreview(items);
    }

    private HBox headerMenu() {
        HBox hbox = new HBox(10);
        hbox.getStyleClass().add("whiteBorderedPanel");
        hbox.getChildren().addAll(backButton());
        return hbox;
    }


    public Scene getScene() {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(headerMenu(), templatePreview.getTemplatePreview());
        Scene scene = baseScene(vBox, 900);
        scene.getStylesheets().add("styles/templateStyle.css");
        return scene;
    }

}



