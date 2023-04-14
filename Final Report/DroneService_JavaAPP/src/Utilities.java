import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static String getDateTime() {
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter
                .ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateObj.format(formatDate);
        return formattedDate;
    }

}
