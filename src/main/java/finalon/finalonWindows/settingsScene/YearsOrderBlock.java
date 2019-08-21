package finalon.finalonWindows.settingsScene;

import finalon.globalReusables.Setting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

class YearsOrderBlock {
    private final String ASCENDING = "Ascending (Example: 2014-2015-1016)";
    private final String DESCENDING = "Descending (Example: 2016-2015-1014)";
    private ObservableMap<Setting, String> settings;
    private ToggleGroup radioGroup;

    YearsOrderBlock(ObservableMap<Setting, String> settings) {
        this.settings = settings;
        this.radioGroup = getRadioGroup();
    }

    public VBox get() {
        VBox vboxrow = new VBox(10);
        vboxrow.getStyleClass().add("vbox-row");
        vboxrow.setPrefWidth(400);
        Label label = new Label("What year-by-year order do you prefer \n (this step will influence 'Add' and 'Edit steps')");
        label.setMinHeight(40);
        label.getStyleClass().add("sub-label");
        String defaultVal = settings.get(Setting.yearOrder);
        RadioButton radioButton = getRadio(ASCENDING);
        RadioButton radioButton2 = getRadio(DESCENDING);
        if (defaultVal.equals("ASCENDING")) {
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
                    if (s.equals(ASCENDING)) {
                        settings.replace(Setting.yearOrder, "ASCENDING");
                    } else {
                        settings.replace(Setting.yearOrder, "DESCENDING");
                    }

                }
            }


        });
        return radioGroup;
    }
}
