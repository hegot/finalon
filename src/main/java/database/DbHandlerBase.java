package database;

import java.sql.*;

public class DbHandlerBase {

    public static boolean tableExists(String tableName) {
        try {
            Connection conn = Connect.getConn();
            if(conn != null){
                DatabaseMetaData md = Connect.getConn().getMetaData();
                ResultSet rs = md.getTables(null, null, tableName, null);
                if (rs.next()) {
                    return true;
                }
            }else{
                Statement statement = Connect.getConn().createStatement();
                int myResult = statement.executeUpdate("CREATE DATABASE finalon_templates");
            }
        } catch (SQLException e) {
           // e.printStackTrace();
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
