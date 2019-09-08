package finalonWindows.reportsScene;

import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import globalReusables.AddNewReport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.SettingsStorage;

import java.io.IOException;

public class AddReportBtn extends HBox {

    public AddReportBtn(String text) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings/addTplBtn.fxml"));
        fxmlLoader.getNamespace().put("labelText", text);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void addTemplateAction() {
        AddNewReport.run();
    }
}