package database;

import database.formula.DbFormulaHandler;
import database.formula.FormulaCreator;
import database.setting.DbSettingHandler;
import database.template.DbItemHandler;

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
