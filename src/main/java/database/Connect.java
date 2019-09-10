package database;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connect {

    private static Connection conn;
    private boolean initalized = false;

    private Connect() {
        if (!initalized) {
            try {
                init();
            } catch (Exception e) {
                System.out.println("Could not init JDBC driver - driver not found");
            }
            initalized = true;
        }
    }

    public static Connect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static Connection getConn() {
        return conn;
    }

    public static void closeConnection() throws ClassNotFoundException, SQLException {
        conn.close();
        System.out.println("Connection closed");
    }

    private void init() throws ClassNotFoundException, SQLException {
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException eString) {
            System.out.println("Could not init JDBC driver - driver not found");
        }

        String filepath = "";
        try {
            filepath = new File(".").getCanonicalPath();
            filepath = filepath + "/src/main/resources/db/finalon_templates.sqlite";
            Path realpath = Paths.get(filepath);
            if (!Files.exists(realpath)) {
                File file = new File(filepath);
                file.createNewFile();
            }
        } catch (Exception eString) {
            System.out.println("Could not create db file");
        }

        conn = DriverManager.getConnection("jdbc:sqlite::resource:" + getClass().getResource("/db/finalon_templates.sqlite"));
        System.out.println("Database connected!");
    }

    private static class SingletonHolder {
        public static final Connect INSTANCE = new Connect();
    }
}