package database.template;

import database.Connect;
import database.DbHandlerBase;
import defaultData.DefaultTemplate;
import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;

public class DbItemHandler extends DbHandlerBase {

    private static String tableName = "items";


    private static void createTbl() throws SQLException {
        Connect.getConn().createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "`name` TEXT," +
                "`shortName` TEXT," +
                "`isPositive` INTEGER," +
                "`finResult` INTEGER," +
                "`parent` INTEGER," +
                "`level` INTEGER," +
                "`weight` INTEGER" +
                ");");

        System.out.println("Table " + tableName + " created");
    }


    public static void createTable() throws ClassNotFoundException, SQLException {
        if (!tableExists(tableName)) {
            createTbl();
        } else {
            System.out.println("Table " + tableName + " already exists");
        }
    }

    public static ObservableList<Item> getItems(int parent) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName,  isPositive, finResult, parent, level, weight FROM "
                    + tableName + " WHERE parent = " + parent);
            while (resultSet.next()) {
                Items.add(
                        new Item(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("shortName"),
                                resultSet.getBoolean("isPositive"),
                                resultSet.getBoolean("finResult"),
                                resultSet.getInt("parent"),
                                resultSet.getInt("level"),
                                resultSet.getInt("weight")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Items;
    }

    public static Item getItem(int id) {
        Item item = new Item(0, "", "", false, false, 0, 0, 0);
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName,  isPositive, finResult, parent, level, weight FROM "
                    + tableName + " WHERE id = " + id);
            while (resultSet.next()) {
                item = new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("shortName"),
                        resultSet.getBoolean("isPositive"),
                        resultSet.getBoolean("finResult"),
                        resultSet.getInt("parent"),
                        resultSet.getInt("level"),
                        resultSet.getInt("weight")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }


    public static ObservableList<Item> getTemplates() {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName,  isPositive, finResult, parent, level, weight FROM "
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
                                resultSet.getInt("level"),
                                resultSet.getInt("weight")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Items;
    }


    public static int getLastId() {
        try {
            Statement stmt = Connect.getConn().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM " + tableName);
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int addItem(Item Item) {
        try {
            String[] returnId = {"id"};
            String sql = "INSERT INTO " + tableName + " (`id`, `name`, `shortName`,  `isPositive`, `finResult`, `parent`, `level`, `weight`) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = Connect.getConn().prepareStatement(sql, returnId);
            statement.setObject(1, Item.getId());
            statement.setObject(2, Item.getName());
            statement.setObject(3, Item.getShortName());
            statement.setObject(4, Item.getIsPositive());
            statement.setObject(5, Item.getFinResult());
            statement.setObject(6, Item.getParent());
            statement.setObject(7, Item.getLevel());
            statement.setObject(8, Item.getWeight());
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

    public static void updateItem(Item Item) throws ClassNotFoundException, SQLException {
        if (itemExists(Item.getId())) {
            try (PreparedStatement statement = Connect.getConn().prepareStatement(
                    "UPDATE " + tableName + " SET `name` = ?,  `shortName` = ?, `isPositive` = ?, `finResult` = ?, `parent` = ?,  `level` = ?,  `weight` = ? WHERE `id` = " + Item.getId()
            )) {
                statement.setObject(1, Item.getName());
                statement.setObject(2, Item.getShortName());
                statement.setObject(3, Item.getIsPositive());
                statement.setObject(4, Item.getFinResult());
                statement.setObject(5, Item.getParent());
                statement.setObject(6, Item.getLevel());
                statement.setObject(7, Item.getWeight());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            addItem(Item);
        }
    }

    public static Boolean itemExists(Integer id) {
        if (id != null) {
            try {
                String query = "SELECT (count(*) > 0) as found FROM " + tableName + " WHERE id=" + id;
                PreparedStatement pst = Connect.getConn().prepareStatement(query);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return rs.getBoolean(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void deleteItem(int id) {
        try (PreparedStatement statement = Connect.getConn().prepareStatement(
                "DELETE FROM " + tableName + " WHERE id = ?")) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> parentIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id FROM " + tableName + " WHERE parent = 0");
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                Statement statement2 = Connect.getConn().createStatement();
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


    public static TreeSet getCodes() {
        TreeSet entries = new TreeSet<String>();
        ArrayList<Integer> parents = parentIds();
        if (parents.size() > 0) {
            String list = "";
            for (int id : parents) {
                list += id + ", ";
            }
            list = list.substring(0, list.length() - 2);
            try (Statement statement = Connect.getConn().createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT shortName FROM " + tableName + " WHERE id NOT IN (" + list + ")");
                while (resultSet.next()) {
                    entries.add(resultSet.getString("shortName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (entries.size() == 0) {
            ObservableList<Item> items = DefaultTemplate.getTpl();
            for (Item item : items) {
                entries.add(item.getShortName());
            }
        }

        return entries;
    }

}
