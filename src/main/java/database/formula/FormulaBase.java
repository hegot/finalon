package database.formula;

import entities.Formula;

import java.sql.SQLException;

class FormulaBase {
    int createFormula(Formula formula) {
        try {
            return DbFormulaHandler.addFormula(formula);
        } catch (Exception se) {
            se.printStackTrace();
        }
        return 0;
    }
}
