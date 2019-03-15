package finalonWindows.addReport.report;

import database.template.DbItemHandler;
import defaultData.DefaultTemplate;
import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.addReport.outcome.Outcome;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondStep extends SceneBase {
    private Stage window;
    private ObservableMap<String, String> settings;
    private ReportEditable report;

    public SecondStep(Stage windowArg, ObservableMap<String, String> settings) {
        this.window = windowArg;
        this.settings = settings;
    }

    public Scene getScene() {
        VBox vbox = new VBox(10);
        DbItemHandler itemsHandler = new DbItemHandler();
        int tpl = Integer.parseInt(settings.get("template"));
        ObservableList<Item> items = itemsHandler.getItems(tpl);
        if(items.size() == 0){
            items = FXCollections.observableArrayList(DefaultTemplate.getTpl());
        }else{
            items.add(itemsHandler.getItem(tpl));
        }
        this.report = new ReportEditable(items, settings);
        vbox.getChildren().addAll(headerMenu(), report.getTemplateEditable());
        Scene scene = baseScene(vbox, 900);
        scene.getStylesheets().add("styles/templateStyle.css");
        return scene;
    }

    HBox headerMenu(){
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(backButton(), generateButton());
        return hBox;
    }


    Button backButton() {
        Button button = new Button("Back to Report Settings");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                window.setScene(SceneSwitcher.getScenes(SceneName.BARE).get(SceneName.ADDREPORT));
                window.setWidth(900);
            }
        });
        return button;
    }

    Button generateButton() {
        Button button = new Button("Generate Report");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ObservableList<Item> items = report.getItems();
                Outcome outcome = new Outcome(window, settings, items);

                window.setScene(outcome.getScene());
            }
        });
        return button;
    }
}

