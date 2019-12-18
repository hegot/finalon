package reportGeneration;

import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.SettingsMenu;
import globalReusables.CallTypes;
import globalReusables.StatTrigger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import reportGeneration.stepOne.StepOne;
import reportGeneration.stepThree.StepThree;
import reportGeneration.stepTwo.StepTwo;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;
import reportGeneration.validator.Validator;

import java.util.ArrayList;

public class AddReportScene extends SceneBase {


    private static HBox saveReportBtn() {
        HBox hBox = new HBox(20);
        Label label = new Label();
        label.getStyleClass().add("confirm");
        Button btn = new Button("Save company data");
        btn.getStyleClass().add("blue-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                label.setText("Company data successfully saved!");
                Timeline timeline = new Timeline(
                        new KeyFrame(
                                Duration.millis(3000),
                                ae -> label.setText("")
                        )
                );
                timeline.play();
                SaveReport.save();
            }
        });
        hBox.getChildren().addAll(btn, label);
        return hBox;
    }

    public static HBox headerMenu() {
        HBox hbox = new HBox(20);
        String step = SettingsStorage.get("step");
        if (step.equals("two")) {
            hbox.getChildren().addAll(backSettingsButton(), generateButton(), saveReportBtn());
        }
        if (step.equals("three")) {
            hbox.getChildren().addAll(backSettingsButton(), backStepTwoButton());
        }
        return hbox;
    }

    public static Button generateButton() {
        Button button = new Button("Generate Report");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                StatTrigger.call(CallTypes.report_generated_times);
                Validator validator = new Validator();
                String errors = validator.validate();
                if (errors.length() > 0) {
                    validator.showValidation(errors);
                } else {
                    populateEmptyValues();
                    SettingsStorage.put("step", "three");
                }
                SaveReport.save();
            }
        });
        return button;
    }

    public static void populateEmptyValues() {
        String perStr = SettingsStorage.get("periods");
        if (perStr != null) {
            Integer periods = Integer.parseInt(perStr);
            ArrayList<String> periodsArr = Periods.getPeriodArr();
            for (Item item : ItemsStorage.getItems()) {
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
    }

    public static Button backSettingsButton() {
        Button button = new Button("Report Settings");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SettingsStorage.put("step", "one");
            }
        });
        return button;
    }

    public static Button backStepTwoButton() {
        Button button = new Button("Back to data input");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SettingsStorage.put("step", "two");
            }
        });
        return button;
    }

    public VBox getScene() {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("add-company");
        SettingsMenu settingsMenu = new SettingsMenu();
        StepOne stepOne = new StepOne();
        settings.put("step", "one");
        settings.addListener(new MapChangeListener() {
            @Override
            public void onChanged(MapChangeListener.Change change) {
                if (change.getKey().equals("step")) {
                    String newStep = (String) change.getValueAdded();
                    vbox.getChildren().clear();
                    if (newStep.equals("one")) {
                        vbox.getChildren().addAll(settingsMenu.getMenu(), stepOne.show());
                    } else if (newStep.equals("two")) {
                        StepTwo stepTwo = new StepTwo();
                        vbox.getChildren().addAll(headerMenu(), stepTwo.show(), headerMenu());
                    } else if (newStep.equals("three")) {
                        StepThree stepThree = new StepThree();
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

}
