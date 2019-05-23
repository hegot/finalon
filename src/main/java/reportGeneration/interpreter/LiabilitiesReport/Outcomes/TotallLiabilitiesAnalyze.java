package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;
import reportGeneration.SettingsStorage;
import reportGeneration.interpreter.ReusableComponents.OutcomeBase;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;


public class TotallLiabilitiesAnalyze extends OutcomeBase implements LabelWrap {

    private Double first;
    private Double last;
    private Double assetDiffrence;
    private Double liabilitiesDifference;
    private String startDate;
    private String endDate;
    private ObservableMap<String, String> settings;

    public TotallLiabilitiesAnalyze(
            Item liabilities,
            String startDate,
            String endDate
    ) {
        this.settings = SettingsStorage.getSettings();
        this.startDate = startDate;
        this.endDate = endDate;
        ObservableMap<String, Double> values = liabilities.getValues();
        if (values.size() > 1) {
            this.first = getFirstVal(values);
            this.last = getLastVal(values);
            this.liabilitiesDifference = last - first;
        }
        String assetDiffrence = settings.get("assetsDifference");
        this.assetDiffrence = (assetDiffrence != null) ? Double.parseDouble(assetDiffrence) : 0;
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
        if (liabilitiesDifference > 0 && assetDiffrence > 0 ||
                liabilitiesDifference < 0 && assetDiffrence < 0 ||
                liabilitiesDifference == 0 && assetDiffrence == 0) {
            res = "Similar ";
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
