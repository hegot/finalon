package finalonWindows.addReport;

import finalonWindows.reusableComponents.calendar.FXCalendar;
import finalonWindows.reusableComponents.calendar.FXCalendarUtility;
import javafx.collections.ObservableMap;

import java.util.Calendar;
import java.util.Date;

public class Callendar {

    private String type;
    private FXCalendar callendar;
    private ObservableMap<String, String> settings;
    private FXCalendarUtility fxCalendarUtility = new FXCalendarUtility();

    public Callendar(String type, ObservableMap<String, String> settings) {
        this.type = type;
        this.settings = settings;
        this.callendar = new FXCalendar();
    }

    public FXCalendar get() {
        Calendar c = Calendar.getInstance();
        if (type.equals("start")) {
            c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        }
        Date date = c.getTime();
        settings.put(type, fxCalendarUtility.convertDatetoString(date));
        callendar.setValue(date);

        // settings.put(type, date);
        callendar.selectedYearProperty().addListener(e -> {
            settings.replace(type, getDate(callendar));
        });
        callendar.selectedMonthProperty().addListener(e -> {
            settings.replace(type, getDate(callendar));
        });

        return callendar;
    }

    private String getDate(FXCalendar calendar) {
        String output = "";
        int date = calendar.selectedDateProperty().get();
        int month = calendar.selectedMonthProperty().get();
        int year = calendar.selectedYearProperty().get();
        if (date != 0 && month != -1 && year != 0) {
            output = fxCalendarUtility.getFormattedDate(date, month, year);
        }
        return output;
    }
}

