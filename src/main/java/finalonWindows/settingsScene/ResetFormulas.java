package finalonWindows.settingsScene;

import database.formula.DbFormulaHandler;
import database.formula.FormulaCreator;

import java.sql.SQLException;

class ResetFormulas {
    static void reset() {
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    dbFormula.deleteTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                FormulaCreator formulaCreator = new FormulaCreator();
                try {
                    dbFormula.createTable();
                    if (dbFormula.isEmpty()) {
                        formulaCreator.createFormulas();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
    }
}
