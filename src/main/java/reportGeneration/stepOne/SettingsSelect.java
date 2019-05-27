package reportGeneration.stepOne;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

public class SettingsSelect {
    public static ComboBox get(ObservableList<String> items,
                               String key,
                               String defaultVal) {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
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
                    new Periods().reInit();
                }
            }
        });
        return box;
    }
}
