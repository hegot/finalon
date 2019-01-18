package database;

import entities.Sheet;

import java.sql.*;
import java.util.ArrayList;

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
                    "`parentTpl` INTEGER" +
                    ");");

            addSheet(new Sheet(0, "Statement of Financial Position (Balance Sheet)", 0));
            System.out.println("Table " + tableName + " created");
        } else {
            System.out.println("Table " + tableName + " already exists");
        }

    }


    public ArrayList<Sheet> getSheets(int parentTpl) {
        ArrayList<Sheet> Sheets = new ArrayList<Sheet>();
        DbItemHandler itemHandler = new DbItemHandler();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, parentTpl FROM " + tableName + " WHERE parentTpl = " + parentTpl);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Sheets.add(
                        new Sheet(
                                id,
                                resultSet.getString("name"),
                                resultSet.getInt("parentTpl"),
                                itemHandler.getItems(id)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Sheets;
    }


    public int addSheet(Sheet Sheet) throws ClassNotFoundException, SQLException {

        try {
            String[] returnId = {"id"};
            String sql = "INSERT INTO " + tableName + " (`id`, `name`, `parentTpl`) " + "VALUES(NULL, ?, ?)";
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

    public void deleteSheet(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM " + tableName + " WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
