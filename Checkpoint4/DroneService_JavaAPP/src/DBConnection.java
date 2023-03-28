import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection conn = null;
    private static DBConnection instance = null;
    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:DroneServiceDB.db";

    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL);
    }

    public static Connection getConnection() {
        if (instance == null) {
            try {
                instance = new DBConnection();
                System.out.println("Connection Successful");
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error: " + e);
            }
        }
        return conn;
    }

    public static void close() {
        try {
            conn.close();
            System.out.println("Database Connection Closed");
        } catch (SQLException s) {
            System.out.println("Error: " + s);
        }
    }

}
