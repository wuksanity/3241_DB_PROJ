import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reports {

    public static void Renting_Checkouts() {
        try {
            System.out.print("Enter Phone number of member(###-###-####): ");
            String phone = App.input.nextLine();

            String sql = "Select First_name, Last_name, Phone, COUNT(Serial_num) as Total_Items_Rented\n"
                    + "FROM (Rental JOIN Rental_Equipment ON Rental_number = Rental_num) JOIN PERSON USING (Phone)\n"
                    + "WHERE Phone = ?;";

            PreparedStatement stmt = DBConnection.getConnection()
                    .prepareStatement(sql);

            stmt.setString(1, phone);

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

    public static void Popular_Item() {
        try {

            String sql = "SELECT Serial_number, Name, Manufacturer, SUM(Time) as Total_rented_days, Count(Rental_number) as Total_Rentals\n"
                    + "FROM Element JOIN (\n"
                    + "    Select Serial_num as Serial_number, Time, Rental_number\n"
                    + "    FROM Rental_Equipment as R JOIN (\n"
                    + "    SELECT Rental_number, julianday(Due_date) - julianday(Checkout_date_time) as Time\n"
                    + "    FROM RENTAL) as e ON R.Rental_num = e.Rental_number )\n"
                    + "    USING(Serial_number)\n"
                    + "GROUP BY (Serial_number)\n"
                    + "ORDER BY Total_rented_days desc;";

            PreparedStatement stmt = DBConnection.getConnection()
                    .prepareStatement(sql);

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

    public static void Popular_Manufacturer() {
        try {

            String sql = "SELECT Manufacturer, COUNT(Serial_num) as Rented_Items\n"
                    + "FROM (RENTAL_EQUIPMENT as r JOIN ELEMENT as e ON r.Serial_num = e.Serial_number )\n"
                    + "GROUP BY Manufacturer\n" + "ORDER BY Rented_Items DESC;";

            PreparedStatement stmt = DBConnection.getConnection()
                    .prepareStatement(sql);

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

    public static void Popular_Drone() {
        try {

            String sql = "SELECT ELEMENT.Model,ELEMENT.Name,ELEMENT.Manufacturer,ELEMENT.Year,ELEMENT.Address,ELEMENT.Serial_number, COUNT(ELEMENT.Serial_number) as Deliveries\n"
                    + "FROM ELEMENT,RENTAL_DRONE,RENTAL_EQUIPMENT\n"
                    + "WHERE ELEMENT.Serial_number=RENTAL_DRONE.Serial_num AND RENTAL_DRONE.Rental_num=RENTAL_EQUIPMENT.Rental_num\n"
                    + "GROUP BY ELEMENT.Serial_number\n"
                    + "ORDER BY COUNT(ELEMENT.Serial_number) DESC;";

            PreparedStatement stmt = DBConnection.getConnection()
                    .prepareStatement(sql);

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

    public static void Items_Checked_Out() {
        try {

            String sql = "Select Phone, First_name, Last_name, COUNT(Serial_num) as Total_Rented\n"
                    + "FROM ((Rental_Equipment JOIN Equipment USING(Serial_num))JOIN Rental ON Rental_num = Rental_number) JOIN PERSON USING (Phone)\n"
                    //+ "WHERE Equipment_Type = 'computer & internet'\n"
                    + "GROUP BY Phone\n" + "ORDER BY Total_Rented DESC\n"
                    + "Limit 1;";

            PreparedStatement stmt = DBConnection.getConnection()
                    .prepareStatement(sql);

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

    public static void Drone_trips() {
        try {

            String sql = "Select * FROM DRONE_TRIPS;";

            PreparedStatement stmt = DBConnection.getConnection()
                    .prepareStatement(sql);

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

    public static void Unpaid_rentals() {
        try {

            String sql = "SELECT * FROM UNPAID_RENTALS;";

            PreparedStatement stmt = DBConnection.getConnection()
                    .prepareStatement(sql);

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
