package database;

import entities.Item;
import entities.Sheet;
import entities.Template;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

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
            System.out.println("Table " + tableName + " created");
        }else{
            System.out.println("Table " + tableName + " already exists");
        }

    }

    public  ArrayList<Template> getTemplates() {
        ArrayList<Template> templates = new ArrayList<Template>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name FROM " + tableName );
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
