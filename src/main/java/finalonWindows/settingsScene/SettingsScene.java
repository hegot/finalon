package finalonWindows.settingsScene;

import database.setting.DbSettingHandler;
import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.reusableComponents.SettingsMenu;
import globalReusables.Setting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(yearsOrderBlock.get(), currencyBlock.get());
        vboxInner.getChildren().addAll(mainLabel, hbox, numberFormat.get(), submitBtn());
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

}
