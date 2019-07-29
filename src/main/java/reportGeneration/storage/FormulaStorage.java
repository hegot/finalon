package reportGeneration.storage;

import database.formula.DbFormulaHandler;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class FormulaStorage {
    private static ObservableList<Formula> formulas;
    private static ObservableList<Formula> sections;
    private static ObservableMap<String, Formula> indexes;
    private boolean initalized = false;
    private DbFormulaHandler dbFormula = new DbFormulaHandler();

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
            if (item.getParent() == id && item.getPeriods().size() > 0) {
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
        this.sections = initSections();
        this.formulas = getFormulas();
        System.out.println("Formulas Storage added!");
    }

    private ObservableList<Formula> initSections() {
        int rootIndustry = Integer.parseInt(SettingsStorage.getInstance().getSettings().get("industry"));
        ObservableList<Formula> parents = dbFormula.getFormulas(rootIndustry);
        return parents;
    }

    public static ObservableList<Formula> getSections() {
        return sections;
    }

    private ObservableList<Formula> getFormulas() {
        ObservableList<Formula> myFormulas = FXCollections.observableArrayList();
        for (Formula child : sections) {
            myFormulas.add(child);
            ObservableList<Formula> childs2 = dbFormula.getFormulas(child.getId());
            for (Formula child2 : childs2) {
                myFormulas.add(child2);
            }
        }
        myFormulas.add(new Formula(-1,
                "Net Sales Change",
                "NetSalesChange",
                "(RevenueGeneral[1]-(RevenueGeneral[0]))/RevenueGeneral[0]",
                "",
                "formula",
                "",
                0));
        myFormulas.add(new Formula(-1,
                "Equity Change",
                "EquityChange",
                "(EquityGeneral[1]-(EquityGeneral[0]))/EquityGeneral[0]",
                "",
                "formula",
                "",
                0));
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
