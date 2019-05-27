package reportGeneration.stepOne;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import reportGeneration.storage.SettingsStorage;

import java.util.Calendar;
import java.util.Date;

public class DateSelect {
    private ObservableMap<String, String> settings;

    DateSelect() {
        this.settings = SettingsStorage.getSettings();
    }

    public HBox get() {
        HBox hBox = new HBox(20);
        Label label = new Label("Reporting date");
        hBox.getStyleClass().add("hbox-row");
        label.getStyleClass().add("sub-label");
        hBox.getChildren().addAll(label, dates(), years());
        return hBox;
    }


    private ComboBox dates() {
        ObservableList<String> dates = FXCollections.observableArrayList();
        dates.addAll("31.12", "30.11", "31.10", "30.09", "31.08", "30.07", "31.06", "30.05", "31.04", "30.03", "28.02", "31.01");
        return SettingsSelect.get(dates, "date", "31.12");
    }


    private ComboBox years() {
        ObservableList<String> years = FXCollections.observableArrayList();
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int year = cal.get(Calendar.YEAR);
        for (int i = 0; i < 10; i++) {
            int currentYear = year - i;
            years.add(Integer.toString(currentYear));
        }
        return SettingsSelect.get(years, "year", Integer.toString(year));
    }

}

