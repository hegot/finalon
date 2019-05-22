package finalonWindows.addReport;

import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.addReport.stepOne.StepOne;
import finalonWindows.addReport.stepThree.StepThree;
import finalonWindows.addReport.stepTwo.StepTwo;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import finalonWindows.reusableComponents.SettingsMenu;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class AddReportScene extends SceneBase {
    private ObservableMap<String, String> settings;
    private ObservableList<Item> items;

    public AddReportScene() {
        this.settings = FXCollections.observableHashMap();
        this.items = FXCollections.observableArrayList();
    }

    public VBox getScene() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("add-company");
        SettingsMenu settingsMenu = new SettingsMenu();
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

        vbox.getChildren().addAll(
                settingsMenu.getMenu(),
                stepOne.show()
        );
        return vbox;
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
                populateEmptyValues();
                settings.put("step", "three");
            }
        });
        return button;
    }

    private void populateEmptyValues() {
        int periods = Integer.parseInt(settings.get("periods"));
        Periods Periods = new Periods(settings);
        ArrayList<String> periodsArr = Periods.getPeriodArr();
        for (Item item : items) {
            if (item.getValues().size() > 0) {
                ObservableMap<String, Double> values = item.getValues();
                if (values.size() != periods) {
                    for (String period : periodsArr) {
                        values.putIfAbsent(period, 0.0);
                    }
                }
            }
        }
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
