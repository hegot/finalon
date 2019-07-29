package reportGeneration.interpreter.FinancialResults.Outcomes;

import entities.Item;
import javafx.scene.layout.VBox;
import globalReusables.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.ResultsStorage;

public class NetSalesAnalyze implements LabelWrap, Round {

    private Double first;
    private Double last;
    private Double change;

    public NetSalesAnalyze() {
        ItemsStorage stor = ItemsStorage.getInstance();
        Item item = stor.get("RevenueGeneral");
        if (item.getValues().size() > 1) {
            this.first = item.getFirstVal();
            this.last = item.getLastVal();
            this.change = item.getChange();
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
            ResultsStorage.addStr(133, "text", output);
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
