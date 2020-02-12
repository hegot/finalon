package finalonWindows.settingsScene;

import database.setting.DbSettingHandler;
import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.reusableComponents.SettingsMenu;
import globalReusables.Setting;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Optional;

public class SettingsScene extends SceneBase {

    private ObservableMap<Setting, String> settings;

    public SettingsScene() {
        this.settings = FXCollections.observableHashMap();
        settings.put(Setting.numberFormat, DbSettingHandler.getSetting(Setting.numberFormat));
        settings.put(Setting.yearOrder, DbSettingHandler.getSetting(Setting.yearOrder));
        settings.put(Setting.includeAll, DbSettingHandler.getSetting(Setting.includeAll));
        settings.put(Setting.defaultCurrency, DbSettingHandler.getSetting(Setting.defaultCurrency));
    }

    public VBox getScene() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("settings-container");
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
        vboxInner.getChildren().addAll(mainLabel, hbox, numberFormat.get(), showAllBlock.get(), submitBtn(), resetFormulaBtn());
        vbox.getChildren().addAll(new SettingsMenu().getMenu(), vboxInner);
        return vbox;
    }

    private HBox submitBtn() {
        HBox hbox = new HBox(20);
        Button btn = new Button("Save Changes");
        btn.getStyleClass().add("blue-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    for (Setting key : settings.keySet()) {
                        DbSettingHandler.updateSetting(key, settings.get(key));
                    }
                    SceneSwitcher.goTo(SceneName.MAIN);
                } catch (Exception exception) {
                    System.out.println("Error while saving settings");
                }
            }
        });
        hbox.getChildren().addAll(btn);
        return hbox;
    }

    private VBox resetFormulaBtn() {
        VBox vBox = new VBox(20);
        vBox.getStyleClass().add("vbox-row");
        Label label = new Label("You can reset Formulas to default state - before you made changes (all added industries and changes will be dropped!)");
        label.getStyleClass().add("sub-label");
        label.setWrapText(true);
        label.getStyleClass().add("confirm-label");
        Button btn = new Button("Reset Formulas");
        btn.getStyleClass().add("blue-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    resetFormulasAlert(label);
                } catch (Exception exception) {
                    System.out.println("Error while saving settings");
                }
            }
        });
        vBox.getChildren().addAll(label, btn);
        return vBox;
    }

    private void resetFormulasAlert(Label label) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reset Formulas");
        alert.setHeaderText("Are you sure you want " +
                "to reset formulas to default state?");
        alert.setContentText("This action will delete all formula " +
                "customizations and can not be undone.");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            new ResetFormulas().reset();
            label.setText("Formulas were reseted to default state");
            Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis(5000),
                            ae -> label.setText("")
                    )
            );
            timeline.play();
        }
    }
}
