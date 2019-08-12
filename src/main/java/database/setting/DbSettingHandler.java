package database.setting;

import database.Connect;
import database.DbHandlerBase;
import globalReusables.RandomString;
import globalReusables.Setting;

import java.sql.*;

public class DbSettingHandler extends DbHandlerBase {

    private static String tableName;
    private Connection connection;

    public DbSettingHandler() {
        this.connection = Connect.conn;
        tableName = "settings";
    }

    private void createTbl() throws SQLException {
        this.connection.createStatement().execute("CREATE TABLE if not exists " + tableName + " (`key` TEXT, `value` TEXT);");

        System.out.println("Table " + tableName + " created");
        String sql = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('numberFormat', 'default')";
        this.connection.prepareStatement(sql).executeUpdate();
        String sql2 = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('yearOrder', 'ASCENDING')";
        this.connection.prepareStatement(sql2).executeUpdate();
        String sql3 = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('includeAll', 'YES')";
        this.connection.prepareStatement(sql3).executeUpdate();
        String sql4 = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('defaultCurrency', 'USD')";
        this.connection.prepareStatement(sql4).executeUpdate();
        String sql5 = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('appId', '" + RandomString.get() + "')";
        this.connection.prepareStatement(sql5).executeUpdate();
    }

    public void createTable() throws ClassNotFoundException, SQLException {
        if (!tableExists(this.connection, tableName)) {
            createTbl();
        } else {
            System.out.println("Table " + tableName + " already exists");
        }
    }


    public String getSetting(Setting key) {
        String value = "";
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT key, value FROM " + tableName + " WHERE key = '" + key.toString() + "'");
            while (resultSet.next()) {
                value = resultSet.getString("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void updateSetting(Setting key, String value) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE " + tableName + " SET `value` = ? WHERE key = '" + key.toString() + "'"
        )) {
            statement.setObject(1, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
