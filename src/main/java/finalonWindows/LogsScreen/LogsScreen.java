package finalonWindows.LogsScreen;

import database.log.DbLogHandler;
import entities.Log;
import finalonWindows.reusableComponents.SettingsMenu;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class LogsScreen {

    public VBox getScene() {
        VBox vbox = new VBox(0);
        vbox.getStyleClass().add("settings-container");
        VBox vboxInner = new VBox(10);
        vboxInner.getStyleClass().add("inner-container");
        Label mainLabel = new Label("General Application Settings");
        mainLabel.getStyleClass().add("settings-label");
        vbox.getChildren().addAll(new SettingsMenu().getMenu(), getTable());
        return vbox;
    }

    private TableView getTable(){
        TableView<Log> table = new TableView<Log>();
        TableColumn<Log, String> dateCol = new TableColumn<Log, String>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<Log, String>("date"));
        TableColumn<Log, String> messageCol = new TableColumn<Log, String>("Message");
        messageCol.setCellValueFactory(new PropertyValueFactory<Log, String>("message"));
        table.getColumns().addAll(dateCol, messageCol);
        DbLogHandler dbLogHandler = new DbLogHandler();
        ObservableList<Log> logs = dbLogHandler.getLogs();
        table.getItems().addAll(logs);
        return table;
    }
}
