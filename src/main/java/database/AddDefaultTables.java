package database;

import database.formula.DbFormulaHandler;
import database.formula.FormulaCreator;
import database.template.DbItemHandler;

import java.sql.SQLException;

public class AddDefaultTables {

    public void start() {
        DbItemHandler item = new DbItemHandler();
        DbFormulaHandler formulasTable = new DbFormulaHandler();
        FormulaCreator formulaCreator = new FormulaCreator();
        try {
            item.createTable();
            formulasTable.createTable();
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
