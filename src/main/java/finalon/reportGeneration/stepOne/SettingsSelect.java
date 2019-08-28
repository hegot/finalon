package finalon.reportGeneration.stepOne;

import finalon.reportGeneration.storage.Periods;
import finalon.reportGeneration.storage.SettingsStorage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class SettingsSelect {
    public static ComboBox get(ObservableList<String> items,
                               String key,
                               String defaultVal) {
        ComboBox<String> box = new ComboBox<String>();
        box.setItems(items);
        box.getSelectionModel().selectFirst();
        String val = SettingsStorage.get(key);
        if (val != null) {
            box.setValue(val);
        } else {
            SettingsStorage.put(key, defaultVal);
            box.getSelectionModel().selectFirst();
        }
        box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2 != null) {
                    SettingsStorage.replace(key, arg2);
                    Periods.reInit();
                }
            }
        });
        return box;
    }
}
