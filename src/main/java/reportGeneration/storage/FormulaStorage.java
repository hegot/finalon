package reportGeneration.storage;

import database.formula.DbFormulaHandler;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class FormulaStorage {
    private static ObservableList<Formula> formulas;
    private boolean initalized = false;
    private DbFormulaHandler dbFormula = new DbFormulaHandler();
    private static ObservableMap<String, Formula> indexes;

    private FormulaStorage() {
        if (!initalized) {
            try {
                init();
            } catch (Exception e) {
                System.out.println("Could not init FormulaStorage");
            }
            initalized = true;
        }
    }

    public static FormulaStorage getInstance() {
        return FormulaStorage.SingletonHolder.INSTANCE;
    }

    public static ObservableList<Formula> getItems() {
        return formulas;
    }

    public ObservableList<Formula> getItems(int id) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        for (Formula item : formulas) {
            if (item.getParent() == id) {
                Formulas.add(item);
            }
        }
        return Formulas;
    }

    public Formula get(String code) {
        Formula index = indexes.get(code);
        if (index != null) {
            return index;
        } else {
            for (Formula formula : formulas) {
                if (formula.getShortName().equals(code)) {
                    indexes.put(formula.getShortName(), formula);
                    return formula;
                }
            }
        }

        return null;
    }

    private void init() {
        this.formulas = getFormulas();
        System.out.println("Formulas Storage added!");
    }

    private ObservableList<Formula> getFormulas() {
        int rootIndustry = Integer.parseInt(SettingsStorage.getInstance().getSettings().get("industry"));
        ObservableList<Formula> myFormulas = FXCollections.observableArrayList();
        ObservableList<Formula> parents = dbFormula.getFormulas(rootIndustry);
        for (Formula child : parents) {
            myFormulas.add(child);
            ObservableList<Formula> childs2 = dbFormula.getFormulas(child.getId());
            for (Formula child2 : childs2) {
                myFormulas.add(child2);
            }
        }
        indexes = FXCollections.observableHashMap();
        return myFormulas;
    }

    public static void setFormulas(ObservableList<Formula> itemsList) {
        formulas = itemsList;
    }

    private static class SingletonHolder {
        public static final FormulaStorage INSTANCE = new FormulaStorage();
    }
}
