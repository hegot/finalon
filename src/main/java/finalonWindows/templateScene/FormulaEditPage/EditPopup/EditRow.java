package finalonWindows.templateScene.FormulaEditPage.EditPopup;

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

    EditRow(String key, String label, String value) {
        this.key = key;
        this.label = label;
        this.value = value;
        this.textfield = null;
    }
}
