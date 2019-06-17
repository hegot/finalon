package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.storage.IndexesStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;


public class TotallLiabilitiesAnalyze implements LabelWrap, ParseDouble, JsCalcHelper {

    private Double first;
    private Double last;
    private Double assetDiffrence;
    private Double liabilitiesDifference;
    private String startDate;
    private String endDate;
    private ObservableMap<String, String> settings;

    public TotallLiabilitiesAnalyze() {
        Item liabilities = IndexesStorage.get("EquityAndLiabilities");
        this.settings = SettingsStorage.getSettings();
        this.startDate = Periods.getInstance().getStart();
        this.endDate = Periods.getInstance().getEnd();
        if (liabilities.getValues().size() > 1) {
            this.first = liabilities.getFirstVal();
            this.last = liabilities.getLastVal();
            this.liabilitiesDifference = last - first;
        }
        String assetDiffrence = settings.get("assetsDifference");
        this.assetDiffrence = parseDouble(assetDiffrence);
    }

    public VBox get() {
        VBox hbox = new VBox(10);
        if (first != null && last != null) {
            String output = "";
            if (last > first) {
                output = increase();
            }
            if (last < first) {
                output = decrease();
            }
            if (last == first) {
                output = equal();
            }
            if (last <= 0) {
                output = bankrupt();
            }
            hbox.getChildren().addAll(labelWrap(preOutput()), labelWrap(output));
        }
        return hbox;
    }

    private String suffix() {
        if (liabilitiesDifference > 0) {
            return "more";
        } else if (liabilitiesDifference < 0) {
            return "less";
        } else {
            return "";
        }
    }


    private String prefix() {
        String res = "Differently ";
        if (liabilitiesDifference != null && assetDiffrence != null) {
            if (liabilitiesDifference > 0 && assetDiffrence > 0 ||
                    liabilitiesDifference < 0 && assetDiffrence < 0 ||
                    liabilitiesDifference == 0 && assetDiffrence == 0) {
                res = "Similar ";
            }

        }
        return res;
    }


    private String preOutput() {
        return prefix() + "to the value of total assets, the liabilities and equity value" +
                " amounted to " + settings.get("defaultCurrency") + " "
                + last + " " + settings.get("amount") + " in "
                + endDate + ", " +
                getRelativeChange(first, last) + "% " + suffix() + " than in " +
                endDate + ".";
    }

    private String increase() {
        return "There was a stable growth of the stockholders' equity value in " +
                startDate + " - " + endDate +
                ", which indicates that the company's assets would worth more " +
                "after all claims upon those assets were paid. This means that " +
                settings.get("company") +
                " was expanding.";
    }

    private String decrease() {
        return "There was a stable decline in the stockholders' equity value in " +
                startDate + " - " + endDate +
                ", which indicates that the company's assets would worth less " +
                "after all claims upon those assets were paid. This means that " +
                settings.get("company") +
                " was degrading.";
    }

    private String equal() {
        return "Stockholders' equity value in" +
                startDate + " - " + endDate +
                ", was stable";
    }

    private String bankrupt() {
        return "The stockholders' equity value equals zero or less, meaning that the company may go bankrupt.";
    }

}
