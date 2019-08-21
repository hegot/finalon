package finalon.reportGeneration.interpreter.FinancialResults.Outcomes;

import finalon.entities.Item;
import finalon.globalReusables.LabelWrap;
import javafx.scene.layout.VBox;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.ResultsStorage;

public class NetSalesAnalyze implements LabelWrap, Round {

    private Double first;
    private Double last;
    private Double change;

    public NetSalesAnalyze() {
        Item item = ItemsStorage.get("RevenueGeneral");
        if (item.getValues().size() > 1) {
            this.first = item.getFirstVal();
            this.last = item.getLastVal();
            this.change = item.getChange();
        }
    }

    public VBox get(int weight) {
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
            ResultsStorage.addStr(weight, "text", output);
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
