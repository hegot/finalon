package reportGeneration.interpreter.FinancialSustainability.Outcomes;

import entities.Formula;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.storage.SettingsStorage;

import java.util.Map;
import java.util.TreeMap;

public class DebtRatioHook implements JsCalcHelper {
    private TreeMap<String, String> vals;
    private ObservableMap<String, String> settings;
    private String currency;

    public DebtRatioHook(Formula formula) {
        this.vals = new TreeMap<>(formula.getPeriods());
        this.settings = SettingsStorage.getSettings();
        this.currency = settings.get("defaultCurrency");
    }

    public String getResult() {
        String output = "";
        if (vals.size() > 0) {

            String[][] values = new String[3][3];
            int counter = 0;
            for (Map.Entry<String, String> entry : vals.entrySet()) {
                String key = formatDate(entry.getKey());
                String val = entry.getValue();
                if (counter == 0) output += first(key,val);
                if (counter == 1) output += second(key,val);
                if (counter == 2) output += third(key,val);
                if (counter == 3) output += second(key,val);
                if (counter == 4) output += third(key,val);
                counter++;
            }
        }

        return output;
    }

    private String first(String date, String val) {
        return "The debt ratio tells us that in " + date +
                " each " + currency + " 1.00 of the assets was financed by "
                + val + "% of debt (and " + getPart(val) + "% of equity). ";
    }

    private String second(String date, String val) {
        return "In " + date + " " + val +
                "% of the sources of finance were liabilities. ";
    }

    private String third(String date, String val) {
        return "For every " + currency + " 1.00 of the assets there were " +
                val + "% of liabilities in " + date + " ";
    }

    private String getPart(String val) {
        Double valInt = Double.parseDouble(val);
        return Double.toString(1 - valInt);
    }

}
