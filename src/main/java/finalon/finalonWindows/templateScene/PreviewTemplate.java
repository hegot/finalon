package finalon.finalonWindows.templateScene;

import finalon.entities.Item;
import finalon.finalonWindows.templateScene.templates.TemplatePreview;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PreviewTemplate extends TemplateBase {

    protected ObservableList<Item> items;
    private TemplatePreview templatePreview;

    public PreviewTemplate(ObservableList<Item> items) {
        super(items);
        this.items = items;
        this.templatePreview = new TemplatePreview(items);
    }

    private HBox headerMenu() {
        HBox hbox = new HBox(10);
        hbox.getStyleClass().add("whiteBorderedPanel");
        hbox.getChildren().addAll(backButton());
        return hbox;
    }


    public VBox getScene() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("template-screen");
        vBox.getChildren().addAll(
                headerMenu(),
                templatePreview.getTemplatePreview()
        );
        return vBox;
    }

}



