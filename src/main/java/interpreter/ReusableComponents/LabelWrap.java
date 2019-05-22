package interpreter.ReusableComponents;

import javafx.scene.control.Label;

public interface LabelWrap {
    default Label labelWrap(String str) {
        Label label = new Label(str);
        label.getStyleClass().add("report-text-small");
        label.setWrapText(true);
        return label;
    }
}
