package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.Random;

public class EachPeriodMultivariate implements JsCalcHelper, ParseDouble {
    private ObservableList<Formula> childs;
    private Formula formula;
    private ArrayList<String> periods;

    public EachPeriodMultivariate(Formula formula) {
        this.childs = formula.getChilds();
        this.formula = formula;
        this.periods = Periods.getInstance().getPeriodArr();
    }

    private ArrayList<String> getConsequenceArr() {
        ArrayList<String> consequences = new ArrayList<>();
        for (Formula formula : childs) {
            if (formula.getName().equals(
                    EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString()
            )) {
                consequences.add(formula.getDescription());
            }
        }
        if (periods.size() > consequences.size()) {
            int diff = periods.size() - consequences.size();
            Random rand = new Random();
            ArrayList<String> consequencesClone = (ArrayList<String>) consequences.clone();
            for (int i = 1; i < diff; i++) {
                int randomIndex = rand.nextInt(consequences.size());
                consequences.add(consequencesClone.get(randomIndex));
            }
        }
        return consequences;
    }

    public String getResult() {
        StringBuilder outcome = new StringBuilder();
        Random rand = new Random();
        ArrayList<String> consequences = getConsequenceArr();
        for (int i = 0; i < periods.size(); i++) {
            String period = periods.get(i);
            int randomIndex = rand.nextInt(consequences.size());
            if (consequences.get(randomIndex) != null
                    && formula.getVal(period) != null) {
                outcome.append(
                        strReplace(consequences.get(randomIndex), period)
                );
                consequences.remove(randomIndex);
            }

        }
        return outcome.toString();
    }


    private String strReplace(String outcome, String period) {
        outcome = outcome.replace("CURRENTPERIOD", formatDate(period));
        Double val = formula.getVal(period);
        if (val != null) {
            Double valDob = val * 100;
            outcome = outcome.replace("CURRENTVALUEPERCENT", toString(valDob));
            outcome = outcome.replace("CURRENTVALUE", toString(val));
        }
        return outcome;
    }

}
