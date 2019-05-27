package reportGeneration.interpreter.AssetsReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.OutcomeBase;
import reportGeneration.storage.IndexesStorage;
import reportGeneration.storage.Periods;

import java.util.Map;

public class CurrentNonCurrentAssetsAnalyze extends OutcomeBase {

    private Double firstCurentVal;
    private Double lastCurentVal;
    private Double firstNonCurentVal;
    private Double lastNonCurentVal;
    private String startDate;
    private String endDate;
    private String currentAssetsChange;
    private String nonCurrentAssetsChange;

    public CurrentNonCurrentAssetsAnalyze() {
        Item current = IndexesStorage.get("GeneralCurrentAssets");
        Item nonCurrent = IndexesStorage.get("NonCurrentAssets");
        this.startDate = Periods.getInstance().getStart();
        this.endDate = Periods.getInstance().getEnd();
        ObservableMap<String, Double> valuesCurrent = current.getValues();
        ObservableMap<String, Double> valuesNonCurrent = nonCurrent.getValues();
        if (valuesCurrent.size() > 1) {
            this.firstCurentVal = getFirstValue(valuesCurrent);
            this.lastCurentVal = getLastValue(valuesCurrent);
        }
        if (valuesNonCurrent.size() > 1) {
            this.firstNonCurentVal = getFirstValue(valuesNonCurrent);
            this.lastNonCurentVal = getLastValue(valuesNonCurrent);
        }
        this.currentAssetsChange = getRelativeChange(firstCurentVal, lastCurentVal);
        this.nonCurrentAssetsChange = getRelativeChange(firstNonCurentVal, lastNonCurentVal);
    }

    private Double getFirstValue(ObservableMap<String, Double> values) {
        Map.Entry<String, Double> firstCurent = getFirst(values);
        if (firstCurent != null) {
            return firstCurent.getValue();
        } else {
            return 0.0;
        }
    }

    private Double getLastValue(ObservableMap<String, Double> values) {
        Map.Entry<String, Double> firstCurent = getLast(values);
        if (firstCurent != null) {
            return firstCurent.getValue();
        } else {
            return 0.0;
        }
    }

    public VBox get() {
        VBox vbox = new VBox(10);
        vbox.setPrefWidth(600);
        String output = "";
        if (lastCurentVal != null && firstCurentVal != null && lastNonCurentVal != null && firstNonCurentVal != null) {
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
                nonCurrentAssetsChange + "% and a growth in the current assets by " +
                currentAssetsChange + "%" + endString();
    }

    private String decreaseAll() {
        return "The overall decrease of the assets reflects both a reduction in the non-current assets by " +
                nonCurrentAssetsChange + "% and a reduction in the current assets by" +
                currentAssetsChange + "%" + endString();
    }

    private String totalIncreaseCurrentDecrease() {
        return incStr() + "non-current assets by " + nonCurrentAssetsChange +
                "%, while the value of the current assets dropped by " +
                currentAssetsChange + "%" + endString();
    }

    private String totalIncreaseCurrentStable() {
        return incStr() + "non-current assets by " + nonCurrentAssetsChange +
                "%, while the value of the current assets not changed";
    }

    private String totalIncreaseNonCurrentDecrease() {
        return incStr() + "current assets by " + currentAssetsChange +
                "% while the value of the non-current assets dropped by " +
                nonCurrentAssetsChange + "%" + endString();
    }

    private String totalIncreaseNonCurrentStable() {
        return incStr() + "current assets by " + currentAssetsChange +
                "% while the value of the non-current assets not changed";
    }

    private String totalDecreaseCurrentIncrease() {
        return decStr() + "non-current assets by " + nonCurrentAssetsChange +
                "%, while the value of the current assets increased by " +
                currentAssetsChange + "%" + endString();
    }

    private String totalDecreaseCurrentStable() {
        return decStr() + "non-current assets by " + nonCurrentAssetsChange +
                "%, while the value of the current assets not changed";
    }

    private String totalDecreaseNonCurrentIncrease() {
        return decStr() + "current assets by " + currentAssetsChange +
                "% while the value of the non-current assets increased by " +
                nonCurrentAssetsChange + "%" + endString();
    }

    private String totalDecreaseNonCurrentStable() {
        return decStr() + "current assets by " + currentAssetsChange +
                "% while the value of the non-current assets was stable";
    }
}
