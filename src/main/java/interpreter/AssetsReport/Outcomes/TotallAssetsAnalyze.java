package interpreter.AssetsReport.Outcomes;

import entities.Item;
import interpreter.ReusableComponents.LabelWrap;
import interpreter.ReusableComponents.OutcomeBase;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;

public class TotallAssetsAnalyze extends OutcomeBase implements LabelWrap {

    private Double first;
    private Double last;
    private String startDate;
    private String endDate;

    public TotallAssetsAnalyze(
            ObservableMap<String, String> settings,
            Item item,
            String startDate,
            String endDate
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        ObservableMap<String, Double> values = item.getValues();
        if (values.size() > 1) {
            this.first = getFirstVal(values);
            this.last = getLastVal(values);
            settings.put("assetsDifference", Double.toString(last - first));
            settings.put("assetsStartValue", Double.toString(first));
            settings.put("assetsEndValue", Double.toString(last));
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
            hbox.getChildren().addAll(
                    labelWrap(output),
                    labelWrap(consequence())
            );
        }
        return hbox;
    }


    private String increase() {
        return "It can be noticed from Table 1 that there was a " +
                "tendency to increase in the total value of the assets. " +
                "The percentage change was equal " +
                getRelativeChange(first, last) + "% in " + startDate +
                " comparing to " + endDate + ". ";
    }

    private String decrease() {
        return "It can be noticed from Table 1 that there was a " +
                "tendency to decrease in the total value of the assets. " +
                "The percentage change was equal " +
                getRelativeChange(first, last) + "% in " + startDate +
                " comparing to " + endDate + ". ";
    }

    private String equal() {
        return "It can be noticed from Table 1 that " +
                "total value of the assets was stable in "
                + startDate + " and " + endDate + ". ";
    }

    private String consequence() {
        return "The value of the assets totalled " + last +
                " at the end of " + endDate + ". ";
    }
}
