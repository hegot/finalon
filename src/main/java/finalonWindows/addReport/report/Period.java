package finalonWindows.addReport.report;

import javafx.collections.ObservableMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Period {

    private final int MONTHS = 12;
    private ObservableMap<String, String> settings;
    private int startMonth;
    private int startYear;
    private int endMonth;
    private int endYear;
    private int monthAmount;
    private DateTimeFormatter formatM = DateTimeFormatter.ofPattern("MM");
    private DateTimeFormatter formatY = DateTimeFormatter.ofPattern("yyyy");

    public Period(ObservableMap<String, String> settings) {
        this.settings = settings;

        this.startMonth = parce("start", 1);
        this.startYear = parce("start", 2);
        this.endMonth = parce("end", 1);
        this.endYear = parce("end", 2);
        this.monthAmount = getMonthAmount();
    }

    public int parce(String key, int index) {
        String[] tempArray = settings.get(key).split("/");
        return Integer.parseInt(tempArray[index]);
    }


    private int getMonthAmount() {
        int pre = MONTHS - startMonth;
        int yearsAmount = endYear - startYear;
        if (yearsAmount == 0) {
            return endMonth - startMonth;
        } else if (yearsAmount == 1) {
            return pre + endMonth;
        } else if (yearsAmount > 1) {
            return pre + endMonth + yearsAmount * 12;
        }
        return 0;
    }

    public ArrayList<String> getPeriods() {
        ArrayList<String> periods = new ArrayList<>();
        String step = settings.get("step");
        switch (step) {
            case "month":
                periods = getMonths();
                break;
            case "quater":
                periods = getPeriodArr(3);
                break;
            case "half year":
                periods = getPeriodArr(6);
                break;
            case "year":
                periods = getPeriodArr(12);
                break;
        }
        return periods;
    }

    private ArrayList<String> getPeriodArr(int num) {
        ArrayList<String> periods = new ArrayList<>();
        int chunks = (monthAmount) / num;
        LocalDateTime time = LocalDateTime.of(startYear, startMonth, 1, 0, 0);
        for (int j = 0; j < chunks; j++) {
            String str = time.format(formatM);
            time = time.plusMonths(num);
            str += " - " + time.format(formatM) + "/" + time.format(formatY);
            periods.add(str);
        }

        return periods;
    }


    private ArrayList<String> getMonths() {
        ArrayList<String> periods = new ArrayList<>();
        int mNumber = startMonth;
        for (int j = 0; j < monthAmount; j++) {
            String str = "";
            if (mNumber < MONTHS) {
                str = Integer.toString(mNumber) + "/" + Integer.toString(startYear);
            } else {
                str = Integer.toString(mNumber) + "/" + Integer.toString(startYear);
                startYear = startYear + 1;
                mNumber = 0;
            }
            mNumber++;
            periods.add(str);
        }
        return periods;
    }
}
