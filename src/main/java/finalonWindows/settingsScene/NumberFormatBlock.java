package finalonWindows.settingsScene;

import defaultData.DefaultNumberFormats;
import globalReusables.Setting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NumberFormatBlock {
    private ObservableMap<Setting, String> settings;

    NumberFormatBlock(ObservableMap<Setting, String> settings) {
        this.settings = settings;
    }

    public VBox get() {
        ObservableList<entities.NumberFormat> Formats = DefaultNumberFormats.getFormats();
        ToggleGroup radioGroup = getRadioGroup(Formats);
        String defaultVal = settings.get(Setting.numberFormat);

        VBox vboxrow = new VBox(10);
        vboxrow.getStyleClass().add("vbox-row");
        Label subLabel = new Label("Number format: ");
        subLabel.getStyleClass().add("sub-label");
        vboxrow.getChildren().addAll(subLabel);

        for (entities.NumberFormat format : Formats) {
            RadioButton radioButton = new RadioButton(format.getName());
            if (format.getShortName().equals(defaultVal)) {
                radioButton.setSelected(true);
            }
            radioButton.setToggleGroup(radioGroup);
            HBox hBox = new HBox(10);
            radioButton.getStyleClass().add("radio-button");
            Label label = new Label(format.getExample());
            hBox.getChildren().addAll(radioButton, label);
            vboxrow.getChildren().add(hBox);
        }
        return vboxrow;
    }

    private ToggleGroup getRadioGroup(ObservableList<entities.NumberFormat> formats) {
        ToggleGroup radioGroup = new ToggleGroup();
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
                RadioButton rb = (RadioButton) radioGroup.getSelectedToggle();
                if (rb != null) {
                    String s = rb.getText();
                    for (entities.NumberFormat format : formats) {
                        if (format.getName().equals(s)) {
                            try {
                                settings.replace(Setting.numberFormat, format.getShortName());
                            } catch (Exception e) {
                                System.out.println(e.toString());
                            }
                        }
                    }

                }
            }
        });
        return radioGroup;
    }
}
