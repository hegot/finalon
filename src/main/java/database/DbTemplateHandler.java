package database;

import entities.Template;

import java.sql.*;
import java.util.ArrayList;

public class DbTemplateHandler extends DbHandlerBase {

    private static String tableName;
    private Connection connection;

    public DbTemplateHandler() {
        Connect connect = Connect.getInstance();
        this.connection = connect.conn;
        tableName = "templates";
    }

    public void createTable() throws ClassNotFoundException, SQLException {
        if (!tableExists(this.connection, tableName)) {
            this.connection.createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                    "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                    "`name` TEXT" +
                    ");");
            System.out.println("Table " + tableName + " created");
        } else {
            System.out.println("Table " + tableName + " already exists");
        }

    }

    public ArrayList<Template> getTemplates() {
        ArrayList<Template> templates = new ArrayList<Template>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name FROM " + tableName);
            while (resultSet.next()) {
                templates.add(
                        new Template(
                                resultSet.getInt("id"),
                                resultSet.getString("name")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return templates;
    }


    public int addTemplate(Template Template) throws ClassNotFoundException, SQLException {
        try {
            String[] returnId = {"id"};
            String sql = "INSERT INTO " + tableName + " (`id`, `name`) " +
                    "VALUES(NULL, ?)";
            PreparedStatement statement = this.connection.prepareStatement(sql, returnId);
            statement.setObject(1, Template.name);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating template failed, no rows affected.");
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
