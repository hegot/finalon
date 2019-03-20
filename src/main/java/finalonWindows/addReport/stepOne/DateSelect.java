package finalonWindows.addReport.stepOne;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Calendar;
import java.util.Date;

public class DateSelect {
    private ObservableMap<String, String> settings;

    public DateSelect(ObservableMap<String, String> settings) {
        this.settings = settings;
    }

    public HBox get() {
        HBox hBox = new HBox(20);
        Label label = new Label("Reporting date");
        hBox.getStyleClass().add("hbox-row");
        label.getStyleClass().add("sub-label");
        hBox.getChildren().addAll(label, dates(), years());
        return hBox;
    }


    private int getCurYear() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return cal.get(Calendar.YEAR);
    }

    private ComboBox dates() {
        ComboBox<String> stepBox = new ComboBox<String>();
        ObservableList<String> years = FXCollections.observableArrayList();
        years.addAll("31.12", "30.11", "31.10", "30.09", "31.08", "30.07", "31.06", "30.05", "31.04", "30.03", "28.02", "31.01");
        stepBox.setItems(years);
        stepBox.getSelectionModel().selectFirst();
        settings.put("date", "31.12");
        stepBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2 != null) {
                    settings.replace("date", arg2);
                }
            }
        });
        return stepBox;
    }


    private ComboBox years() {
        ComboBox<String> stepBox = new ComboBox<String>();
        ObservableList<String> years = FXCollections.observableArrayList();
        int year = getCurYear();
        for (int i = 0; i < 10; i++) {
            int currentYear = year - i;
            years.add(Integer.toString(currentYear));
        }
        settings.put("year", Integer.toString(year));
        stepBox.setItems(years);
        stepBox.getSelectionModel().selectFirst();
        stepBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (arg2 != null) {
                    settings.replace("year", arg2);
                }
            }
        });
        return stepBox;
    }

}

