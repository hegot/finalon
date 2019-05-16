package interpreter.AssetsReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Map;

public class CurrentNonCurrentAssetsAnalyze extends OutcomeBase {

    private Map.Entry<String, Double> firstCurent;
    private Map.Entry<String, Double> lastCurent;
    private Map.Entry<String, Double> firstNonCurent;
    private Map.Entry<String, Double> lastNonCurent;
    private String startDate;
    private String endDate;

    public CurrentNonCurrentAssetsAnalyze(
            Item current,
            Item nonCurrent,
            String startDate,
            String endDate
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        ObservableMap<String, Double> valuesCurrent = current.getValues();
        ObservableMap<String, Double> valuesNonCurrent = nonCurrent.getValues();
        if (valuesCurrent.size() > 1) {
            this.firstCurent = getFirst(valuesCurrent);
            this.lastCurent = getLast(valuesCurrent);
            if (valuesNonCurrent.size() == 0) {
                this.firstNonCurent = nullEntry(firstCurent.getKey());
                this.lastNonCurent = nullEntry(lastCurent.getKey());
            }
        }
        if (valuesNonCurrent.size() > 1) {
            this.firstNonCurent = getFirst(valuesNonCurrent);
            this.lastNonCurent = getLast(valuesNonCurrent);
            if (valuesCurrent.size() == 0) {
                this.firstCurent = nullEntry(firstNonCurent.getKey());
                this.lastCurent = nullEntry(lastNonCurent.getKey());
            }
        }
    }

    public VBox get() {
        VBox vbox = new VBox(10);
        vbox.setPrefWidth(600);
        String output = "";
        if (firstCurent != null && lastCurent != null && firstNonCurent != null && lastNonCurent != null) {
            Double firstCurentVal = firstCurent.getValue();
            Double lastCurentVal = lastCurent.getValue();
            Double firstNonCurentVal = firstNonCurent.getValue();
            Double lastNonCurentVal = lastNonCurent.getValue();
            Double AssetsOverall = (lastCurentVal - firstCurentVal) + (lastNonCurentVal - firstNonCurentVal);
            if (lastCurentVal > firstCurentVal && lastNonCurentVal > firstNonCurentVal) {
                output = increasedAll();
            }
            if (lastCurentVal < firstCurentVal && lastNonCurentVal < firstNonCurentVal) {
                output = decreaseAll();
            }

            if (AssetsOverall > 0) {
                if (lastCurentVal < firstCurentVal && lastNonCurentVal > firstNonCurentVal) {
                    output = totalIncreaseCurrentDecrease();
                }
                if (lastCurentVal.equals(firstCurentVal) && lastNonCurentVal > firstNonCurentVal) {
                    output = totalIncreaseCurrentStable();
                }
                if (lastCurentVal > firstCurentVal && lastNonCurentVal < firstNonCurentVal) {
                    output = totalIncreaseNonCurrentDecrease();
                }
                if (lastCurentVal > firstCurentVal && lastNonCurentVal.equals(firstNonCurentVal)) {
                    output = totalIncreaseNonCurrentStable();
                }
            } else {
                if (lastCurentVal > firstCurentVal && lastNonCurentVal < firstNonCurentVal) {
                    output = totalDecreaseCurrentIncrease();
                }
                if (lastCurentVal.equals(firstCurentVal) && lastNonCurentVal < firstNonCurentVal) {
                    output = totalDecreaseCurrentStable();
                }
                if (lastCurentVal < firstCurentVal && lastNonCurentVal > firstNonCurentVal) {
                    output = totalDecreaseNonCurrentIncrease();
                }
                if (lastCurentVal < firstCurentVal && lastNonCurentVal.equals(firstNonCurentVal)) {
                    output = totalDecreaseNonCurrentStable();
                }
            }
        }
        Label text1 = new Label(output);
        text1.getStyleClass().add("report-text-small");
        text1.setWrapText(true);
        vbox.getChildren().addAll(text1);
        return vbox;
    }

    private String endString() {
        return " in " + startDate + " comparing to " + endDate + ".";
    }
    private String incStr() {
        return "The overall increase of the assets reflects a growth in the ";
    }
    private String decStr() {
        return "The overall decrease of the assets reflects a reduction in the ";
    }

    private String increasedAll() {
        return "The overall increase of the assets reflects both a growth in the non-current assets by " +
                getRelativeChange(firstNonCurent.getValue(), lastNonCurent.getValue()) +
                "% and a growth in the current assets by " +
                getRelativeChange(firstCurent.getValue(), lastCurent.getValue()) + "%" + endString();
    }

    private String decreaseAll() {
        return "The overall decrease of the assets reflects both a reduction in the non-current assets by " +
                getRelativeChange(firstNonCurent.getValue(), lastNonCurent.getValue()) +
                "% and a reduction in the current assets by" +
                getRelativeChange(firstCurent.getValue(), lastCurent.getValue()) +
                "%" + endString();
    }


    private String totalIncreaseCurrentDecrease() {
        return incStr() + "non-current assets by " +
                getRelativeChange(firstNonCurent.getValue(), lastNonCurent.getValue()) +
                "%, while the value of the current assets dropped by " +
                getRelativeChange(firstCurent.getValue(), lastCurent.getValue()) + "%" + endString();
    }

    private String totalIncreaseCurrentStable() {
        return incStr() + "non-current assets by " +
                getRelativeChange(firstNonCurent.getValue(), lastNonCurent.getValue()) +
                "%, while the value of the current assets not changed";
    }

    private String totalIncreaseNonCurrentDecrease() {
        return incStr() + "current assets by " +
                getRelativeChange(firstCurent.getValue(), lastCurent.getValue()) +
                "% while the value of the non-current assets dropped by " +
                getRelativeChange(firstNonCurent.getValue(), lastNonCurent.getValue()) + "%" + endString();
    }

    private String totalIncreaseNonCurrentStable() {
        return incStr() + "current assets by " +
                getRelativeChange(firstCurent.getValue(), lastCurent.getValue()) +
                "% while the value of the non-current assets not changed";
    }

    private String totalDecreaseCurrentIncrease() {
        return decStr() + "non-current assets by " +
                getRelativeChange(firstNonCurent.getValue(), lastNonCurent.getValue()) +
                "%, while the value of the current assets increased by " +
                getRelativeChange(firstCurent.getValue(), lastCurent.getValue()) + "%" + endString();
    }

    private String totalDecreaseCurrentStable() {
        return decStr() + "non-current assets by " +
                getRelativeChange(firstNonCurent.getValue(), lastNonCurent.getValue()) +
                "%, while the value of the current assets not changed";
    }

    private String totalDecreaseNonCurrentIncrease() {
        return decStr() + "current assets by " +
                getRelativeChange(firstCurent.getValue(), lastCurent.getValue()) +
                "% while the value of the non-current assets increased by " +
                getRelativeChange(firstNonCurent.getValue(), lastNonCurent.getValue()) + "%" + endString();
    }

    private String totalDecreaseNonCurrentStable() {
        return decStr() + "current assets by " +
                getRelativeChange(firstCurent.getValue(), lastCurent.getValue()) +
                "% while the value of the non-current assets was stable";
    }
}
