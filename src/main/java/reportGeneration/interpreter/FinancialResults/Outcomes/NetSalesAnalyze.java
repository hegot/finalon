package reportGeneration.interpreter.FinancialResults.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.OutcomeBase;
import reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import reportGeneration.storage.ItemsStorage;

public class NetSalesAnalyze implements LabelWrap, OutcomeBase, Round {

    private Double first;
    private Double last;
    private Double change;
    private ItemsStorage stor = ItemsStorage.getInstance();

    public NetSalesAnalyze() {
        Item item = stor.getItemByCode("RevenueGeneral");
        ObservableMap<String, Double> values = item.getValues();
        if (values.size() > 1) {
            this.first = getFirstVal(values);
            this.last = getLastVal(values);
            this.change = ((last - first) / first) * 100;
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
            hbox.getChildren().add(labelWrap(output));
        }
        return hbox;
    }


    private String increase() {
        return "The comparative income statement given above shows there " +
                "has been an increase in the net sales of "
                + round(change) + "% over the reported period. ";
    }

    private String decrease() {
        return "The comparative income statement given above shows that " +
                "there has been a decrease in the net sales of "
                + round(change) + "% over the reported period. ";
    }

    private String equal() {
        return "The comparative income statement given above shows " +
                "that the overall sales level has been " +
                "stable over the reported period. ";
    }
}
