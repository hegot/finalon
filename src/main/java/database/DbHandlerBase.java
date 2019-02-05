package database;

import java.sql.*;

public class DbHandlerBase {

    public boolean tableExists(Connection conn, String tableName) {
        try {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean itemExists(int id, String tableName, Connection connection) {
        try {
            String query = "SELECT (count(*) > 0) as found FROM " + tableName + " WHERE WHERE `id` = " + id;
            PreparedStatement pst = connection.prepareStatement(query);
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
