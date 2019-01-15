package database;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbItemHandler extends DbHandlerBase {

    private static String tableName;
    private Connection connection;

    public DbItemHandler() {
        Connect connect = Connect.getInstance();
        this.connection = connect.conn;
        tableName = "items";
    }

    public void createTable(int parentSheet) throws ClassNotFoundException, SQLException {
        if (!tableExists(this.connection, tableName)) {
            this.connection.createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                    "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "`name` TEXT," +
                    "`shortName` TEXT," +
                    "`mainCategory` TEXT," +
                    "`subCategory` TEXT," +
                    "`isPositive` INTEGER," +
                    "`parent` INTEGER," +
                    "`parentSheet` INTEGER" +
                    ");");

            System.out.println("Table " + tableName + " created");
        } else {
            System.out.println("Table " + tableName + " already exists");
        }

    }

    public ObservableList<Item> getAllItems() {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName, mainCategory, subCategory, isPositive, parent, parentSheet FROM " + tableName);
            while (resultSet.next()) {
                Items.add(
                        new Item(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("shortName"),
                                resultSet.getString("mainCategory"),
                                resultSet.getString("subCategory"),
                                resultSet.getBoolean("isPositive"),
                                resultSet.getInt("parent"),
                                resultSet.getInt("parentSheet")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Items;
    }


    public void addItem(Item Item) throws ClassNotFoundException, SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO " + tableName + " (`id`, `name`, `shortName`, `mainCategory`, `subCategory`, `isPositive`, `parent`, `parentSheet`) " +
                        "VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, Item.name);
            statement.setObject(2, Item.shortName);
            statement.setObject(3, Item.mainCategory);
            statement.setObject(4, Item.subCategory);
            statement.setObject(5, Item.isPositive);
            statement.setObject(6, Item.parent);
            statement.setObject(7, Item.parentSheet);
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
