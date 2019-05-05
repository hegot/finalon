package database.formula;

import database.Connect;
import database.DbHandlerBase;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbFormulaHandler extends DbHandlerBase {

    private static String tableName;
    private Connection connection;

    public DbFormulaHandler() {
        Connect connect = Connect.getInstance();
        this.connection = connect.conn;
        tableName = "formulas";
    }

    private void createTbl() throws SQLException {
        this.connection.createStatement().execute("CREATE TABLE if not exists `" + tableName + "` (" +
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

    public void createTable() throws ClassNotFoundException, SQLException {
        if (!tableExists(this.connection, tableName)) {
            createTbl();
        } else {
            System.out.println("Table " + tableName + " already exists");
        }
    }

    public ObservableList<Formula> getFormulas(int parent) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        try (Statement statement = this.connection.createStatement()) {
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

    public int addDefaultFormula(Formula Formula) throws SQLException {
        try {
            String sql = "INSERT INTO " + tableName + " (`id`, `name`, `shortName`,  `value`, `description`, `category`, `unit`, `parent`) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
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


    public int addFormula(Formula Formula) throws SQLException {
        try {
            String[] returnId = {"id"};
            String sql = "INSERT INTO " + tableName + " (`id`, `name`, `shortName`,  `value`, `description`, `category`, `unit`, `parent`) " +
                    "VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.connection.prepareStatement(sql, returnId);
            statement.setObject(1, Formula.getName());
            statement.setObject(2, Formula.getShortName());
            statement.setObject(3, Formula.getValue());
            statement.setObject(4, Formula.getDescription());
            statement.setObject(5, Formula.getCategory());
            statement.setObject(6, Formula.getUnit());
            statement.setObject(7, Formula.getParent());
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

    public void updateFormula(Formula Formula) throws SQLException {
        if (itemExists(Formula.getId(), tableName, this.connection)) {
            try (PreparedStatement statement = this.connection.prepareStatement(
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


    public void deleteItem(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM " + tableName + " WHERE id = ?")) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isEmpty() throws SQLException {
        try {
            Statement stmt = this.connection.createStatement();
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

    public int getLastId() {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM " + tableName);
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer findByName(String name) {
        try (Statement statement = this.connection.createStatement()) {
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

}
