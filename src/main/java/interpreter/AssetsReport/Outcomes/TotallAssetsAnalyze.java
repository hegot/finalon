package interpreter.AssetsReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Map;

public class TotallAssetsAnalyze {

    private ObservableMap<String, Double> values;
    private Map.Entry<String, Double> first;
    private Map.Entry<String, Double> last;

    public TotallAssetsAnalyze(Item item) {
        this.values = item.getValues();
        this.first = getFirst();
        this.last = getLast();
    }

    private Map.Entry<String, Double> getLast() {
        Map.Entry<String, Double> last = null;
        for (Map.Entry<String, Double> entry : values.entrySet()) {
            last = entry;
        }
        return last;
    }

    private Map.Entry<String, Double> getFirst() {
        return values.entrySet().iterator().next();
    }

    public VBox get() {
        VBox hbox = new VBox(10);
        Double first = this.first.getValue();
        Double last = this.last.getValue();
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
        Label text1 = new Label(output);
        text1.setWrapText(true);
        Label text2 = new Label(consequence());
        text2.setWrapText(true);
        hbox.getChildren().addAll(text1, text2);
        return hbox;
    }

    private String formatDate(String input) {
        input = input.replace("\n", "");
        String[] parts = input.split("-");
        return (parts[1] != null) ? parts[1] : "";
    }

    private String endDate() {
        return formatDate(last.getKey());
    }

    private String startDate() {
        return formatDate(first.getKey());
    }

    private String increase() {
        return " It can be noticed from Table 1 that there was a " +
                "tendency to increase in the total value of the assets. " +
                "The percentage change was equal " +
                getRelativeChange() + "% in " + startDate() +
                " comparing to " + endDate() + ". ";
    }

    private String decrease() {
        return "It can be noticed from Table 1 that there was a " +
                "tendency to decrease in the total value of the assets. " +
                "The percentage change was equal " +
                getRelativeChange() + "% in " + startDate() +
                " comparing to " + endDate() + ". ";
    }

    private String equal() {
        return "It can be noticed from Table 1 that " +
                "total value of the assets was stable in "
                + startDate() + " and " + endDate() + ". ";
    }

    private String consequence() {
        return "The value of the assets totalled " + last.getValue() +
                " at the end of " + endDate() + ". ";
    }

    private String getRelativeChange() {
        Double start = first.getValue();
        Double end = last.getValue();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String formula = "(("
                + Double.toString(end)
                + "-"
                + Double.toString(start)
                + ")/"
                + Double.toString(start)
                + ") * 100";
        String val = "";
        try {
            String result = engine.eval(formula).toString();
            if (result != null && result.length() > 0) {
                Double doubleInt = Double.parseDouble(result);
                val = String.format("%.1f", doubleInt);
                if (val.equals("NaN")) val = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }
}
