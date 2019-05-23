package reportGeneration;

import javafx.collections.ObservableMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Periods {

    private ObservableMap<String, String> settings;
    private int periods;
    private int endYear;
    private int endDay;
    private int endMonth;
    private DateTimeFormatter formatM = DateTimeFormatter.ofPattern("MM");
    private DateTimeFormatter formatY = DateTimeFormatter.ofPattern("yyyy");
    private int month;

    public Periods() {
        this.settings = SettingsStorage.getSettings();
        this.endYear = Integer.parseInt(settings.get("year"));
        this.periods = Integer.parseInt(settings.get("periods"));
        setMonthDay();
    }

    private void setMonthDay() {
        String date = settings.get("date");
        String[] tempArray = date.split("\\.");
        this.endMonth = Integer.parseInt(tempArray[1]);
        this.endDay = Integer.parseInt(tempArray[0]);
        this.month = getMonths();
    }

    private int getMonths() {
        int amount = 12;
        String step = settings.get("reportStep");
        switch (step) {
            case "month":
                amount = 1;
                break;
            case "quater":
                amount = 3;
                break;
            case "half year":
                amount = 6;
                break;
            case "year":
                amount = 12;
                break;
        }
        return amount;
    }

    public ArrayList<String> getPeriodArr() {
        ArrayList<String> arr = new ArrayList<>();
        LocalDateTime time = getStartTime();
        for (int j = 0; j < periods; j++) {
            String str = time.format(formatM) + "/" + time.format(formatY) + "-";
            time = time.plusMonths(month);
            str += "\n" + time.format(formatM) + "/" + time.format(formatY);
            arr.add(str);
        }
        return arr;
    }

    public LocalDateTime getStartTime() {
        int totall = periods * month;
        LocalDateTime time = getEndTime();
        return time.minusMonths(totall);
    }

    public LocalDateTime getEndTime() {
        Date date = new Date();
        date.setMonth(endMonth);
        date.setYear(endYear);
        date.setDate(endDay);
        return LocalDateTime.of(endYear, endMonth, endDay, 0, 0);
    }

    public String getStart() {
        LocalDateTime time = getStartTime().plusMonths(month);
        ;
        return time.format(formatM) + "/" + time.format(formatY);
    }

    public String getEnd() {
        LocalDateTime time = getEndTime();
        return time.format(formatM) + "/" + time.format(formatY);
    }
}
