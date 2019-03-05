package finalonWindows.addReport;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

public class ReportStep {
    public static ComboBox<String> get(ObservableMap<String, Object> settings) {
        ComboBox<String> stepBox = new ComboBox<String>();
        ObservableList<String> steps = FXCollections.observableArrayList();
        steps.addAll("month", "quater", "half year", "year");
        stepBox.setItems(steps);
        stepBox.getSelectionModel().selectFirst();
        stepBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2 != null) {
                    settings.replace("step", arg2);
                }
            }
        });
        return stepBox;
    }
}
