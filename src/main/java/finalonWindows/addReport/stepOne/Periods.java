package finalonWindows.addReport.stepOne;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

public class Periods {
    public static ComboBox<String> get(ObservableMap<String, String> settings) {
        ComboBox<String> box = new ComboBox<String>();
        ObservableList<String> periods = FXCollections.observableArrayList();
        for (int i = 1; i < 15; i++) {
            periods.add(Integer.toString(i));
        }
        box.setItems(periods);
        settings.put("periods", "1");
        box.getSelectionModel().selectFirst();
        box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2 != null) {
                    settings.replace("periods", arg2);
                }
            }
        });
        return box;
    }
}