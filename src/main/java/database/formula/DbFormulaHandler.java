package database.formula;

import database.Connect;
import database.DbHandlerBase;
import entities.Formula;
import globalReusables.CallTypes;
import globalReusables.StatTrigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbFormulaHandler extends DbHandlerBase {

    private static String tableName = "formulas";


    private static void createTbl() throws SQLException {
        Connect.getConn().createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
                "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "`name` TEXT," +
                "`shortName` TEXT," +
                "`value` TEXT," +
                "`description` TEXT," +
                "`category` TEXT," +
                "`unit` TEXT," +
                "`parent` INTEGER" +
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


    public static ObservableList<Formula> getFormulas(int parent) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName, value, description, category, unit, parent FROM " + tableName + " WHERE parent = " + parent);
            while (resultSet.next()) {
                Formulas.add(
                        new Formula(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("shortName"),
                                resultSet.getString("value"),
                                resultSet.getString("description"),
                                resultSet.getString("category"),
                                resultSet.getString("unit"),
                                resultSet.getInt("parent")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Formulas;
    }

    public static int addDefaultFormula(Formula Formula) throws SQLException {
        try {
            String sql = "INSERT INTO " + tableName + " (`id`, `name`, `shortName`,  `value`, `description`, `category`, `unit`, `parent`) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = Connect.getConn().prepareStatement(sql);
            statement.setObject(1, Formula.getId());
            statement.setObject(2, Formula.getName());
            statement.setObject(3, Formula.getShortName());
            statement.setObject(4, Formula.getValue());
            statement.setObject(5, Formula.getDescription());
            statement.setObject(6, Formula.getCategory());
            statement.setObject(7, Formula.getUnit());
            statement.setObject(8, Formula.getParent());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating formula failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static int addFormula(Formula Formula) {
        try {
            if (Formula.getId() == -1) {
                Formula.setId(getLastId() + 1);
            }
            StatTrigger.call(CallTypes.formula_customization_times);
            String[] returnId = {"id"};
            String sql = "INSERT INTO " + tableName + " (`id`, `name`, `shortName`,  `value`, `description`, `category`, `unit`, `parent`) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = Connect.getConn().prepareStatement(sql, returnId);
            statement.setObject(1, Formula.getId());
            statement.setObject(2, Formula.getName());
            statement.setObject(3, Formula.getShortName());
            statement.setObject(4, Formula.getValue());
            statement.setObject(5, Formula.getDescription());
            statement.setObject(6, Formula.getCategory());
            statement.setObject(7, Formula.getUnit());
            statement.setObject(8, Formula.getParent());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating formula failed, no rows affected.");
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

    public static void updateFormula(Formula Formula) {
        StatTrigger.call(CallTypes.formula_customization_times);
        if (itemExists(Formula.getId(), tableName)) {
            try (PreparedStatement statement = Connect.getConn().prepareStatement(
                    "UPDATE " + tableName + " SET `name` = ?,  `shortName` = ?, `value` = ?, `description` = ?,  `category` = ?, `unit` = ?, `parent` = ? WHERE `id` = " + Formula.getId()
            )) {
                statement.setObject(1, Formula.getName());
                statement.setObject(2, Formula.getShortName());
                statement.setObject(3, Formula.getValue());
                statement.setObject(4, Formula.getDescription());
                statement.setObject(5, Formula.getCategory());
                statement.setObject(6, Formula.getUnit());
                statement.setObject(7, Formula.getParent());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            addFormula(Formula);
        }
    }


    public static void deleteItem(int id) {
        StatTrigger.call(CallTypes.formula_customization_times);
        try (PreparedStatement statement = Connect.getConn().prepareStatement(
                "DELETE FROM " + tableName + " WHERE id = ?")) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean isEmpty() throws SQLException {
        try {
            Statement stmt = Connect.getConn().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
            int rows = rs.getInt(1);
            if (rows > 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
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

    public static Integer findByName(String name) {
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id FROM " + tableName + " WHERE name = " + name);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Formula findTemplate(int id) {
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName, value, description, category, unit, parent FROM "
                    + tableName + " WHERE parent = " + id + " AND category='template'");
            while (resultSet.next()) {
                return new Formula(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("shortName"),
                        resultSet.getString("value"),
                        resultSet.getString("description"),
                        resultSet.getString("category"),
                        resultSet.getString("unit"),
                        resultSet.getInt("parent")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Formula findById(int id) {
        try (Statement statement = Connect.getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, shortName, value, description, category, unit, parent FROM "
                    + tableName + " WHERE id = " + id);
            while (resultSet.next()) {
                return new Formula(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("shortName"),
                        resultSet.getString("value"),
                        resultSet.getString("description"),
                        resultSet.getString("category"),
                        resultSet.getString("unit"),
                        resultSet.getInt("parent")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteTable() throws ClassNotFoundException, SQLException {
        try (PreparedStatement statement = Connect.getConn().prepareStatement(
                "delete from " + tableName + ";"
        )) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement = Connect.getConn().prepareStatement(
                "delete from sqlite_sequence where name='" + tableName + "';"
        )) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
