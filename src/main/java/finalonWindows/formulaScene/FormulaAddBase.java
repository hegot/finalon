package finalonWindows.formulaScene;

import database.formula.DbFormulaHandler;

public class FormulaAddBase {
    protected int biggestId() {
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        int id = dbFormula.getLastId();
        int biggestId = EditStorage.getBiggestId();
        if (biggestId >= id) {
            id = biggestId + 1;
        }
        return id;
    }
}
