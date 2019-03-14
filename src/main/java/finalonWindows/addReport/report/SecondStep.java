package finalonWindows.addReport.report;

import database.template.DbItemHandler;
import entities.Item;
import finalonWindows.SceneBase;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondStep extends SceneBase {
    private Stage window;
    private ObservableMap<String, String> settings;

    public SecondStep(Stage windowArg, ObservableMap<String, String> settings) {
        this.window = windowArg;
        this.settings = settings;
    }

    public Scene getScene() {
        VBox vbox = new VBox(0);
        DbItemHandler itemsHandler = new DbItemHandler();
        int tpl = Integer.parseInt(settings.get("template"));
        ObservableList<Item> items = itemsHandler.getItems(tpl);
        items.add(itemsHandler.getItem(tpl));
        ReportEditable report = new ReportEditable(items, settings);
        vbox.getChildren().addAll(report.getTemplateEditable());
        Scene scene = baseScene(vbox, 900);
        scene.getStylesheets().add("styles/templateStyle.css");
        return scene;
    }


}
