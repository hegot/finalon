package database;

import java.sql.Connection;
import java.sql.SQLException;

public class AddDefaultTpl {

    public void start(){

        DbTemplateHandler template = new DbTemplateHandler();
        DbSheetHandler sheet = new DbSheetHandler();
        DbItemHandler item = new DbItemHandler();
        try{
            template.createTable();
            sheet.createTable();
            item.createTable(0);
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
