package finalonWindows.formulaScene.editScreen;

import finalonWindows.SceneBase;
import finalonWindows.SceneName;
import finalonWindows.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class IndustryEdit extends SceneBase {


    public static VBox getScene() {
        return getBox(3);
    }

    public static VBox getScene(int root) {
        return getBox(root);
    }

    private static VBox getBox(int id) {
        VBox vBox = new VBox(10);
        vBox.getStyleClass().add("formula-edit");
        HBox hbox = new HBox(20);
        hbox.getStyleClass().add("whiteBorderedPanel");
        hbox.getChildren().addAll(backButton());
        vBox.getChildren().addAll(
                hbox,
                FormulaEditable.getFormulaTable(id)
        );
        return vBox;
    }


    static Button backButton() {
        Button button = new Button("Back to Industries Listing");
        button.getStyleClass().add("blue-btn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SceneSwitcher.goTo(SceneName.FORMULALIST);
            }
        });
        return button;
    }


}
