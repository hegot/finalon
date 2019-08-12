package reportGeneration.interpreter.AssetsReport.Outcomes;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class TotallAssetsAnalyze implements LabelWrap, JsCalcHelper {

    private Double first;
    private Double last;
    private String startDate;
    private String endDate;

    public TotallAssetsAnalyze() {
        this.startDate = Periods.getInstance().getStart();
        this.endDate = Periods.getInstance().getEnd();
        ItemsStorage stor = ItemsStorage.getInstance();
        Item item = stor.get("AssetsGeneral");
        if (item.getValues().size() > 1) {
            this.first = item.getFirstVal();
            this.last = item.getLastVal();
            ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
            settings.put("assetsDifference", Double.toString(last - first));
            settings.put("assetsStartValue", Double.toString(first));
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
            String consequence = consequence();
            hbox.getChildren().addAll(
                    labelWrap(output),
                    labelWrap(consequence)
            );

            ResultsStorage.addStr(5, "text", output + consequence);
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
        return "The value of the assets totaled " + last +
                " at the end of " + endDate + ". ";
    }
}
