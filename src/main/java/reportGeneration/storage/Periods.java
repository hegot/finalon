package reportGeneration.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Periods {

    private static int periods = getPeriods();
    private static int endYear = getYear();
    private static int endDay = getEndDay();
    private static int endMonth = getEndMonth();
    private static DateTimeFormatter formatM = DateTimeFormatter.ofPattern("MM");
    private static DateTimeFormatter formatY = DateTimeFormatter.ofPattern("yyyy");
    private static int month = getMonths();
    private static ArrayList<String> periodsArr = initPeriodArr();


    private static int getPeriods() {
        String periods = SettingsStorage.get("periods");
        if (periods != null) {
            return Integer.parseInt(periods);
        }
        return 12;
    }

    public static void reInit() {
        periods = getPeriods();
        endYear = getYear();
        endDay = getEndDay();
        endMonth = getEndMonth();
        month = getMonths();
        periodsArr = initPeriodArr();
    }

    private static int getYear() {
        String year = SettingsStorage.get("year");
        if (year != null) {
            return Integer.parseInt(year);
        }
        return 2019;
    }

    private static int getEndDay() {
        String date = SettingsStorage.get("date");
        String[] tempArray = date.split("\\.");
        if (tempArray[0] != null) {
            return Integer.parseInt(tempArray[0]);
        }
        return 31;
    }

    private static int getEndMonth() {
        String date = SettingsStorage.get("date");
        String[] tempArray = date.split("\\.");
        if (tempArray[1] != null) {
            return Integer.parseInt(tempArray[1]);
        }
        return 12;
    }

    private static int getMonths() {
        int amount = 12;
        String step = SettingsStorage.get("reportStep");
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

    public static ArrayList<String> getPeriodArr() {
        return periodsArr;
    }

    public static ArrayList<String> initPeriodArr() {
        ArrayList<String> arr = new ArrayList<>();
        LocalDateTime time = getStartTime();
        for (int j = 0; j < periods; j++) {
            String str = time.format(formatM) + "/" + time.format(formatY) + "-";
            time = time.plusMonths(month);
            str += time.format(formatM) + "/" + time.format(formatY);
            arr.add(str);
        }
        return arr;
    }

    public static String startKey() {
        if (periodsArr.size() > 0) {
            return periodsArr.get(0);
        }
        return "";
    }

    public static String endKey() {
        if (periodsArr.size() > 1) {
            return periodsArr.get(periodsArr.size() - 1);
        }
        return "";
    }

    public static String preEndKey() {
        if (periodsArr.size() > 1) {
            return periodsArr.get(periodsArr.size() - 2);
        }
        return null;
    }

    public static String prePreEndKey() {
        if (periodsArr.size() > 2) {
            return periodsArr.get(periodsArr.size() - 3);
        }
        return "";
    }

    private static LocalDateTime getStartTime() {
        int totall = periods * month;
        LocalDateTime time = getEndTime();
        return time.minusMonths(totall);
    }

    private static LocalDateTime getEndTime() {
        if (endMonth == 0) endMonth = 12;
        if (endDay == 0) endDay = 30;
        if (endYear == 0) endYear = 2019;
        return LocalDateTime.of(endYear, endMonth, endDay, 0, 0);
    }

    public static String getStart() {
        LocalDateTime time = getStartTime().plusMonths(month);
        return time.format(formatM) + "/" + time.format(formatY);
    }

    public static String getAfterStart() {
        if (periodsArr.size() > 1 && periodsArr.get(1) != null) {
            return formatDate(periodsArr.get(1));
        }
        return formatDate(periodsArr.get(0));
    }

    private static String formatDate(String input) {
        if (input != null) {
            input = input.replace("\n", "");
            String[] parts = input.split("-");
            return (parts[1] != null) ? parts[1] : "";
        }
        return "";
    }

    public static String getEnd() {
        LocalDateTime time = getEndTime();
        return time.format(formatM) + "/" + time.format(formatY);
    }

    public static String next(String item) {
        if (periodsArr.size() > 1) {
            int index = periodsArr.indexOf(item) + 1;
            if (index > 0 && periodsArr.size() >= index) {
                return periodsArr.get(index);
            }
        }
        return "";
    }

    public static String prev(String item) {
        if (periodsArr.size() > 1) {
            int index = periodsArr.indexOf(item) - 1;
            if (index > -1) {
                return periodsArr.get(index);
            }
        }
        return "";
    }
}
