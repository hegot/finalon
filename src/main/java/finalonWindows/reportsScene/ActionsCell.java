package finalonWindows.reportsScene;

import database.report.DbReportHandler;
import entities.Item;
import entities.Report;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import finalonWindows.reusableComponents.ImageButton;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import reportGeneration.reportJson.ReportJson;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.Optional;

public class ActionsCell {
    public static Callback<TableColumn<Report, Void>, TableCell<Report, Void>> getActionsFactory() {
        return new Callback<TableColumn<Report, Void>, TableCell<Report, Void>>() {
            @Override
            public TableCell<Report, Void> call(final TableColumn<Report, Void> param) {
                final TableCell<Report, Void> cell = new TableCell<Report, Void>() {

                    private ImageButton removeBtn(int id) {
                        ImageButton btn = new ImageButton("image/remove.png", 16);
                        btn.getStyleClass().add("img-btn");
                        btn.setOnAction((ActionEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Delete Report");
                            alert.setHeaderText("Are you Sure you want to delete report? This Action can not be undone.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                DbReportHandler.deleteItem(id);
                                SceneSwitcher.goTo(SceneName.REPORTLIST);
                            }
                        });
                        return btn;
                    }


                    private ImageButton editBtn(int id) {
                        ImageButton btn = new ImageButton("image/pencil.png", 16);
                        btn.getStyleClass().add("img-btn");
                        btn.setOnAction((ActionEvent event) -> {
                            Report report = DbReportHandler.getItem(id);
                            ObservableList<Item> items = ReportJson.jsonToItems(report.getItems());
                            ObservableMap<String, String> settings = ReportJson.jsonToSettings(report.getSettings());
                            SettingsStorage.setSettings(settings);
                            SettingsStorage.put("reportId", Integer.toString(id));
                            ItemsStorage.setItems(items);
                            Periods.reInit();
                            SceneSwitcher.goTo(SceneName.ADDREPORT);
                        });
                        return btn;
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(10);
                            TableRow row = this.getTableRow();
                            if (row != null) {
                                Report rowItem = (Report) row.getItem();
                                if (rowItem != null) {
                                    int id = rowItem.getId();
                                    hBox.getChildren().addAll(editBtn(id), removeBtn(id));
                                }
                            }
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };
    }
}
