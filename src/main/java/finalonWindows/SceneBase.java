package finalonWindows;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class SceneBase {


    protected CustomMenu topMenu() {
        CustomMenu CustomMenu = new CustomMenu();
        return CustomMenu;
    }


}

class CustomMenu extends HBox {

    public CustomMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu/menu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}