package reportGeneration.interpreter.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class EachPeriodTrue extends ValsEvaluator implements JsCalcHelper {
    private ObservableList<Formula> childs;
    private ObservableMap<String, String> vals;
    private ArrayList<String> periods;

    public EachPeriodTrue(Formula formula) {
        this.childs = formula.getChilds();
        this.periods = Periods.getInstance().getPeriodArr();
        this.vals = formula.getPeriods();
    }

    public String getResult() {
        String outcome = "";
        if (vals.size() > 0) {
            outcome += loopNormatives();
        }
        return outcome;
    }


    private Double getVal(String period) {
        String val = vals.get(period);
        if (val != null) {
            return parseDouble(val);
        }
        return null;
    }


    private String loopNormatives() {
        for (Formula normative : childs) {
            if(normative.getName().equals( EvaluationTypes.EACH_PERIOD_TRUE.toString())){
                try {
                    Double valueToCompare = normative.getValue().length() > 0 ? parseDouble(normative.getValue()) : null;
                    Boolean fits = true;
                    for(String period : periods){
                        Double value = getVal(period);
                        Boolean match = matches(
                                normative.getShortName(),
                                "",
                                value,
                                valueToCompare
                        );
                        if (!match) {
                            fits = false;
                        }
                    }
                    if(fits){
                        return normative.getDescription();
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return "";
    }
}
