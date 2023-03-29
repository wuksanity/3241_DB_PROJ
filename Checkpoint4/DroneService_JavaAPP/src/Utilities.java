import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Utilities {

    public static int displayResults(ResultSet results) throws SQLException {

        ResultSetMetaData rsmd = results.getMetaData();
        int columnCount = rsmd.getColumnCount();
        System.out.print("\n");
        for (int i = 1; i <= columnCount; i++) {
            String value = rsmd.getColumnName(i);
            System.out.print(value);
            if (i < columnCount) {
                System.out.print(",  ");
            }
        }
        System.out.print("\n");
        int count = 0;
        while (results.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String columnValue = results.getString(i);
                System.out.print(columnValue);
                if (i < columnCount) {
                    System.out.print(",  ");
                }
            }
            System.out.print("\n");
            count++;
        }
        System.out.print("\n");

        return count;
    }

}
