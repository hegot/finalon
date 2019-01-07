package database;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbItemHandler extends DbHandlerBase{

    private Connection connection;
    private static String tableName;

    public DbItemHandler(){
        Connect connect = Connect.getInstance();
        this.connection = connect.conn;
        tableName = "items";
    }

    public void createTable(int parentSheet) throws ClassNotFoundException, SQLException
    {
        if(!tableExists(this.connection, tableName)) {
            this.connection.createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                    "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "`name` TEXT," +
                    "`shortName` TEXT," +
                    "`isPositive` INTEGER," +
                    "`parent` INTEGER," +
                    "`parentSheet` INTEGER" +
                    ");");

            addItem(new Item(0, "Property, plant and equipment", "PropertyPlantAndEquipment", true, 0, 0));
            addItem(new Item(0, "Investment property", "Investmentproperty", true, 0, 0));
            addItem(new Item(0, "Goodwill", "Goodwill", true, 0, 0));
            addItem(new Item(0, "Intangible assets other than goodwill", "IntangibleAssetsOtherThanGoodwill ", true, 0, 0));
            addItem(new Item(0, "Investment accounted for using equity method", "InvestmentAccountedForUsingEquityMethod ", true, 0, 0));
            System.out.println("Table " + tableName + " created");
        }else{
            System.out.println("Table " + tableName + " already exists");
        }

    }

    public ObservableList<Item> getAllItems() {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName, isPositive, parent, parentSheet FROM " + tableName );
            while (resultSet.next()) {
                Items.add(
                    new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("shortName"),
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
                "INSERT INTO " + tableName + " (`id`, `name`, `shortName`, `isPositive`, `parent`, `parentSheet`) " +
                        "VALUES(NULL, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, Item.name);
            statement.setObject(2, Item.shortName);
            statement.setObject(3, Item.isPositive);
            statement.setObject(4, Item.parent);
            statement.setObject(5, Item.parentSheet);
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
