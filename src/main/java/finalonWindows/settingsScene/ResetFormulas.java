package finalonWindows.settingsScene;

import database.formula.DbFormulaHandler;
import database.formula.FormulaCreator;
import globalReusables.BaseRun;
import globalReusables.ThreadWrapper;

import java.sql.SQLException;

class ResetFormulas {
    void reset() {
        ThreadWrapper threadWrapper = new ThreadWrapper(new DeleteFormulas());
        threadWrapper.go();
        ThreadWrapper threadWrapper2 = new ThreadWrapper(new CreateFormulas());
        threadWrapper2.go();
    }
}

class CreateFormulas implements BaseRun {

    public void runThread() {
        FormulaCreator formulaCreator = new FormulaCreator();
        try {
            DbFormulaHandler.createTable();
            if (DbFormulaHandler.isEmpty()) {
                formulaCreator.createFormulas();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean shouldWait() {
        return false;
    }
}

class DeleteFormulas implements BaseRun {

    public void runThread() {
        try {
            DbFormulaHandler.deleteTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean shouldWait() {
        return true;
    }
}