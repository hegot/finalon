package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;


public class TotallLiabilitiesAnalyze {

    private Double first;
    private Double last;
    private Double liabilitiesDifference;
    private String startDate;
    private String endDate;

    public TotallLiabilitiesAnalyze() {
        Item liabilities = ItemsStorage.get("EquityGeneral");
        this.startDate = Periods.getStart();
        this.endDate = Periods.getEnd();
        if (liabilities.getValues().size() > 1) {
            this.first = liabilities.getFirstVal();
            this.last = liabilities.getLastVal();
            this.liabilitiesDifference = last - first;
        }
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
            String preOutput = preOutput();
            ResultsStorage.addStr(21, "text", preOutput + output);
            hbox.getChildren().addAll(LabelWrap.wrap(preOutput), LabelWrap.wrap(output));
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


    private String preOutput() {
        return "The liabilities and equity value" +
                " amounted to " + SettingsStorage.get("defaultCurrency") + " "
                + last + " " + SettingsStorage.get("amount") + " in "
                + startDate + ", " +
                Calc.getRelativeChange(first, last) + "% " + suffix() + " than in " +
                endDate + ". ";
    }

    private String increase() {
        return "There was a stable growth of the stockholders' equity value in " +
                startDate + " - " + endDate +
                ", which indicates that the company's assets would worth more " +
                "after all claims upon those assets were paid. This means that " +
                SettingsStorage.get("company") +
                " was expanding. ";
    }

    private String decrease() {
        return "There was a stable decline in the stockholders' equity value in " +
                startDate + " - " + endDate +
                ", which indicates that the company's assets would worth less " +
                "after all claims upon those assets were paid. This means that " +
                SettingsStorage.get("company") +
                " was degrading. ";
    }

    private String equal() {
        return "Stockholders' equity value in" +
                startDate + " - " + endDate +
                ", was stable";
    }

    private String bankrupt() {
        return "The stockholders' equity value equals zero or less, meaning that the company may go bankrupt. ";
    }

}
