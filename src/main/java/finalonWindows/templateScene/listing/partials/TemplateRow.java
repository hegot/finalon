package finalonWindows.templateScene.listing.partials;

import database.template.TemplateCreator;
import entities.Item;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import database.template.TemplateDeleter;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class TemplateRow extends VBox {
    @FXML
    private Item item;

    public TemplateRow(Item item) {
        this.item = item;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listing/templateRow.fxml"));
        fxmlLoader.getNamespace().put("labelText", item.getName());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void editTemplateAction() {
        SceneSwitcher.goTo(SceneName.ADDTEMPLATE, item.getId());
    }

    @FXML
    protected void editFormulaAction() {
        SceneSwitcher.goTo(SceneName.EDITFORMULAS, item.getId());
    }

    @FXML
    protected void deleteRowAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Template");
        alert.setHeaderText("Are you Sure you want to delete template? This Action can not be undone.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            runTask();
            SceneSwitcher.runPreloader(12000, null);
        }
    }

    private void runTask() {
        Task listLoader = new Task<Integer>() {
            {
                setOnSucceeded(workerStateEvent -> {
                    SceneSwitcher.goTo(SceneName.TEMPLATESLIST);
                });
                setOnFailed(workerStateEvent -> getException().printStackTrace());
            }

            @Override
            protected Integer call() throws Exception {
                TemplateDeleter.deleteItems(item.getId());
                return null;
            }
        };
        Thread loadingThread = new Thread(listLoader, "list-loader3");
        loadingThread.setDaemon(true);
        loadingThread.start();
    }
}