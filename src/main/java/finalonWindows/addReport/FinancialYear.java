package finalonWindows.addReport;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

public class FinancialYear {
    public static ComboBox<String> get(ObservableMap<String, String> settings) {
        ComboBox<String> box = new ComboBox<String>();
        ObservableList<String> steps = FXCollections.observableArrayList();
        steps.addAll(
                "1st of January",
                "1st of February",
                "1st of March",
                "1st of April",
                "1st of May",
                "1st of June",
                "1st of July",
                "1st of August",
                "1st of September",
                "1st of October",
                "1st of November",
                "1st of December"

        );
        box.setItems(steps);
        box.getSelectionModel().selectFirst();
        box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2 != null) {
                    settings.replace("finYear", arg2);
                }
            }
        });
        return box;
    }
}