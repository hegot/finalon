package database;

import entities.Sheet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbSheetHandler extends DbHandlerBase {

    private static String tableName;
    private Connection connection;

    public DbSheetHandler() {
        Connect connect = Connect.getInstance();
        this.connection = connect.conn;
        tableName = "sheets";
    }

    public void createTable() throws ClassNotFoundException, SQLException {
        if (!tableExists(this.connection, tableName)) {
            this.connection.createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                    "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                    "`name` TEXT," +
                    "`parentSheet` INTEGER" +
                    ");");

            addSheet(new Sheet(0, "Statement of Financial Position (Balance Sheet)", 0));
            System.out.println("Table " + tableName + " created");
        } else {
            System.out.println("Table " + tableName + " already exists");
        }

    }


    public int addSheet(Sheet Sheet) throws ClassNotFoundException, SQLException {

        try {
            String[] returnId = {"id"};
            String sql = "INSERT INTO " + tableName + " (`id`, `name`, `parentSheet`) " + "VALUES(NULL, ?, ?)";
            PreparedStatement statement = this.connection.prepareStatement(sql, returnId);
            statement.setObject(1, Sheet.name);
            statement.setObject(2, Sheet.parentTpl);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating sheet failed, no rows affected.");
            }
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    rs.close();
                    return id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteItem(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM " + tableName + " WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
