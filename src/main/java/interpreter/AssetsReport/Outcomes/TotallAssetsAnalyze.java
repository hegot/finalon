package interpreter.AssetsReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Map;

public class TotallAssetsAnalyze extends OutcomeBase {

    private Map.Entry<String, Double> first;
    private Map.Entry<String, Double> last;
    private String startDate;
    private String endDate;

    public TotallAssetsAnalyze(
            Item item,
            String startDate,
            String endDate
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        ObservableMap<String, Double> values = item.getValues();
        if (values.size() > 1) {
            this.first = getFirst(values);
            this.last = getLast(values);
        }
    }

    public VBox get() {
        VBox hbox = new VBox(10);
        if (first != null && last != null) {
            Double firstVal = first.getValue();
            Double lastVal = last.getValue();
            String output = "";
            if (lastVal > firstVal) {
                output = increase();
            }
            if (lastVal < firstVal) {
                output = decrease();
            }
            if (lastVal == firstVal) {
                output = equal();
            }
            Label text1 = new Label(output);
            text1.getStyleClass().add("report-text-small");
            text1.setWrapText(true);
            Label text2 = new Label(consequence());
            text2.getStyleClass().add("report-text-small");
            text2.setWrapText(true);
            hbox.getChildren().addAll(text1, text2);
        }
        return hbox;
    }


    private String increase() {
        return "It can be noticed from Table 1 that there was a " +
                "tendency to increase in the total value of the assets. " +
                "The percentage change was equal " +
                getRelativeChange(first.getValue(), last.getValue()) + "% in " + startDate +
                " comparing to " + endDate + ". ";
    }

    private String decrease() {
        return "It can be noticed from Table 1 that there was a " +
                "tendency to decrease in the total value of the assets. " +
                "The percentage change was equal " +
                getRelativeChange(first.getValue(), last.getValue()) + "% in " + startDate +
                " comparing to " + endDate + ". ";
    }

    private String equal() {
        return "It can be noticed from Table 1 that " +
                "total value of the assets was stable in "
                + startDate + " and " + endDate + ". ";
    }

    private String consequence() {
        return "The value of the assets totalled " + last.getValue() +
                " at the end of " + endDate + ". ";
    }
}
