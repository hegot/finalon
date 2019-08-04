package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;

public class FormulaAddBase {
    protected int biggestId() {
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        int id = dbFormula.getLastId() + 1;
        return id;
    }
}
