package database.log;

import database.Connect;
import database.DbHandlerBase;
import entities.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbLogHandler extends DbHandlerBase {
    private static String tableName = "log";


    private static void createTbl() throws SQLException {
        Connect.getConn().createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "`date` TEXT," +
                "`message` TEXT);");

        System.out.println("Table " + tableName + " created");
    }


    public static void createTable() throws ClassNotFoundException, SQLException {
        if (!tableExists(tableName)) {
            createTbl();
        } else {
            String sql = "DELETE FROM " + tableName + ";";
            PreparedStatement statement = Connect.getConn().prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Table " + tableName + " already exists");
        }
    }

    public static int addLog(Log log) {
        try {
            String[] returnId = {"id"};
            String sql = "INSERT INTO " + tableName + " (`id`, `date`, `message`) " +
                    "VALUES(NULL, ?, ?)";
            PreparedStatement statement = Connect.getConn().prepareStatement(sql, returnId);
            statement.setObject(1, log.getDate());
            statement.setObject(2, log.getMessage());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating log failed, no rows affected.");
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

    public static ObservableList<Log> getLogs() {
        ObservableList<Log> logs = FXCollections.observableArrayList();
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, date, message FROM "
                    + tableName + " order by `date` DESC limit 100;");
            while (resultSet.next()) {
                logs.add(
                        new Log(
                                resultSet.getInt("id"),
                                resultSet.getString("date"),
                                resultSet.getString("message")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }
}
