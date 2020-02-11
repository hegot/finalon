package reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.AssetsReport.Outcomes;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class TotallAssetsAnalyze {

    private Double first;
    private Double last;
    private String startDate;
    private String endDate;

    public TotallAssetsAnalyze() {
        this.startDate = Periods.getStart();
        this.endDate = Periods.getEnd();
        Item item = ItemsStorage.get("AssetsGeneral");
        if (item.getValues().size() > 1) {
            this.first = item.getFirstVal();
            this.last = item.getLastVal();
            if (first != null) {
                SettingsStorage.put("assetsStartValue", Double.toString(first));
            }
        }
    }

    public VBox get(int weight) {
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
            String consequence = consequence();
            hbox.getChildren().addAll(
                    LabelWrap.wrap(output),
                    LabelWrap.wrap(consequence)
            );

            ResultsStorage.addStr(weight, "text", output + consequence);
        }
        return hbox;
    }


    private String increase() {
        return "It can be noticed from Table 1 that there was a " +
                "tendency to increase in the total value of the assets. " +
                "The percentage change was equal " +
                Calc.getRelativeChange(first, last) + "% in " + startDate +
                " comparing to " + endDate + ". ";
    }

    private String decrease() {
        return "It can be noticed from Table 1 that there was a " +
                "tendency to decrease in the total value of the assets. " +
                "The percentage change was equal " +
                Calc.getRelativeChange(first, last) + "% in " + startDate +
                " comparing to " + endDate + ". ";
    }

    private String equal() {
        return "It can be noticed from Table 1 that " +
                "total value of the assets was stable in "
                + startDate + " and " + endDate + ". ";
    }

    private String consequence() {
        return "The value of the assets totaled " + Formatter.finalNumberFormat(last) +
                " at the end of " + endDate + ". ";
    }
}
