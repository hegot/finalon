package database.formula;

import entities.Formula;
import javafx.collections.ObservableList;

import java.sql.SQLException;

class FormulaBase {
    private ObservableList<Formula> formulas;

    FormulaBase(
            ObservableList<Formula> formulas

    ) {
        this.formulas = formulas;
    }

    int createFormula(Formula formula) {
        try {
            DbFormulaHandler formulaCreator = new DbFormulaHandler();
            return formulaCreator.addFormula(formula);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }
}
