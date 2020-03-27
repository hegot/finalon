package finalonWindows.templateScene.TemplateEditPage.EventHandlers;

import database.formula.DbFormulaHandler;
import entities.Formula;
import javafx.collections.ObservableList;

import java.util.*;

public class FormulaUsage {

    public static String usagesString(String code, int tplId){
        Map<Integer, Formula> usages = findUsage(code, tplId);
        StringBuilder builder = new StringBuilder();
        Set<String> names = new HashSet<String>();
        for (Map.Entry<Integer, Formula> entry : usages.entrySet()) {
            names.add(entry.getValue().getName());
        }
        Iterator it = names.iterator();
        while (it.hasNext()) {
            builder.append("«" + it.next() + "»\n");
        }
        return builder.toString();
    }

    public static Map<Integer, Formula> findUsage(String code, int tplId){
        Map<Integer, Formula> finalUsages = new HashMap<Integer, Formula>();
        ArrayList<Formula> formulas = getFormulasForTpl(tplId);
        for (Formula formula : formulas) {
            if (formula.getValue().contains(code)) {
                finalUsages.put(formula.getId(), formula);
            }
        }
        return finalUsages;
    }

    private static ArrayList<Formula> getFormulasForTpl(int tplId) {
        ArrayList<Formula> items = new ArrayList<>();
        addAllChilds(tplId, items);
        return items;
    }

    private static void addAllChilds(int Id, ArrayList<Formula> items) {
        ObservableList<Formula> formulas = DbFormulaHandler.getFormulas(Id);
        if (formulas.size() > 0) {
            for (Formula formula : formulas) {
                addAllChilds(formula.getId(), items);
                items.add(formula);
            }
        }
    }
}
