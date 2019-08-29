package globalReusables;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;

public class CancelBtn {
    public static Button cancelBtn(Dialog dialog) {
        Button btn = new Button("Cancel");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            dialog.close();
        });
        return btn;
    }
}
