package globalReusables;

import javafx.scene.control.Label;

public class LabelWrap {
    public static Label wrap(String str) {
        Label label = new Label(str);
        label.getStyleClass().add("report-text-small");
        label.setWrapText(true);
        return label;
    }
}
