package finalonWindows.settingsScene;

import database.formula.DbFormulaHandler;
import database.formula.FormulaCreator;

import java.sql.SQLException;

public class ResetFormulas {
    public static void reset(){
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        FormulaCreator formulaCreator = new FormulaCreator();
        try{
            dbFormula.clearTable();
            if (dbFormula.isEmpty()) {
                formulaCreator.createFormulas();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
