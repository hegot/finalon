package finalonWindows.settingsScene;

import entities.Formula;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class StandardRow extends VBox {
    @FXML
    private Formula formula;
    private Stage window;

    StandardRow(Stage window, Formula formula) {
        this.window = window;
        this.formula = formula;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/settings/templateRow.fxml"));
        fxmlLoader.getNamespace().put("labelText", formula.getName());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private double height() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        return primaryScreenBounds.getHeight();
    }


    @FXML
    protected void viewRowAction() {

    }

    @FXML
    protected void editRowAction() {

    }

    @FXML
    protected void deleteRowAction() {

    }

}