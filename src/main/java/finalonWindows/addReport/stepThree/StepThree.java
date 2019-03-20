package finalonWindows.addReport.stepThree;

import entities.Item;
import finalonWindows.SceneBase;
import interpreter.Interprter;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StepThree extends SceneBase {
    private ObservableMap<String, String> settings;
    private ObservableList<Item> items;

    public StepThree(ObservableMap<String, String> settings, ObservableList<Item> items) {
        this.settings = settings;
        this.items = items;
    }

    public VBox show() {

        String res = evaluateFormula();
        Label label = new Label(res);
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(label);

        return vbox;
    }


    private String evaluateFormula() {
        Interprter interprter = new Interprter(settings, items);
        return interprter.result();
    }


}
