package database.report;

import database.Connect;
import database.DbHandlerBase;
import entities.Item;
import entities.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbReportHandler extends DbHandlerBase {
    private static String tableName = "reports";

    private static void createTbl() throws SQLException {
        Connect.getConn().createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "`name` TEXT," +
                "`settings` TEXT," +
                "`items` TEXT," +
                "`updated` TEXT" +
                ");");

        System.out.println("Table " + tableName + " created");
    }

    public static void createTable() throws ClassNotFoundException, SQLException {
        if (!tableExists(tableName)) {
            createTbl();
        } else {
            System.out.println("Table " + tableName + " already exists");
        }
    }


    public static int addReport(Report report) {
        try {
            String[] returnId = {"id"};
            String sql = "INSERT INTO " + tableName + " (`id`, `name`, `settings`, `items`, `updated`) " +
                    "VALUES(NULL, ?, ?, ?, ?)";
            PreparedStatement statement = Connect.getConn().prepareStatement(sql, returnId);
            statement.setObject(1, report.getName());
            statement.setObject(2, report.getSettings());
            statement.setObject(3, report.getItems());
            statement.setObject(4, report.getUpdated());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating report failed, no rows affected.");
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

    public static void updateReport(Report report) throws SQLException {
        if (itemExists(report.getId(), tableName)) {
            try (PreparedStatement statement = Connect.getConn().prepareStatement(
                    "UPDATE " + tableName + " SET `name` = ?,  `settings` = ?, `items` = ?, `updated` = ? WHERE `id` = " + report.getId()
            )) {
                statement.setObject(1, report.getName());
                statement.setObject(2, report.getSettings());
                statement.setObject(3, report.getItems());
                statement.setObject(4, report.getUpdated());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            addReport(report);
        }
    }

    public static void deleteItem(int id) {
        try (PreparedStatement statement = Connect.getConn().prepareStatement(
                "DELETE FROM " + tableName + " WHERE id = ?")) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Report getItem(int id) {
        Report item = new Report(0, "", "", "", "");
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT `id`, `name`, `settings`, `items`, `updated` FROM "
                    + tableName + " WHERE id = " + id);
            while (resultSet.next()) {
                item = new Report(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("settings"),
                        resultSet.getString("items"),
                        resultSet.getString("updated")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
    public static ObservableList<Report> getReports() {
        ObservableList<Report> items = FXCollections.observableArrayList();
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT `id`, `name`, `settings`, `items`, `updated` FROM "
                    + tableName);
            while (resultSet.next()) {
                items.add(
                        new Report(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("settings"),
                                resultSet.getString("items"),
                                resultSet.getString("updated")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
