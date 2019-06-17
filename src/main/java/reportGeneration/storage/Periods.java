package reportGeneration.storage;

import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Periods implements JsCalcHelper {

    private ObservableMap<String, String> settings;
    private int periods;
    private int endYear;
    private int endDay;
    private int endMonth;
    private DateTimeFormatter formatM = DateTimeFormatter.ofPattern("MM");
    private DateTimeFormatter formatY = DateTimeFormatter.ofPattern("yyyy");
    private int month;
    private boolean initalized = false;
    private ArrayList<String> periodArr;

    public Periods() {
        if (!initalized) {
            try {
                init();
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Could not init Periods");
            }
        }
        this.periodArr = new ArrayList<>();
    }

    public static Periods getInstance() {
        return Periods.SingletonHolder.INSTANCE;
    }

    private void init() {
        this.settings = SettingsStorage.getInstance().getSettings();
        String year = settings.get("year");
        String periods = settings.get("periods");
        if(year != null && periods != null){
            this.endYear = Integer.parseInt(year);
            this.periods = Integer.parseInt(periods);
            setMonthDay();
            initalized = true;
        }
    }

    public void reInit() {
        initalized = false;
        new SingletonHolder().reInit();
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
        if (periodArr.size() > 0) {
            return periodArr;
        } else {
            ArrayList<String> arr = new ArrayList<>();
            LocalDateTime time = getStartTime();
            for (int j = 0; j < periods; j++) {
                String str = time.format(formatM) + "/" + time.format(formatY) + "-";
                time = time.plusMonths(month);
                str += "\n" + time.format(formatM) + "/" + time.format(formatY);
                arr.add(str);
            }
            this.periodArr = arr;
            return arr;
        }
    }

    private LocalDateTime getStartTime() {
        int totall = periods * month;
        LocalDateTime time = getEndTime();
        return time.minusMonths(totall);
    }

    private LocalDateTime getEndTime() {
        if (endMonth == 0) endMonth = 1;
        if (endDay == 0) endDay = 1;
        if (endYear == 0) endYear = 2000;
        return LocalDateTime.of(endYear, endMonth, endDay, 0, 0);
    }

    public String getStart() {
        LocalDateTime time = getStartTime().plusMonths(month);
        return time.format(formatM) + "/" + time.format(formatY);
    }

    public String getAfterStart() {
        ArrayList<String> arr = getPeriodArr();
        if (arr.get(1) != null) {
            return formatDate(arr.get(1));
        }
        return formatDate(arr.get(0));
    }

    public String getEnd() {
        LocalDateTime time = getEndTime();
        return time.format(formatM) + "/" + time.format(formatY);
    }

    private static class SingletonHolder {
        static Periods INSTANCE = new Periods();

        void reInit() {
            INSTANCE = new Periods();
        }
    }
}
