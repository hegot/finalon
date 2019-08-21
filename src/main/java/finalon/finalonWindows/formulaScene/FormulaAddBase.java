package finalon.finalonWindows.formulaScene;

import finalon.database.formula.DbFormulaHandler;

public class FormulaAddBase {
    protected int biggestId() {
        int id = DbFormulaHandler.getLastId() + 1;
        return id;
    }
}
