package reportGeneration.stepThree;

import database.report.DbReportHandler;
import entities.Report;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import reportGeneration.reportJson.ReportJson;
import reportGeneration.storage.SettingsStorage;

import java.sql.Timestamp;
import java.util.Optional;

public class SaveReport {
    private static Label confirmation = new Label();

    private static Button saveReportBtn() {
        Button btn = new Button("Save report");
        btn.getStyleClass().add("blue-btn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TextInputDialog dialog = reportNameDialog();
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name -> {
                    saveReport(name);
                });
            }
        });
        return btn;
    }

    private static void saveReport(String reportName) {
        String itemsStringfied = ReportJson.itemsToJson();
        String settingsStringfied = ReportJson.settingsToJson();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int reportId = SettingsStorage.getInt("reportId");
        String name = SettingsStorage.get("company");
        if (reportName != null && reportName.length() > 2) {
            name = reportName;
        }
        Report report = new Report(
                reportId,
                name,
                settingsStringfied,
                itemsStringfied,
                timestamp.toString()
        );
        DbReportHandler.updateReport(report);
        confirmation.setText("Report was successfully saved");
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> confirmation.setText("")));
        timeline.play();
    }


    private static TextInputDialog reportNameDialog() {

        int reportId = SettingsStorage.getInt("reportId");
        Report report = DbReportHandler.getItem(reportId);
        String defaultName = SettingsStorage.get("company");
        if (report.getName().length() > 0) {
            defaultName = report.getName();
        }
        TextInputDialog td = new TextInputDialog(defaultName);
        td.setHeaderText("Please enter report title, it will be used on reports listing to identify report");
        Node saveButton = td.getDialogPane().lookupButton(ButtonType.OK);
        saveButton.setDisable(true);
        TextField textfield = td.getEditor();
        textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().length() < 3);
        });
        return td;
    }

    public static HBox getPane() {
        confirmation.getStyleClass().add("report-text");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(saveReportBtn(), confirmation);
        return hBox;
    }
}
