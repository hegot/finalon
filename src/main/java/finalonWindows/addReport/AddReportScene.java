package finalonWindows.addReport;

import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.addReport.stepOne.StepOne;
import finalonWindows.addReport.stepThree.StepThree;
import finalonWindows.addReport.stepTwo.StepTwo;
import finalonWindows.reusableComponents.SettingsMenu;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddReportScene extends SceneBase {
    private Stage window;
    private ObservableMap<String, String> settings;
    private ObservableList<Item> items;

    public AddReportScene(Stage windowArg) {
        window = windowArg;
        this.settings = FXCollections.observableHashMap();
        this.items = FXCollections.observableArrayList();
    }

    public Scene getScene() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("container");
        SettingsMenu settingsMenu = new SettingsMenu(window);
        StepOne stepOne = new StepOne(settings, vbox);
        StepTwo stepTwo = new StepTwo(settings, items, vbox);
        StepThree stepThree = new StepThree(settings, items);
        settings.put("step", "second");
        settings.addListener(new MapChangeListener() {
            @Override
            public void onChanged(MapChangeListener.Change change) {
                if (change.getKey().equals("step")) {
                    String newStep = (String) change.getValueAdded();
                    vbox.getChildren().clear();
                    if (newStep.equals("one")) {
                        vbox.getChildren().addAll(settingsMenu.getMenu(), stepOne.show());
                    } else if (newStep.equals("two")) {
                        vbox.getChildren().addAll(headerMenu(), stepTwo.show());
                    } else if (newStep.equals("three")) {
                        vbox.getChildren().addAll(headerMenu(), stepThree.show());
                    }
                }
            }
        });

        vbox.getChildren().addAll(settingsMenu.getMenu(), stepOne.show());
        Scene scene = baseScene(vbox, 900);
        scene.getStylesheets().addAll("styles/addReport.css", "styles/templateStyle.css");
        return scene;
    }


    HBox headerMenu() {
        HBox hbox = new HBox(20);
        String step = settings.get("step");
        if (step.equals("two")) {
            hbox.getChildren().addAll(backSettingsButton(), generateButton());
        }
        if (step.equals("three")) {
            hbox.getChildren().addAll(backSettingsButton(), backStepTwoButton());
        }
        return hbox;
    }


    Button generateButton() {
        Button button = new Button("Generate Report");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                settings.put("step", "three");
            }
        });
        return button;
    }

    Button backSettingsButton() {
        Button button = new Button("Report Settings");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                settings.put("step", "one");
            }
        });
        return button;
    }

    Button backStepTwoButton() {
        Button button = new Button("Back to data input");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                settings.put("step", "two");
            }
        });
        return button;
    }

}
