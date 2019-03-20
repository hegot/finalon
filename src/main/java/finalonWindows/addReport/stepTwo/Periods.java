package finalonWindows.addReport.stepTwo;

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

    public Periods(ObservableMap<String, String> settings) {
        this.settings = settings;
        this.endYear = Integer.parseInt(settings.get("year"));
        this.periods = Integer.parseInt(settings.get("periods"));
        setMonthDay();
    }

    private void setMonthDay() {
        String date = "31.12";
        String[] tempArray = date.split("\\.");
        this.endMonth = Integer.parseInt(tempArray[1]);
        this.endDay = Integer.parseInt(tempArray[0]);
    }


    public int getMonths() {
        int amount = 1;
        String step = settings.get("step");
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
        int month = getMonths();
        Date date = new Date();
        date.setMonth(endMonth);
        date.setYear(endYear);
        date.setDate(endDay);

        LocalDateTime time = LocalDateTime.of(endYear, endMonth, endDay, 0, 0);
        for (int j = 0; j < periods; j++) {
            String str = time.format(formatM) + "/" + time.format(formatY) + "-";
            time = time.minusMonths(month);
            str += "\n" + time.format(formatM) + "/" + time.format(formatY);
            arr.add(str);
        }

        return arr;
    }

}
