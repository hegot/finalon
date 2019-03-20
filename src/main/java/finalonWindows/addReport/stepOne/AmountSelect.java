package finalonWindows.addReport.stepOne;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;


public class AmountSelect {
    public static ComboBox get(ObservableMap<String, String> settings) {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("thousand", "million");
        ComboBox<String> templatesBox = new ComboBox<String>();
        templatesBox.getSelectionModel().selectFirst();
        settings.put("amount", "thousand");
        templatesBox.setItems(items);
        templatesBox.getSelectionModel().selectFirst();
        templatesBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2 != null) {
                    settings.replace("amount", arg2);
                }
            }
        });
        return templatesBox;
    }
}
