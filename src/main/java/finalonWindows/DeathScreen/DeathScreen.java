package finalonWindows.DeathScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DeathScreen extends BorderPane {

    public DeathScreen() {
        getStyleClass().add("main-screen");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/deathScreen/deathScreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}
