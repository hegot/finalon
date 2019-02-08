package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;
import entities.Formula;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EditStorage {
    public static Map<Integer, Formula> formulas;
    public static ArrayList<Formula> formulasAdd;
    public static ArrayList<Formula> formulasRemove;
    public static EditStorage editStorage;
    private boolean initalized = false;
    private static DbFormulaHandler dbFormula = new DbFormulaHandler();

    private EditStorage() {
        if (!initalized) {
            formulas = new HashMap<>();
            formulasAdd = new ArrayList<Formula>();
            formulasRemove = new ArrayList<Formula>();
            initalized = true;
        }
    }

    public static EditStorage getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private static class SingletonHolder {
        public static final EditStorage INSTANCE = new EditStorage();
    }

    public static void addItem(Integer key, Formula formula) {
        formulas.put(key, formula);
    }

    public static void addItemsAdded(ArrayList<Formula> formulas) {
        formulasAdd.addAll(formulas);
    }

    public static void addItemsDeleted(ArrayList<Formula> formulas) {
        formulasRemove.addAll(formulas);
    }

    public static Map<Integer, Formula> getItems() {
        return formulas;
    }

    public static void saveItems() throws SQLException {
        Iterator it = formulas.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Formula formula = (Formula) pair.getValue();
            dbFormula.updateFormula(formula);
        }
        it.remove();
        for(Formula formulaAdd : formulasAdd){
            dbFormula.addFormula(formulaAdd);
        }
        for(Formula formulaRem : formulasRemove){
            dbFormula.deleteItem(formulaRem.getId());
        }
    }


}
