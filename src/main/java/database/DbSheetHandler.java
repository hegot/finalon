package database;

import entities.Sheet;
import java.sql.*;

public class DbSheetHandler extends DbHandlerBase{

    private Connection connection;
    private static String tableName;

    public DbSheetHandler(){
        Connect connect = Connect.getInstance();
        this.connection = connect.conn;
        tableName = "sheets";
    }

    public void createTable() throws ClassNotFoundException, SQLException
    {
        if(!tableExists(this.connection, tableName)) {
            this.connection.createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                    "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                    "`name` TEXT," +
                    "`parentSheet` INTEGER" +
                    ");");

            addSheet(new Sheet(0, "Statement of Financial Position (Balance Sheet)", 0));
            System.out.println("Table " + tableName + " created");
        }else{
            System.out.println("Table " + tableName + " already exists");
        }

    }


    public void addSheet(Sheet Sheet) throws ClassNotFoundException, SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO " + tableName + " (`id`, `name`, `parentSheet`) " +
                        "VALUES(NULL, ?, ?)")) {
            statement.setObject(1, Sheet.name);
            statement.setObject(2, Sheet.parentTpl);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM "  + tableName + " WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
