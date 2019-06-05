package reportGeneration.interpreter.AssetsReport.Outcomes;

import entities.Item;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.storage.IndexesStorage;
import reportGeneration.storage.Periods;

public class CurrentNonCurrentAssetsAnalyze implements JsCalcHelper {

    private Double firstCurentVal;
    private Double lastCurentVal;
    private Double firstNonCurentVal;
    private Double lastNonCurentVal;
    private String startDate;
    private String endDate;
    private String currentAssetsChange;
    private String nonCurrentAssetsChange;
    private Double AssetsOverall;

    public CurrentNonCurrentAssetsAnalyze() {
        Item current = IndexesStorage.get("GeneralCurrentAssets");
        Item nonCurrent = IndexesStorage.get("NonCurrentAssets");
        this.startDate = Periods.getInstance().getStart();
        this.endDate = Periods.getInstance().getEnd();
        this.firstCurentVal = current.getFirstVal();
        this.lastCurentVal = current.getLastVal();
        this.firstNonCurentVal = nonCurrent.getFirstVal();
        this.lastNonCurentVal = nonCurrent.getLastVal();
        if (firstCurentVal != null && lastCurentVal != null) {
            this.currentAssetsChange = getRelativeChange(firstCurentVal, lastCurentVal);
        }
        if (firstNonCurentVal != null && lastNonCurentVal != null) {
            this.nonCurrentAssetsChange = getRelativeChange(firstNonCurentVal, lastNonCurentVal);
        }
        this.AssetsOverall = (lastCurentVal - firstCurentVal) + (lastNonCurentVal - firstNonCurentVal);
    }


    public VBox get() {
        VBox vbox = new VBox(10);
        vbox.setPrefWidth(600);
        String output = "";
        if (lastCurentVal != null && firstCurentVal != null && lastNonCurentVal != null && firstNonCurentVal != null) {

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

        if (AssetsOverall != 0) {
            return "The overall decrease of the assets reflects a reduction in the ";
        } else {
            return "Overall assets did not changed. However there was a reduction in the ";
        }
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
