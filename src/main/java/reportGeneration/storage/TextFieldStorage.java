package reportGeneration.storage;

import finalonWindows.reusableComponents.NumField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class TextFieldStorage {
    private static ObservableMap<String, NumField> fields = FXCollections.observableHashMap();

    public static void add(String code, NumField field) {
        fields.put(code, field);
    }

    public static NumField get(String code) {
        NumField field = fields.get(code);
        return field;
    }

}
