package finalonWindows.templateScene.FormulaEditPage.IndustryOperations;

import database.formula.DbFormulaHandler;

public class FormulaAddBase {
    protected int biggestId() {
        int id = DbFormulaHandler.getLastId() + 1;
        return id;
    }
}
