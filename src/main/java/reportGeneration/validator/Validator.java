package reportGeneration.validator;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.collections.ObservableMap;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.util.Pair;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.Collections;

public class Validator {

    public String validate() {
        StringBuilder output = new StringBuilder();
        ArrayList<String> errors = validateAssetsEquityLiabilities();
        errors.addAll(validateAssetsEquityPopulated());
        errors.addAll(validateComprehensiveIncome());
        String error;
        String num;
        for (int i = 0; i < errors.size(); i++) {
            error = errors.get(i);
            if (error != null) {
                num = Integer.toString(i + 1) + ". ";
                output.append(num + error + '\n');
            }
        }
        return output.toString();
    }

    private ArrayList<String> validateComprehensiveIncome() {
        Item ComprehensiveIncomeGeneral = ItemsStorage.get("ComprehensiveIncomeGeneral");
        ArrayList<String> errors = new ArrayList<>();
        boolean ComprehensiveIncomeErr = true;
        if (ComprehensiveIncomeGeneral.getValues().size() > 0) {
            ObservableMap<String, Double> vals = ComprehensiveIncomeGeneral.getValues();
            for (String key : vals.keySet()) {
                if (vals.get(key) != 0) {
                    ComprehensiveIncomeErr = false;
                }
            }
        }
        if(ComprehensiveIncomeErr){
            String err = "Please populate Comprehensive Income value to be able to submit report.";
            errors.add(err);
        }
        return errors;
    }


    private ArrayList<String> validateAssetsEquityLiabilities() {
        Item AssetsGeneral = ItemsStorage.get("AssetsGeneral");
        Item EquityAndLiabilities = ItemsStorage.get("EquityAndLiabilities");
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<String> periodsArr = Periods.getPeriodArr();
        Double AssetsGeneralCurrent;
        Double EquityAndLiabilitiesCurrent;
        for (String period : periodsArr) {
            AssetsGeneralCurrent = AssetsGeneral.getVal(period);
            EquityAndLiabilitiesCurrent = EquityAndLiabilities.getVal(period);
            if (AssetsGeneralCurrent != null && EquityAndLiabilitiesCurrent != null) {
                if (!AssetsGeneralCurrent.equals(EquityAndLiabilitiesCurrent)) {
                    String err = "Equity And Liabilities index should be equal to Total assets in "
                            + Formatter.formatDate(period);
                    errors.add(err);
                }
            }
        }
        return errors;
    }

    private ArrayList<String> validateAssetsEquityPopulated() {
        ArrayList<String> errors = new ArrayList<>();
        if(!liabilitiesPopulated() || !assetsPopulated()){
            errors.add("Please populate Equity And Liabilities value and Assets General value to be able to submit report.");
        }
        return errors;
    }


    private Boolean liabilitiesPopulated() {
        Item equityAndLiabilities = ItemsStorage.get("EquityAndLiabilities");
        Boolean showLiabilities = false;
        if (equityAndLiabilities.getValues().size() > 0) {
            ObservableMap<String, Double> vals = equityAndLiabilities.getValues();
            for (String key : vals.keySet()) {
                if (vals.get(key) != 0) {
                    showLiabilities = true;
                }
            }
        }
        return showLiabilities;
    }

    private Boolean assetsPopulated() {
        Boolean showAssets = false;
        Item assetsGeneral = ItemsStorage.get("AssetsGeneral");
        if (assetsGeneral.getValues().size() > 0) {
            ObservableMap<String, Double> vals = assetsGeneral.getValues();
            for (String key : vals.keySet()) {
                if (vals.get(key) != 0) {
                    showAssets = true;
                }
            }
        }
        return showAssets;
    }


    public void showValidation(String text) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setWidth(300);
        dialog.setTitle("Please fix following errors");
        DialogPane dialogPane = dialog.getDialogPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(LabelWrap.wrap(text));
        dialogPane.setContent(scrollPane);
        dialogPane.getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

}
