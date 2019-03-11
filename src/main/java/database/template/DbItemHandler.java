package database.template;

import database.Connect;
import database.DbHandlerBase;
import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class DbItemHandler extends DbHandlerBase {

    private static String tableName;
    private Connection connection;

    public DbItemHandler() {
        Connect connect = Connect.getInstance();
        this.connection = connect.conn;
        tableName = "items";
    }

    private void createTbl() throws SQLException {
        this.connection.createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "`name` TEXT," +
                "`shortName` TEXT," +
                "`isPositive` INTEGER," +
                "`finResult` INTEGER," +
                "`parent` INTEGER," +
                "`parentSheet` INTEGER" +
                ");");

        System.out.println("Table " + tableName + " created");
    }

    private void addCol() throws SQLException {
        this.connection.createStatement().executeUpdate("ALTER TABLE " + tableName + " ADD `finResult` INTEGER");
        System.out.println("Table " + tableName + " updated");
    }


    public void createTable() throws ClassNotFoundException, SQLException {
        if (!tableExists(this.connection, tableName)) {
            createTbl();
        } else {
            System.out.println("Table " + tableName + " already exists");
        }
        DatabaseMetaData md = this.connection.getMetaData();
        ResultSet rs = md.getColumns(null, null, tableName, "finResult");
        if (!rs.next()) {
            addCol();
            createTbl();
        }
    }

    public ObservableList<Item> getItems(int parent) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName,  isPositive, finResult, parent, parentSheet FROM "
                    + tableName + " WHERE parentSheet = " + parent);
            while (resultSet.next()) {
                Items.add(
                        new Item(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("shortName"),
                                resultSet.getBoolean("isPositive"),
                                resultSet.getBoolean("finResult"),
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
    public Item getItem(int id) {
        Item item = new Item(0, "", "" ,false, false, 0, 0);
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName,  isPositive, finResult, parent, parentSheet FROM "
                    + tableName + " WHERE id = " + id);
            while (resultSet.next()) {
               item = new Item(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("shortName"),
                                resultSet.getBoolean("isPositive"),
                                resultSet.getBoolean("finResult"),
                                resultSet.getInt("parent"),
                                resultSet.getInt("parentSheet")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }


    public ObservableList<Item> getTemplates() {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName,  isPositive, finResult, parent, parentSheet FROM "
                    + tableName + " WHERE parent = 0");
            while (resultSet.next()) {
                Items.add(
                        new Item(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("shortName"),
                                resultSet.getBoolean("isPositive"),
                                resultSet.getBoolean("finResult"),
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

    int addItem(Item Item) throws ClassNotFoundException, SQLException {
        try {
            String[] returnId = {"id"};
            String sql = "INSERT INTO " + tableName + " (`id`, `name`, `shortName`,  `isPositive`, `finResult`, `parent`, `parentSheet`) " +
                    "VALUES(NULL, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.connection.prepareStatement(sql, returnId);
            statement.setObject(1, Item.getName());
            statement.setObject(2, Item.getShortName());
            statement.setObject(3, Item.getIsPositive());
            statement.setObject(4, Item.getFinResult());
            statement.setObject(5, Item.getParent());
            statement.setObject(6, Item.getParentSheet());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating item failed, no rows affected.");
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

    void updateItem(Item Item) throws ClassNotFoundException, SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE " + tableName + " SET `name` = ?,  `shortName` = ?, `isPositive` = ?, `finResult` = ?, `parent` = ?, `parentSheet` = ? WHERE `id` = " + Item.getId()
        )) {
            statement.setObject(1, Item.getName());
            statement.setObject(2, Item.getShortName());
            statement.setObject(3, Item.getIsPositive());
            statement.setObject(4, Item.getFinResult());
            statement.setObject(5, Item.getParent());
            statement.setObject(6, Item.getParentSheet());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean itemExists(int id) {
        try {
            String query = "SELECT (count(*) > 0) as found FROM " + tableName + " WHERE WHERE `id` = " + id;
            PreparedStatement pst = this.connection.prepareStatement(query);
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

    void deleteItem(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM " + tableName + " WHERE id = ?")) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Integer> parentIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id FROM " + tableName + " WHERE parent = 0");
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                Statement statement2 = this.connection.createStatement();
                ResultSet resultSet2 = statement2.executeQuery("SELECT id FROM " + tableName + " WHERE parent = " + Id);
                ids.add(Id);
                while (resultSet2.next()) {
                    ids.add(resultSet2.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public TreeSet getCodes() {
        TreeSet entries = new TreeSet<String>();
        ArrayList<Integer> parents = parentIds();
        String list = "";
        for (int id : parents) {
            list += id + ", ";
        }
        list = list.substring(0, list.length() - 2);
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT shortName FROM " + tableName + " WHERE id NOT IN (" + list + ")");
            while (resultSet.next()) {
                entries.add(resultSet.getString("shortName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

}
