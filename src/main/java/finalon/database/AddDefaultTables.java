package finalon.database;

import finalon.database.formula.DbFormulaHandler;
import finalon.database.formula.FormulaCreator;
import finalon.database.setting.DbSettingHandler;
import finalon.database.template.DbItemHandler;

import java.sql.SQLException;

public class AddDefaultTables {

    public void start() {
        FormulaCreator formulaCreator = new FormulaCreator();
        try {
            DbItemHandler.createTable();
            DbFormulaHandler.createTable();
            DbSettingHandler.createTable();
            if (DbFormulaHandler.isEmpty()) {
                formulaCreator.createFormulas();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
