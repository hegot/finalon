package finalon.globalReusables;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class Choicebox {
    public static HBox get(ComboBox choiceBox, String title, String tooltip) {
        HBox hBox = new HBox(20);

        hBox.getStyleClass().add("hbox-row");
        Label label = new Label(title);
        label.getStyleClass().add("sub-label");
        choiceBox.setTooltip(new Tooltip(tooltip));
        hBox.getChildren().addAll(label, choiceBox);
        return hBox;
    }
}
