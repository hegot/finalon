package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;
import entities.Formula;
import finalonWindows.formulaScene.eventHandlers.FormulaExtended;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.*;

public class EditStorage {
    public static EditStorage editStorage;
    private static Map<Integer, FormulaExtended> formulas;
    private static DbFormulaHandler dbFormula = new DbFormulaHandler();
    private boolean initalized = false;

    private EditStorage() {
        if (!initalized) {
            formulas = new HashMap<>();
            initalized = true;
        }
    }

    public static EditStorage getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void addItem(Integer key, FormulaExtended formula) {
        formulas.put(key, formula);
    }

    public static FormulaExtended find(Integer key) {
        return formulas.get(key);
    }

    public static Map<Integer, FormulaExtended> getItems() {
        return formulas;
    }

    static void saveItems() throws SQLException {
        Iterator it = formulas.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            FormulaExtended formula = (FormulaExtended) pair.getValue();
            int ID = formula.getId();
            dbFormula.updateFormula(formula);


            //handle added normative values
            ObservableList<Formula> childs = formula.getChilds();
            for (Formula child : childs) {
                if (child.getId() == -1) {
                    dbFormula.addFormula(child);
                } else {
                    dbFormula.updateFormula(child);
                }
            }

            //handle deleted items
            ObservableList<Formula> oldChilds = dbFormula.getFormulas(ID);
            for (Formula oldChild : oldChilds) {
                int id = oldChild.getId();
                Boolean found = false;
                for (Formula child : childs) {
                    if (child.getId() == id || child.getId() == -1) {
                        found = true;
                    }
                }
                if (!found) {
                    dbFormula.deleteItem(id);
                }
            }

            if (formula.getCategory() == "TO_BE_DELETED") {
                dbFormula.deleteItem(ID);
            }
            if (formula.getCategory() == "TO_BE_ADDED") {
                dbFormula.updateFormula(formula);
            }
        }
        it.remove();
    }

    public static int getBiggestId() {
        Set<Integer> set = formulas.keySet();
        if (set.size() > 0) {
            return Collections.max(set);
        }
        return 0;
    }

    private static class SingletonHolder {
        static final EditStorage INSTANCE = new EditStorage();
    }


}
