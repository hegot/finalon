package reportGeneration.storage;

import database.formula.DbFormulaHandler;
import entities.Formula;
import globalReusables.StandardAndIndustry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FormulaStorage {

    private static ObservableList<Formula> formulas = getFormulas();


    public static ObservableList<Formula> getItems() {
        return formulas;
    }

    public static ObservableList<Formula> getItems(int id) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        for (Formula item : formulas) {
            if (item.getParent() == id && item.getPeriods().size() > 0) {
                Formulas.add(item);
            }
        }
        return Formulas;
    }

    public static Formula get(String code) {
        for (Formula formula : formulas) {
            if (formula.getShortName().equals(code)) {
                return formula;
            }
        }
        return null;
    }

    public static void reInit() {
        if (formulas.size() > 0) {
            formulas = getFormulas();
        }
    }

    public static ObservableList<Formula> getSections() {
        Integer industryId = SettingsStorage.getInt("industry");
        if (industryId == null) {
            industryId = StandardAndIndustry.getIndustryId();
        }
        ObservableList<Formula> parents = DbFormulaHandler.getFormulas(industryId);
        return parents;
    }

    public static ObservableList<Formula> getFormulas() {
        ObservableList<Formula> sections = getSections();
        ObservableList<Formula> myFormulas = FXCollections.observableArrayList();
        for (Formula child : sections) {
            myFormulas.add(child);
            ObservableList<Formula> childs2 = DbFormulaHandler.getFormulas(child.getId());
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
        return myFormulas;
    }

    public static void setFormulas(ObservableList<Formula> itemsList) {
        formulas = itemsList;
    }

}
