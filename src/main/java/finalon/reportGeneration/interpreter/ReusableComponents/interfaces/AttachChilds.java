package finalon.reportGeneration.interpreter.ReusableComponents.interfaces;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Formula;
import javafx.collections.ObservableList;

public interface AttachChilds {
    default void setFormulaChilds(Formula formula) {
        ObservableList<Formula> childs = DbFormulaHandler.getFormulas(formula.getId());
        formula.setChilds(childs);
    }
}
