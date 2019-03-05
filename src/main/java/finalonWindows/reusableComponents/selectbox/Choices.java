package finalonWindows.reusableComponents.selectbox;

import database.formula.DbFormulaHandler;
import entities.Formula;
import javafx.collections.ObservableList;

public class Choices {
    public static ObservableList<Formula> getChoices(int parent) {
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        return dbFormula.getFormulas(parent);
    }
}
