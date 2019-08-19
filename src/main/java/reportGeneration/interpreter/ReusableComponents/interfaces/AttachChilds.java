package reportGeneration.interpreter.ReusableComponents.interfaces;

import database.formula.DbFormulaHandler;
import entities.Formula;
import javafx.collections.ObservableList;

public interface AttachChilds {
    default void setFormulaChilds(Formula formula) {
        ObservableList<Formula> childs = DbFormulaHandler.getFormulas(formula.getId());
        formula.setChilds(childs);
    }
}
