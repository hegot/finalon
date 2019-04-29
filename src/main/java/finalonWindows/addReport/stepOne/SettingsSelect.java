package finalonWindows.addReport.stepOne;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

public class SettingsSelect {
    public static ComboBox get(ObservableMap<String, String> settings,
                               ObservableList<String> items,
                               String key,
                               String defaultVal) {
        ComboBox<String> box = new ComboBox<String>();
        box.setItems(items);
        box.getSelectionModel().selectFirst();
        String val = settings.get(key);
        if (val != null) {
            box.setValue(val);
        } else {
            settings.put(key, defaultVal);
            box.getSelectionModel().selectFirst();
        }
        box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2 != null) {
                    settings.replace(key, arg2);
                }
            }
        });
        return box;
    }
}
