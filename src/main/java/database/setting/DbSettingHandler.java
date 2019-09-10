package database.setting;

import database.Connect;
import database.DbHandlerBase;
import globalReusables.RandomString;
import globalReusables.Setting;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSettingHandler extends DbHandlerBase {

    private static String tableName = "settings";

    private static void createTbl() throws SQLException {
        Connect.getConn().createStatement().execute("CREATE TABLE if not exists " + tableName + " (`key` TEXT, `value` TEXT);");

        System.out.println("Table " + tableName + " created");
        String sql = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('numberFormat', 'default')";
        Connect.getConn().prepareStatement(sql).executeUpdate();
        String sql2 = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('yearOrder', 'ASCENDING')";
        Connect.getConn().prepareStatement(sql2).executeUpdate();
        String sql3 = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('includeAll', 'YES')";
        Connect.getConn().prepareStatement(sql3).executeUpdate();
        String sql4 = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('defaultCurrency', 'USD')";
        Connect.getConn().prepareStatement(sql4).executeUpdate();
        String sql5 = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('appId', '" + RandomString.get() + "')";
        Connect.getConn().prepareStatement(sql5).executeUpdate();
        String sql6 = "INSERT INTO " + tableName + " (`key`, `value`) VALUES('blocked', 'false')";
        Connect.getConn().prepareStatement(sql6).executeUpdate();
    }

    public static void createTable() throws ClassNotFoundException, SQLException {
        if (!tableExists(tableName)) {
            createTbl();
        } else {
            System.out.println("Table " + tableName + " already exists");
        }
    }


    public static String getSetting(Setting key) {
        String value = "";
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT key, value FROM " + tableName + " WHERE key = '" + key.toString() + "'");
            while (resultSet.next()) {
                value = resultSet.getString("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void updateSetting(Setting key, String value) {
        try (PreparedStatement statement = Connect.getConn().prepareStatement(
                "UPDATE " + tableName + " SET `value` = ? WHERE key = '" + key.toString() + "'"
        )) {
            statement.setObject(1, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createSetting(Setting key, String value) {
        try {
            String sql = "INSERT INTO " + tableName + " (`key`, `value`) VALUES ('" + key.toString() + "', '" + value + "')";
            Connect.getConn().prepareStatement(sql).executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
