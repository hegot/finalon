package database;

import java.sql.SQLException;

public class AddDefaultTpl {

    public void start() {
        DbItemHandler item = new DbItemHandler();
        try {
            item.createTable(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
