package database;

import database.formula.DbFormulaHandler;
import database.formula.FormulaCreator;
import database.setting.DbSettingHandler;
import database.template.DbItemHandler;

import java.sql.SQLException;

public class AddDefaultTables {

    public void start() {
        DbItemHandler item = new DbItemHandler();
        DbFormulaHandler formulasTable = new DbFormulaHandler();
        FormulaCreator formulaCreator = new FormulaCreator();
        DbSettingHandler dbSettingHandler = new DbSettingHandler();
        try {
            item.createTable();
            formulasTable.createTable();
            dbSettingHandler.createTable();
            if (formulasTable.isEmpty()) {
                formulaCreator.createFormulas();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
