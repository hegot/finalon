package interpreter.AssetsReport.Outcomes;

import entities.Item;
import interpreter.ReusableComponents.OutcomeBase;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Map;

public class RelativeAssetsChange extends OutcomeBase {
    private Item parent;
    private ObservableList<Item> items;
    private String startDate;
    private String endDate;

    public RelativeAssetsChange(Item parent,
                                ObservableList<Item> items,
                                String startDate,
                                String endDate) {
        this.parent = parent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.items = items;
    }


    private void attachRowsEvaluation(VBox vBox) {
        for (Item item : items) {
            if (item.getValues().size() > 1) {
                ObservableMap<String, Double> values = item.getValues();
                Map.Entry<String, Double> first = getFirst(values);
                Map.Entry<String, Double> last = getLast(values);
                Double firstVal = first.getValue();
                Double lastVal = last.getValue();
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
            Map.Entry<String, Double> first = getFirst(values);
            Map.Entry<String, Double> last = getLast(values);
            Double firstVal = first.getValue();
            Double lastVal = last.getValue();
            if (firstVal != null && lastVal != null) {
                return (lastVal > first.getValue()) ? "positive" : "negative";
            }
        }
        return "";
    }

    private Label message() {
        Label label = new Label("The change of the " + parent.getName() + " value in " +
                startDate + "-" + endDate + " was connected with a " +
                riseOrFall() + "change of the following assets:");
        return label;

    }
}
