package finalonWindows.settingsScene;

import database.setting.DbSettingHandler;
import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.SettingsMenu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Timer;
import java.util.TimerTask;


public class SettingsScene{

    private Stage window;
    private DbSettingHandler dbSettingHandler;
    private ObservableMap<String, String> settings;
    private Timer timer;


    public SettingsScene(Stage windowArg) {
        window = windowArg;
        this.dbSettingHandler = new DbSettingHandler();
        this.settings = FXCollections.observableHashMap();
        settings.put("numberFormat", dbSettingHandler.getSetting("numberFormat"));
        settings.put("yearOrder", dbSettingHandler.getSetting("yearOrder"));
        settings.put("includeAll", dbSettingHandler.getSetting("includeAll"));
        settings.put("defaultCurrency", dbSettingHandler.getSetting("defaultCurrency"));
    }


    public Scene getScene() {

        Scene scene = new Scene(getSettings(), 900, 600);
        scene.getStylesheets().add("styles/generalSettings.css");
        return scene;
    }


    private VBox getSettings() {
        VBox vbox = new VBox(0);
        SettingsMenu settingsMenu = new SettingsMenu(window);
        vbox.getStyleClass().add("number-format-container");

        VBox vboxInner = new VBox(10);
        vboxInner.getStyleClass().add("inner-container");
        Label mainLabel = new Label("General Application Settings");
        mainLabel.getStyleClass().add("settings-label");
        NumberFormatBlock numberFormat = new NumberFormatBlock(settings);
        CurrencyBlock currencyBlock = new CurrencyBlock(settings);
        YearsOrderBlock yearsOrderBlock = new YearsOrderBlock(settings);
        ShowAllBlock showAllBlock = new ShowAllBlock(settings);
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(yearsOrderBlock.get(), currencyBlock.get());
        vboxInner.getChildren().addAll(mainLabel, hbox, numberFormat.get(), showAllBlock.get(), submitBtn());
        vbox.getChildren().addAll(settingsMenu.getMenu(), vboxInner);
        return vbox;
    }


    private HBox submitBtn() {
        HBox hbox = new HBox(20);
        Label label = new Label("");
        label.getStyleClass().add("confirm-label");
        Button btn = new Button("Save Changes");
        btn.getStyleClass().add("blue-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    for (String key : settings.keySet()) {
                        dbSettingHandler.updateSetting(key, settings.get(key));
                    }
                    label.setText("Your changes have been saved!");
                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(2500),
                            ae -> label.setText("")));
                    timeline.play();


                } catch (Exception exception) {
                    System.out.println("Error while saving settings");
                }
            }
        });
        hbox.getChildren().addAll(btn, label);
        return hbox;
    }

}
