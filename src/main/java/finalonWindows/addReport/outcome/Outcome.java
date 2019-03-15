package finalonWindows.addReport.outcome;

import database.template.DbItemHandler;
import defaultData.DefaultTemplate;
import entities.Item;
import finalonWindows.SceneBase;
import interpreter.Interprter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Outcome extends SceneBase {
    private Stage window;
    private ObservableMap<String, String> settings;
    private ObservableList<Item> items;

    public Outcome(Stage windowArg, ObservableMap<String, String> settings, ObservableList<Item> items) {
        this.window = windowArg;
        this.settings = settings;
        this.items = items;
    }

    public Scene getScene() {
        VBox vbox = new VBox(10);
        DbItemHandler itemsHandler = new DbItemHandler();
        int tpl = Integer.parseInt(settings.get("template"));
        ObservableList<Item> items = itemsHandler.getItems(tpl);
        if (items.size() == 0) {
            items = FXCollections.observableArrayList(DefaultTemplate.getTpl());
        } else {
            items.add(itemsHandler.getItem(tpl));
        }
        Label label = new Label("asd");
        vbox.getChildren().add(label);
        Scene scene = baseScene(vbox, 900);
        scene.getStylesheets().add("styles/templateStyle.css");
            String res = evaluateFormula();
        return scene;
    }


    private String  evaluateFormula() {
        Interprter interprter = new Interprter(settings, items);
        return interprter.result();
    }

}
