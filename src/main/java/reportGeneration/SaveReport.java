package reportGeneration;

import database.report.DbReportHandler;
import entities.Report;
import javafx.application.Platform;
import reportGeneration.reportJson.ReportJson;
import reportGeneration.storage.SettingsStorage;

import java.sql.Timestamp;

public class SaveReport {
    public static void save() {
        Platform.runLater(() -> {
            int reportId = SettingsStorage.getInt("reportId");
            Report report = new Report(
                    reportId,
                    SettingsStorage.get("company"),
                    ReportJson.settingsToJson(),
                    ReportJson.itemsToJson(),
                    new Timestamp(System.currentTimeMillis()).toString()
            );
            DbReportHandler.updateReport(report);
        });
    }
}
