import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection conn = null;
    private static DBConnection instance = null;
    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:DroneServiceDB.db";

    //constructor
    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL);
    }

    //if first call, creates connection. If not, returns old connection
    public static Connection getConnection() {
        if (instance == null) {
            try {
                instance = new DBConnection();
                DatabaseMetaData meta = conn.getMetaData();
                System.out
                        .println("The driver name is " + meta.getDriverName());
                System.out.println(
                        "The connection to the database was successful.");
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
