package reportGeneration.interpreter.ReusableComponents;

import entities.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import globalReusables.LabelWrap;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;

public class RelativeItemsChange implements LabelWrap, JsCalcHelper {
    private Item parent;
    private ObservableList<Item> items;
    private String startDate;
    private String endDate;
    private String text;

    public RelativeItemsChange(Item parent,
                               ObservableList<Item> items,
                               String text) {
        this.parent = parent;
        this.startDate = Periods.getInstance().getStart();
        this.endDate = Periods.getInstance().getEnd();
        this.items = items;
        this.text = text;
    }


    private String getRowsEvaluation() {
        String output = "";
        for (Item item : items) {
            if (item.getValues().size() > 1) {
                Double firstVal = item.getFirstVal();
                Double lastVal = item.getLastVal();
                if (firstVal != null && lastVal != null) {
                    String change = getRelativeChange(firstVal, lastVal);
                    output += "- " + item.getName() + " (" + change + "%) \n";
                }

            }
        }
        return output;
    }

    public VBox get(int id) {
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            String mess = message();
            vBox.getChildren().add(labelWrap(mess));
            String rowsEval = getRowsEvaluation();
            Label label = new Label(rowsEval);
            vBox.getChildren().addAll(label);
            ResultsStorage.addStr(id, "text", mess + rowsEval);
        }
        return vBox;
    }

    private String riseOrFall() {
        if (this.parent.getValues().size() > 1) {
            Double firstVal = parent.getFirstVal();
            Double lastVal = parent.getLastVal();
            if (firstVal != null && lastVal != null) {
                return (lastVal > firstVal) ? "positive" : "negative";
            }
        }
        return "";
    }

    private String message() {
        return "The change of the " + parent.getName() + " value in " +
                startDate + "-" + endDate + " was connected with a " +
                riseOrFall() + " change of the following " + text + ": \n";
    }
}
