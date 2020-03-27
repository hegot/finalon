package database;

import database.formula.DbFormulaHandler;
import database.log.DbLogHandler;
import database.report.DbReportHandler;
import database.setting.DbSettingHandler;
import database.template.DbItemHandler;
import database.template.TemplateCreator;

import java.sql.SQLException;

public class AddDefaultTables {

    public void start() {
        TemplateCreator templateCreator = new TemplateCreator();
        try {
            DbItemHandler.createTable();
            DbFormulaHandler.createTable();
            DbSettingHandler.createTable();
            DbReportHandler.createTable();
            DbLogHandler.createTable();

            if (DbFormulaHandler.isEmpty()) {
                templateCreator.saveTpl();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
