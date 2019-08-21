package finalon.database.formula;

import finalon.entities.Formula;

import java.sql.SQLException;

class FormulaBase {
    int createFormula(Formula formula) {
        try {
            return DbFormulaHandler.addFormula(formula);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }
}
