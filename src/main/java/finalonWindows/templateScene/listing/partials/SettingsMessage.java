package finalonWindows.templateScene.listing.partials;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SettingsMessage extends VBox {

    public SettingsMessage() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listing/templatesMessage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}