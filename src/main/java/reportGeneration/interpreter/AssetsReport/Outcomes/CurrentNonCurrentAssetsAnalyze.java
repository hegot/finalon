package reportGeneration.interpreter.AssetsReport.Outcomes;

import entities.Item;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CurrentNonCurrentAssetsAnalyze {

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
        Item current = ItemsStorage.get("GeneralCurrentAssets");
        Item nonCurrent = ItemsStorage.get("NonCurrentAssets");
        this.startDate = Periods.getStart();
        this.endDate = Periods.getEnd();
        this.firstCurentVal = current.getFirstVal();
        this.lastCurentVal = current.getLastVal();
        this.firstNonCurentVal = nonCurrent.getFirstVal();
        this.lastNonCurentVal = nonCurrent.getLastVal();
        if (firstCurentVal != null && lastCurentVal != null && firstNonCurentVal != null && lastNonCurentVal != null) {
            this.currentAssetsChange = Calc.getRelativeChange(firstCurentVal, lastCurentVal);
            this.nonCurrentAssetsChange = Calc.getRelativeChange(firstNonCurentVal, lastNonCurentVal);
            this.AssetsOverall = (lastCurentVal - firstCurentVal) + (lastNonCurentVal - firstNonCurentVal);
        }
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
        ResultsStorage.addStr(6, "text", output);
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
        StringBuilder output = new StringBuilder();
        output.append("The overall increase of the assets reflects both a growth in the non-current assets by ");
        output.append(nonCurrentAssetsChange);
        output.append("% and a growth in the current assets by ");
        output.append(currentAssetsChange);
        output.append("%");
        output.append(endString());
        return output.toString();
    }

    private String decreaseAll() {
        StringBuilder output = new StringBuilder();
        output.append("The overall decrease of the assets reflects both a reduction in the non-current assets by ");
        output.append(nonCurrentAssetsChange);
        output.append("% and a reduction in the current assets by");
        output.append(currentAssetsChange);
        output.append("%");
        output.append(endString());
        return output.toString();
    }

    private String totalIncreaseCurrentDecrease() {
        StringBuilder output = new StringBuilder();
        output.append(incStr());
        output.append("non-current assets by ");
        output.append(nonCurrentAssetsChange);
        output.append("%, while the value of the current assets dropped by ");
        output.append(currentAssetsChange);
        output.append("%");
        output.append(endString());
        return output.toString();
    }

    private String totalIncreaseCurrentStable() {
        StringBuilder output = new StringBuilder();
        output.append(incStr());
        output.append("non-current assets by ");
        output.append(nonCurrentAssetsChange);
        output.append("%, while the value of the current assets not changed");
        return output.toString();
    }

    private String totalIncreaseNonCurrentDecrease() {
        StringBuilder output = new StringBuilder();
        output.append(incStr());
        output.append("current assets by ");
        output.append(currentAssetsChange);
        output.append("% while the value of the non-current assets dropped by ");
        output.append(nonCurrentAssetsChange);
        output.append("%");
        output.append(endString());
        return output.toString();
    }

    private String totalIncreaseNonCurrentStable() {
        StringBuilder output = new StringBuilder();
        output.append(incStr());
        output.append("current assets by ");
        output.append(currentAssetsChange);
        output.append("% while the value of the non-current assets not changed");
        return output.toString();
    }

    private String totalDecreaseCurrentIncrease() {
        StringBuilder output = new StringBuilder();
        output.append(decStr());
        output.append("non-current assets by ");
        output.append(nonCurrentAssetsChange);
        output.append("%, while the value of the current assets increased by ");
        output.append(currentAssetsChange + "%");
        output.append(endString());
        return output.toString();
    }

    private String totalDecreaseCurrentStable() {
        StringBuilder output = new StringBuilder();
        output.append(decStr());
        output.append("non-current assets by ");
        output.append(nonCurrentAssetsChange);
        output.append("%, while the value of the current assets not changed");
        return output.toString();
    }

    private String totalDecreaseNonCurrentIncrease() {
        StringBuilder output;
        output = new StringBuilder();
        output.append(decStr());
        output.append("current assets by ");
        output.append(currentAssetsChange);
        output.append("% while the value of the non-current assets increased by ");
        output.append(nonCurrentAssetsChange);
        output.append("%");
        output.append(endString());
        return output.toString();
    }

    private String totalDecreaseNonCurrentStable() {
        StringBuilder output = new StringBuilder();
        output.append(decStr());
        output.append("current assets by ");
        output.append(currentAssetsChange);
        output.append("% while the value of the non-current assets was stable");
        return output.toString();
    }
}
