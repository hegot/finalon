package finalon.reportGeneration.validator;

import finalon.entities.Item;
import finalon.globalReusables.LabelWrap;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.Periods;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.util.Pair;

import java.util.ArrayList;

public class Validator implements JsCalcHelper {

    public String validate() {
        StringBuilder output = new StringBuilder();
        ArrayList<String> errors = validateAssetsEquityLiabilities();
        for (int i = 0; i < errors.size(); i++) {
            String error = errors.get(i);
            if (error != null) {
                String num = Integer.toString(i + 1) + ". ";
                output.append(num + error + '\n');
            }
        }
        return output.toString();
    }


    private ArrayList<String> validateAssetsEquityLiabilities() {
        Item AssetsGeneral = ItemsStorage.get("AssetsGeneral");
        Item EquityAndLiabilities = ItemsStorage.get("EquityAndLiabilities");
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<String> periodsArr = Periods.getPeriodArr();
        for (String period : periodsArr) {
            Double AssetsGeneralCurrent = AssetsGeneral.getVal(period);
            Double EquityAndLiabilitiesCurrent = EquityAndLiabilities.getVal(period);
            if (AssetsGeneralCurrent != null && EquityAndLiabilitiesCurrent != null) {
                if (!AssetsGeneralCurrent.equals(EquityAndLiabilitiesCurrent)) {
                    String err = "Equity And Liabilities index should be equal to Total assets in "
                            + formatDate(period);
                    errors.add(err);
                }
            }
        }
        return errors;
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
