package interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import interpreter.ReusableComponents.OutcomeBase;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class TotallLiabilitiesAnalyze extends OutcomeBase {

    private Double first;
    private Double last;
    private Double assetDiffrence;
    private Double liabilitiesDifference;
    private String startDate;
    private String endDate;
    private ObservableMap<String, String> settings;

    public TotallLiabilitiesAnalyze(
            ObservableMap<String, String> settings,
            Item liabilities,
            String startDate,
            String endDate
    ) {
        this.settings = settings;
        this.startDate = startDate;
        this.endDate = endDate;
        ObservableMap<String, Double> values = liabilities.getValues();
        if (values.size() > 1) {
            this.first = getFirstVal(values);
            this.last = getLastVal(values);
            this.liabilitiesDifference = last - first;
        }
        String assetDiffrence = settings.get("assetsDifference");
        this.assetDiffrence = (assetDiffrence != null) ?  Double.parseDouble(assetDiffrence) : 0;
    }

    public VBox get() {
        VBox hbox = new VBox(10);
        if (first != null && last != null) {
            String output = output();
            Label text1 = new Label(output);
            text1.getStyleClass().add("report-text-small");
            text1.setWrapText(true);
            hbox.getChildren().addAll(text1);
        }
        return hbox;
    }

    private String suffix(){
        if(liabilitiesDifference > 0){
            return "more";
        } else if(liabilitiesDifference < 0){
            return "less";
        }else{
            return "";
        }
    }



    private String prefix(){
        String res = "Differently ";
        if(liabilitiesDifference > 0 && assetDiffrence > 0 ||
                liabilitiesDifference < 0 && assetDiffrence < 0 ||
                liabilitiesDifference == 0 && assetDiffrence == 0){
            res = "Similar ";
        }
        return res;
    }


    private String output() {
        return prefix() + "to the value of total assets, the liabilities and equity value" +
                " amounted to " +  settings.get("defaultCurrency") + " " + last + " " + settings.get("amount") + " in "
                + endDate + ", " +
                getRelativeChange(first, last) + "% " + suffix() + " than in " +
                endDate + ".";
    }


}
