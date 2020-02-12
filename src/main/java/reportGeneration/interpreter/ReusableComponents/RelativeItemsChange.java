package reportGeneration.interpreter.ReusableComponents;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;

public class RelativeItemsChange {
    private Item parent;
    private ObservableList<Item> items;
    private String startDate;
    private String endDate;
    private String text;
    private Boolean rize;

    public RelativeItemsChange(Item parent,
                               ObservableList<Item> items,
                               String text) {
        this.parent = parent;
        this.startDate = Periods.getStart();
        this.endDate = Periods.getEnd();
        this.items = items;
        this.text = text;
        this.rize = riseOrFall();
    }

    private Boolean riseOrFall() {
        if (this.parent.getValues().size() > 1) {
            Double firstVal = parent.getFirstVal();
            Double lastVal = parent.getLastVal();
            if (firstVal != null && lastVal != null) {
                return lastVal > firstVal;
            }
        }
        return true;
    }

    private String getRowsEvaluation() {
        String output = "";
        for (Item item : items) {
            if (item.getValues().size() > 1) {
                Double firstVal = item.getFirstVal();
                Double lastVal = item.getLastVal();
                if (firstVal != null && lastVal != null) {
                    String change = Calc.getRelativeChange(firstVal, lastVal);
                    if(change.length() > 0){
                        if (rize) {
                            if (lastVal > firstVal && change.length() > 0) {
                                output += "- " + item.getName() + " (" + change + "%)\n";
                            }
                        } else {
                            if (lastVal < firstVal && change.length() > 0) {
                                output += "- " + item.getName() + " (" + change + "%)\n";
                            }
                        }
                    }
                }

            }
        }
        return output;
    }

    public VBox get(int id) {
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            String mess = message();
            vBox.getChildren().add(LabelWrap.wrap(mess));
            String rowsEval = getRowsEvaluation();
            Label label = new Label(rowsEval);
            vBox.getChildren().addAll(label);
            ResultsStorage.addStr(id, "text", mess + rowsEval);
        }
        return vBox;
    }

    private String message() {
        String change = rize ? "positive" : "negative";
        return "The change of the " + parent.getName() + " value in " +
                startDate + "-" + endDate + " was connected with a " +
                change + " change of the following " + text + ":\n";
    }
}