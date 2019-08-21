package finalon.finalonWindows.formulaScene.IndustryOperations;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;

public interface CancelBtn {
    default Button cancelBtn(Dialog dialog) {
        Button btn = new Button("Cancel");
        btn.getStyleClass().add("popup-btn");
        btn.setOnAction((ActionEvent event) -> {
            dialog.close();
        });
        return btn;
    }
}
