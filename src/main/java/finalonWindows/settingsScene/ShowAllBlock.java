package finalonWindows.settingsScene;

import globalReusables.Setting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ShowAllBlock {
    private ObservableMap<Setting, String> settings;
    private ToggleGroup radioGroup;

    ShowAllBlock(ObservableMap<Setting, String> settings) {
        this.settings = settings;
        this.radioGroup = getRadioGroup();
    }

    public VBox get() {
        VBox vboxrow = new VBox(10);
        vboxrow.getStyleClass().add("vbox-row");
        Label label = new Label("Do you want to include all the elements of balance sheet to horizontal and vertical analysis tables? \n (this option influences generated Report) ");
        label.setMinHeight(40);
        label.getStyleClass().add("sub-label");
        String defaultVal = settings.get(Setting.includeAll);
        RadioButton radioButton = getRadio("Yes");
        RadioButton radioButton2 = getRadio("No, only the main elements (current assets, non-current assets, equity, current liabilities, non-current liabilities)");
        if (defaultVal.equals("YES")) {
            radioButton.setSelected(true);
        } else {
            radioButton2.setSelected(true);
        }
        vboxrow.getChildren().addAll(label, radioButton, radioButton2);
        return vboxrow;
    }

    private RadioButton getRadio(String title) {
        RadioButton radioButton = new RadioButton(title);
        radioButton.setToggleGroup(radioGroup);
        radioButton.getStyleClass().add("radio-button");
        return radioButton;
    }

    private ToggleGroup getRadioGroup() {
        ToggleGroup radioGroup = new ToggleGroup();
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
                RadioButton rb = (RadioButton) radioGroup.getSelectedToggle();
                if (rb != null) {
                    String s = rb.getText();
                    String val = (s.equals("Yes")) ? "YES" : "NO";
                    settings.replace(Setting.includeAll, val);
                    String ws = rb.getText();
                }
            }
        });
        return radioGroup;
    }
}
