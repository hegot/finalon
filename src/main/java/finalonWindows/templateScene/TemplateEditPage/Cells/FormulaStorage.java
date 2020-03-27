package finalonWindows.templateScene.TemplateEditPage.Cells;

import database.formula.DbFormulaHandler;
import entities.Formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormulaStorage {
    private static Map<Integer, Formula> formulasUpdate = new HashMap<>();
    private static List<Integer> formulasDelete = new ArrayList<Integer>();

    public static void addFormulaUpdate(Formula formula) {
        formulasUpdate.put(formula.getId(), formula);
    }

    public static void addFormulaDelete(Integer id) {
        formulasDelete.add(id);
    }


    public static Formula getFormulaUpdate(int id) {
        return formulasUpdate.get(id);
    }

    public static void updateFormulas() {
        for (Map.Entry<Integer, Formula> entry : formulasUpdate.entrySet()) {
            Formula formula = entry.getValue();
            DbFormulaHandler.updateFormula(formula);
        }
        formulasUpdate = new HashMap<>();
        for (Integer id : formulasDelete) {
            DbFormulaHandler.deleteItem(id);
        }
        formulasDelete = new ArrayList<>();
    }
}
