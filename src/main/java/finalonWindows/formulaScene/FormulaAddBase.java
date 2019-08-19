package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;

public class FormulaAddBase {
    protected int biggestId() {
        int id = DbFormulaHandler.getLastId() + 1;
        return id;
    }
}
