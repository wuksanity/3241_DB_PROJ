import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reports {

    public static void Renting_Checkouts() {

    }

    public static void Popular_Item() {

    }

    public static void Popular_Manufacturer() {

    }

    public static void Popular_Drone() {

    }

    public static void Items_Checked_Out() {

    }

    public static void Equipment_Before_Year() {
        try {
            System.out.print("Enter Manufacturer: ");
            String manufacturer = App.input.nextLine();

            System.out.print("Enter Year: ");
            String year = App.input.nextLine();

            String sql = "Select Name, Serial_number FROM Element WHERE Manufacturer = ? AND Year < ?;";

            PreparedStatement stmt = DBConnection.getConnection()
                    .prepareStatement(sql);

            stmt.setString(1, manufacturer);
            stmt.setString(2, year);

            ResultSet results = stmt.executeQuery();
            Utilities.displayResults(results);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("**DATABASE REPORT FAILED**");
            System.out.println("Error Message: " + e);
        }

        System.out.println("** END OF REPORT **");
        Menus.mainMenu();
    }

}
