package finalonWindows.formulaScene.EditPopup;

import javafx.scene.control.TextField;

public class EditRow {
    String key;
    String label;
    String value;
    TextField textfield;

    EditRow(String key, String label, String value, TextField textfield) {
        this.key = key;
        this.label = label;
        this.value = value;
        this.textfield = textfield;
    }
}
