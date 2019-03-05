package finalonWindows.addReport;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ReportName {
    public static HBox get(ObservableMap<String, Object> settings) {
        Label label = new Label("Company name: ");
        HBox hBox = new HBox(20);
        hBox.getStyleClass().add("hbox-row");
        label.getStyleClass().add("sub-label");

        TextField company = new TextField();
        company.setMinWidth(300.00);
        company.setPromptText("Company name will be used in report");
        company.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String text = (String) company.getText();
            settings.replace("company", text);
        });

        hBox.getChildren().addAll(label, company);
        return hBox;
    }
}
