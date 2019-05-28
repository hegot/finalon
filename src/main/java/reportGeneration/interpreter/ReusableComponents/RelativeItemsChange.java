package reportGeneration.interpreter.ReusableComponents;

import entities.Item;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.storage.Periods;

public class RelativeItemsChange extends OutcomeBase implements LabelWrap {
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


    private void attachRowsEvaluation(VBox vBox) {
        for (Item item : items) {
            if (item.getValues().size() > 1) {
                ObservableMap<String, Double> values = item.getValues();
                Double firstVal = getFirstVal(values);
                Double lastVal = getLastVal(values);
                if (firstVal != null && lastVal != null) {
                    String change = getRelativeChange(firstVal, lastVal);
                    Label label = new Label("- " + item.getName() + " (" + change + "%)");
                    vBox.getChildren().addAll(label);
                }

            }
        }
    }

    public VBox get() {
        VBox vBox = new VBox(10);

        if (this.parent.getValues().size() > 1) {
            vBox.getChildren().add(message());
        }
        attachRowsEvaluation(vBox);
        return vBox;
    }

    private String riseOrFall() {
        if (this.parent.getValues().size() > 1) {
            ObservableMap<String, Double> values = this.parent.getValues();
            Double firstVal = getFirstVal(values);
            Double lastVal = getLastVal(values);
            if (firstVal != null && lastVal != null) {
                return (lastVal > firstVal) ? "positive" : "negative";
            }
        }
        return "";
    }

    private Label message() {
        return labelWrap(
                "The change of the " + parent.getName() + " value in " +
                        startDate + "-" + endDate + " was connected with a " +
                        riseOrFall() + " change of the following " + text + ":");
    }
}
