package database;

import entities.Template;
import java.sql.*;

public class DbTemplateHandler extends DbHandlerBase{

    private Connection connection;
    private static String tableName;

    public DbTemplateHandler(){
        Connect connect = Connect.getInstance();
        this.connection = connect.conn;
        tableName = "templates";
    }

    public void createTable() throws ClassNotFoundException, SQLException
    {
        if(!tableExists(this.connection, tableName)) {
            this.connection.createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                    "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                    "`name` TEXT" +
                    ");");

            addTemplate(new Template(1, "Default Template"));
            System.out.println("Table " + tableName + " created");
        }else{
            System.out.println("Table " + tableName + " already exists");
        }

    }


    public void addTemplate(Template Template) throws ClassNotFoundException, SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO " + tableName + " (`id`, `name`) " +
                        "VALUES(NULL, ?)")) {
            statement.setObject(1, Template.name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
