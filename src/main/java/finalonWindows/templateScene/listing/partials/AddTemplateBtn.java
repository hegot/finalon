package finalonWindows.templateScene.listing.partials;
import database.template.TemplateCreator;
import javafx.concurrent.Task;
import entities.Formula;
import entities.FormulaConverter;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class AddTemplateBtn extends HBox {

    private int tplId;

    public AddTemplateBtn(String text) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listing/addTplBtn.fxml"));
        fxmlLoader.getNamespace().put("labelText", text);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static ComboBox<Formula> industry() {
        ComboBox<Formula> industryBox = new ComboBox<Formula>();
        industryBox.setPrefWidth(200);
        industryBox.setConverter(
                new FormulaConverter()
        );
        return industryBox;
    }

    @FXML
    protected void addTemplateAction() {
        runTask();
        SceneSwitcher.runPreloader(12000, null);
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
                TemplateCreator saver = new TemplateCreator();
                saver.saveTpl();
                return null;
            }
        };
        Thread loadingThread = new Thread(listLoader, "list-loader2");
        loadingThread.setDaemon(true);
        loadingThread.start();
    }
}