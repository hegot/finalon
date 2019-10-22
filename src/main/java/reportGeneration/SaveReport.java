package reportGeneration;

import database.report.DbReportHandler;
import entities.Report;
import javafx.application.Platform;
import reportGeneration.reportJson.ReportJson;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveReport {
    public static void save() {
        Platform.runLater(() -> {

            int reportId = SettingsStorage.getInt("reportId");
            Report report = new Report(
                    reportId,
                    SettingsStorage.get("company"),
                    ReportJson.settingsToJson(),
                    ReportJson.itemsToJson(),
                    getSaveDate(),
                    getYears()
            );
            DbReportHandler.updateReport(report);
        });
    }

    private static String getYears() {
        return formatDate(Periods.startKey()) +
                " - " +
                formatDate(Periods.endKey());
    }

    private static String formatDate(String input) {
        if (input != null) {
            input = input.replace("\n", "");
            String[] parts = input.split("-");
            return (parts[1] != null) ? parts[1] : "";
        }
        return "";
    }

    private static String getSaveDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        return format.format(new Date());
    }
}
