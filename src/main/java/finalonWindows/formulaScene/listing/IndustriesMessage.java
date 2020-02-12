package finalonWindows.formulaScene.listing;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class IndustriesMessage extends VBox {

    public IndustriesMessage() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listing/industriesMessage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}