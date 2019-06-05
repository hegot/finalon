package reportGeneration.interpreter.ReusableComponents.interfaces;

import database.formula.DbFormulaHandler;
import entities.Formula;
import javafx.collections.ObservableList;

public interface AttachChilds {
    default void setFormulaChilds(Formula formula) {
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        ObservableList<Formula> childs = dbFormula.getFormulas(formula.getId());
        formula.setChilds(childs);
    }
}
