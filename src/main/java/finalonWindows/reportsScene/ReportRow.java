package finalonWindows.reportsScene;

import database.report.DbReportHandler;
import entities.Item;
import entities.Report;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import reportGeneration.reportJson.ReportJson;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.SettingsStorage;

import java.io.IOException;
import java.util.Optional;

public class ReportRow extends VBox {
    @FXML
    private Report item;

    public ReportRow(Report item) {
        this.item = item;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings/templateRow.fxml"));
        fxmlLoader.getNamespace().put("labelText", item.getName());
        fxmlLoader.getNamespace().put("describtionText", "Industry: " + item.getIndustryName());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void editRowAction() {
        Report report = DbReportHandler.getItem(item.getId());
        ObservableList<Item> items = ReportJson.jsonToItems(report.getItems());
        ObservableMap<String, String> settings = ReportJson.jsonToSettings(report.getSettings());
        SettingsStorage.setSettings(settings);
        SettingsStorage.put("reportId", Integer.toString(item.getId()));
        ItemsStorage.setItems(items);
        SceneSwitcher.goTo(SceneName.ADDREPORT);
    }

    @FXML
    protected void deleteRowAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Report");
        alert.setHeaderText("Are you Sure you want to delete report? This Action can not be undone.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            DbReportHandler.deleteItem(item.getId());
            SceneSwitcher.goTo(SceneName.REPORTLIST);
        }
    }


}