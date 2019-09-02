package reportGeneration.stepOne;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

public class SettingsSelect {
    public static ComboBox get(ObservableList<String> items,
                               String key,
                               String defaultVal) {
        ComboBox<String> box = new ComboBox<String>();
        box.setItems(items);
        if(defaultVal.length() > 0){
            box.getSelectionModel().select(defaultVal);
        }else{
            box.getSelectionModel().selectFirst();
        }
        String val = SettingsStorage.get(key);
        if (val != null) {
            box.setValue(val);
        } else {
            SettingsStorage.put(key, defaultVal);
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
