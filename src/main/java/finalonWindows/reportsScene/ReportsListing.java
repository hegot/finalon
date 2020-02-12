package finalonWindows.reportsScene;

import database.report.DbReportHandler;
import entities.Report;
import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.SettingsMenu;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


public class ReportsListing extends SceneBase {

    static TableColumn getTextCol(String title, String key) {
        TableColumn<Report, String> col = new TableColumn<Report, String>(title);
        col.setMinWidth(190);
        col.setCellValueFactory(new PropertyValueFactory<Report, String>(key));
        return col;
    }

    static TableColumn buttonCol() {
        TableColumn<Report, Void> col = new TableColumn<>("");
        col.setMinWidth(70);
        col.getStyleClass().add("buttons-cell");
        col.setCellFactory(ActionsCell.getActionsFactory());
        col.setSortable(false);
        return col;
    }

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

        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 20px 30px;");
        vbox.getStyleClass().add("template-screen");
        ObservableList<Report> items = DbReportHandler.getReports();
        if (items.size() > 0) {
            vbox.getChildren().addAll(
                    getTable(items),
                    new AddReportBtn("Add new report: ")
            );
        } else {
            vbox.getChildren().addAll(
                    new AddReportBtn("You have no custom reports yet, you can add one here: ")
            );
        }
        return vbox;
    }

    public TableView<Report> getTable(ObservableList<Report> items) {
        TableView<Report> table = new TableView<>();
        table.setEditable(true);
        table.setMaxHeight(780);
        table.setPrefHeight(780);

        table.getColumns().addAll(
                getTextCol("Company", "name"),
                getTextCol("Industry", "industry"),
                getTextCol("Years", "years"),
                getTextCol("Last time Updated", "updated"),
                buttonCol()
        );
        table.getItems().addAll(items);
        return table;
    }
}
