package reportGeneration.stepOne;

import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import reportGeneration.storage.SettingsStorage;

public class ReportName {
    public static HBox get() {
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
        Label label = new Label("Company name: ");
        HBox hBox = new HBox(20);
        hBox.getStyleClass().add("hbox-row");
        label.getStyleClass().add("sub-label");

        TextField company = new TextField();
        company.setMinWidth(300.00);
        company.setPromptText("Company name will be used in report");
        String companyVal = settings.get("company");
        if (companyVal != null) {
            company.setText(companyVal);
        } else {
            settings.put("company", "");
        }
        company.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) company.getText();
            settings.replace("company", text);
        });

        hBox.getChildren().addAll(label, company);
        return hBox;
    }
}
