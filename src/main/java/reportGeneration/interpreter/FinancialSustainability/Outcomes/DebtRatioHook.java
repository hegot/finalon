package reportGeneration.interpreter.FinancialSustainability.Outcomes;

import entities.Formula;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import reportGeneration.storage.SettingsStorage;

import java.util.Map;
import java.util.TreeMap;

public class DebtRatioHook implements JsCalcHelper, ParseDouble, Round {
    private TreeMap<String, String> vals;
    private ObservableMap<String, String> settings;
    private String currency;
    private String company;

    public DebtRatioHook(Formula formula) {
        this.vals = new TreeMap<>(formula.getPeriods());
        this.settings = SettingsStorage.getSettings();
        this.currency = settings.get("defaultCurrency");
        this.company = settings.get("company");
    }

    public String getResult() {
        StringBuilder output = new StringBuilder();
        if (vals.size() > 0) {

            String[][] values = new String[3][3];
            int counter = 0;
            Boolean allnull = true;
            for (Map.Entry<String, String> entry : vals.entrySet()) {
                String key = formatDate(entry.getKey());
                String val = entry.getValue();
                if (parseDouble(val) != 0) {
                    allnull = false;
                }
                if (counter == 0) output.append(first(key, val));
                if (counter == 1) output.append(second(key, val));
                if (counter == 2) output.append(third(key, val));
                if (counter == 3) output.append(second(key, val));
                if (counter == 4) output.append(third(key, val));
                counter++;
            }
            if (allnull) {
                output.append(company + " was not using debt to finance its assets. " +
                        "As a result, company was sustainable in the long term run.");
            }
        }

        return output.toString();
    }

    private String first(String date, String val) {
        return "The debt ratio tells us that in " + date +
                " each " + currency + " 1.00 of the assets was financed by "
                + val + " of debt (and " + getPart(val) + " of equity). ";
    }

    private String second(String date, String val) {
        return "In " + date + " " + val +
                " of the sources of finance were liabilities. ";
    }

    private String third(String date, String val) {
        return "For every " + currency + " 1.00 of the assets there were " +
                val + " of liabilities in " + date + " ";
    }

    private String getPart(String val) {
        return round(1 - parseDouble(val));
    }

}
