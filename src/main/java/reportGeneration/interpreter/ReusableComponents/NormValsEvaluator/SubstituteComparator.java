package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import reportGeneration.interpreter.ReusableComponents.interfaces.EvaluateOperator;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class SubstituteComparator extends ValsEvaluator implements JsCalcHelper, ParseDouble, EvaluateOperator {
    private Formula formula;
    private ObservableList<Formula> childs;
    private ArrayList<String> periodsArr;

    public SubstituteComparator(Formula formula) {
        this.formula = formula;
        this.periodsArr = Periods.getInstance().getPeriodArr();
        this.childs = formula.getChilds();
    }

    public String getResult() {
        StringBuilder out = new StringBuilder();
        if (formula.getPeriods().size() > 0) {
            for (Formula normative : childs) {
                if (normative.getName().equals(
                        EvaluationTypes.SUBSTITUTE_COMPARATOR.toString()
                )) {
                    out.append(evaluate(normative));
                }
            }
        }
        return strReplace(out.toString());
    }

    private String strReplace(String text) {
        String lastValChange = Double.toString(formula.getLastVal() - getPreLastVal());
        text = text.replace("LASTVALUECHANGE", lastValChange);
        text = text.replace("PREENDDATE", formatDate(getPreEndDate()));
        return text;
    }

    private String evaluate(Formula normative) {
        try {
            Boolean match = false;
            Double firstVal = getVal(normative.getShortName());
            Double secondVal = getVal(normative.getValue());
            String operator = normative.getCategory();
            if (firstVal != null && secondVal != null) {
                match = evaluateOperator(operator, firstVal, secondVal);
            }
            if (match) {
                return normative.getDescription();
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private Double getVal(String str) {
        if (str.length() > 0) {
            if (str.equals("LASTVALUE")) {
                return formula.getLastVal();
            } else if (str.equals("PRELASTVALUE")) {

                return getPreLastVal();
            }
        }
        return null;
    }

    private Double getPreLastVal() {
        String period = getPreEndDate();
        if (period != null) {
            periodsArr.get(periodsArr.size() - 2);
            return formula.getVal(period);
        }
        return null;
    }

    private String getPreEndDate(){
        if (periodsArr.size() > 2) {
            return periodsArr.get(periodsArr.size() - 2);
        }
        return null;
    }
}
