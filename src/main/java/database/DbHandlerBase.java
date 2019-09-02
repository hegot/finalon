package database;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbHandlerBase {

    public static boolean tableExists(String tableName) {
        try {
            DatabaseMetaData md = Connect.getConn().getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean itemExists(int id, String tableName) {
        try {
            String query = "SELECT (count(*) > 0) as found FROM " + tableName + " WHERE `id` = " + id;
            PreparedStatement pst = Connect.getConn().prepareStatement(query);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
