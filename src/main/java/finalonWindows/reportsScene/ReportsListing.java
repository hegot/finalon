package finalonWindows.reportsScene;

import database.report.DbReportHandler;
import entities.Report;
import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.SettingsMenu;
import javafx.collections.ObservableList;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class ReportsListing extends SceneBase {

    public VBox getScene() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("templates-listing");
        vbox.setPrefSize(900, 600);
        vbox.setPrefHeight(600);
        SettingsMenu settingsMenu = new SettingsMenu();
        vbox.getChildren().addAll(settingsMenu.getMenu(), getTemplates());
        return vbox;
    }

    private VBox getTemplates() {
        ObservableList<Report> items = DbReportHandler.getReports();
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 20px 30px;");
        TilePane tilePane = new TilePane();
        tilePane.setStyle("-fx-padding:10px");
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        if (items.size() > 0) {
            for (Report item : items) {
                tilePane.getChildren().add(new ReportRow(item));
            }
            vbox.getChildren().addAll(
                    tilePane,
                    new AddReportBtn("Add new report: ")
            );
        } else {
            vbox.getChildren().addAll(
                    new AddReportBtn("You have no custom reports yet, you can add one here: ")
            );
        }
        return vbox;
    }

}
