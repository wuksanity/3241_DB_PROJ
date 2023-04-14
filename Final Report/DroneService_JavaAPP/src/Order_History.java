import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Order_History {

    public static void insert() {
        boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("ADD - ORDER_HISTORY");

            System.out.println("Enter the information as prompted.");

            String date_time_arrival = Utilities.getDateTime();

            System.out.print("Description: ");
            String description = App.input.nextLine();

            System.out.print("Element_type: ");
            String element_type = App.input.nextLine();

            System.out.print("Value: ");
            String value = App.input.nextLine();

            System.out.print("Address: ");
            String address = App.input.nextLine();

            System.out.print("Serial_num: ");
            String serial_num = App.input.nextLine();

            try {
                insertSQL(date_time_arrival, description, element_type, value,
                        address, serial_num);
                System.out.println("Insertion Successful");
                System.out.println("Would you like to add another?");
                System.out.print("Enter Here [y/n]: ");
                String selection = App.input.nextLine();
                if (selection.equals("y")) {
                    again = true;
                }

            } catch (SQLException e) {
                System.out.println("**INSERTION FAILED**");
                System.out.println("Error Message: " + e);
                again = true;

            }
        } while (again);

        Menus.mainMenu();
    }

    public static void edit() {
        boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("EDIT - ORDER_HISTORY");

            System.out.println("Enter the information as prompted.");
            System.out.print("Enter the Date_time_arrival: ");
            String date_time_arrival = App.input.nextLine().trim();

            System.out.print("Enter the Address: ");
            String address = App.input.nextLine().trim();

            System.out.print("Enter the Serial_num: ");
            String serial_num = App.input.nextLine().trim();

            try {

                if (selectSQL(date_time_arrival, address, serial_num)) {
                    System.out.println("**Entry Found**");
                    System.out.println("Select Option Below");
                    System.out.println("[1] Edit\n[2] Delete \n[3] Main Menu");

                    System.out.print("Enter Here: ");

                    String selection = App.input.nextLine();

                    switch (selection) {
                        case "1":
                            System.out.println("Select an option to edit");
                            System.out.println(
                                    "[1] Description\n[2] Element_type \n[3] Value");
                            System.out.print("Enter Here: ");
                            selection = App.input.nextLine();
                            switch (selection) {
                                case "1":
                                    System.out.print("New Description: ");
                                    String description = App.input.nextLine();
                                    updateSQL(date_time_arrival, address,
                                            serial_num, description,
                                            "Description");
                                    System.out.println("Update Successful");
                                    break;
                                case "2":
                                    System.out.print("New Element_type: ");
                                    String element_type = App.input.nextLine();
                                    updateSQL(date_time_arrival, address,
                                            serial_num, element_type,
                                            "Element_type");
                                    System.out.println("Update Successful");
                                    break;
                                case "3":
                                    System.out.print("New Value: ");
                                    String value = App.input.nextLine();
                                    updateSQL(date_time_arrival, address,
                                            serial_num, value, "Value");
                                    System.out.println("Update Successful");
                                    break;
                                default:
                                    System.out.println("**Invalid Input < "
                                            + selection
                                            + " >, please enter integer between 1-3**");
                                    again = true;
                                    break;
                            }

                            break;
                        case "2":
                            System.out.println(
                                    "Are you sure you want to delete < "
                                            + date_time_arrival + " > < "
                                            + address + " > < " + serial_num
                                            + " > ?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();

                            if (selection.equals("y")) {
                                deleteSQL(date_time_arrival, address,
                                        serial_num);
                                System.out.println("Delete Successful");
                            }

                            break;
                        case "3":
                            Menus.mainMenu();

                            break;
                        default:
                            System.out.println("**Invalid Input < " + selection
                                    + " >, please enter integer between 1-3**");
                            again = true;
                            break;

                    }

                    System.out.println("Would you like to update another?");
                    System.out.print("Enter Here [y/n]: ");
                    selection = App.input.nextLine();
                    if (selection.equals("y")) {
                        again = true;
                    }

                } else {
                    System.out.println("ERROR: Entry NOT Found");
                    System.out.println("Would you like to try again?");
                    System.out.print("Enter Here [y/n]: ");
                    String selection = App.input.nextLine();
                    if (selection.equals("y")) {
                        again = true;
                    }
                }
            } catch (SQLException e) {
                System.out.println("**DATABASE UPDATE FAILED**");
                System.out.println("Error Message: " + e);
                again = true;

            }
        } while (again);

        Menus.mainMenu();

    }

    public static void search() {
        boolean again = false;

        do {
            try {
                again = false;
                System.out.println(
                        "_________________________________________________________________");
                System.out.println("SEARCH - ORDER_HISTORY");

                System.out.println("Select Option Below");
                System.out.println(
                        "[1] Search by Date_time_arrival, Address, and Serial_num\n[2] Display all\n[3] Main Menu\n");
                System.out.print("Enter Here: ");

                String selection = App.input.nextLine();

                switch (selection) {
                    case "1":
                        System.out
                                .println("Enter the information as prompted.");
                        System.out.print("Enter the Date_time_arrival: ");
                        String date_time_arrival = App.input.nextLine().trim();

                        System.out.print("Enter the Address: ");
                        String address = App.input.nextLine().trim();

                        System.out.print("Enter the Serial_num: ");
                        String serial_num = App.input.nextLine().trim();

                        if (selectSQL(date_time_arrival, address, serial_num)) {
                            System.out.println("**Entry Found**");
                        } else {
                            System.out.println("ERROR: Entry NOT Found");
                        }

                        break;
                    case "2":
                        displayAll();

                        break;
                    case "3":
                        Menus.mainMenu();
                        break;
                    default:
                        System.out.println("**Invalid Input < " + selection
                                + " >, please enter integer between 1-3**");
                        again = true;

                }

                System.out.println("Would you like to Search again?");
                System.out.print("Enter Here [y/n]: ");
                selection = App.input.nextLine();
                if (selection.equals("y")) {
                    again = true;
                }

            } catch (SQLException e) {
                System.out.println("**DATABASE SEARCH FAILED**");
                System.out.println("Error Message: " + e);
                again = true;
            }
        } while (again);
        Menus.mainMenu();
    }

    public static void insertSQL(String date_time_arrival, String description,
            String element_type, String value, String address,
            String serial_num) throws SQLException {
        String sql = "INSERT into ORDER_HISTORY (Date_time_arrival, Description, Element_type, Value, Address, Serial_num) values (?, ?, ?, ?, ?, ?);";

        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        stmt.setString(1, date_time_arrival);
        stmt.setString(2, description);
        stmt.setString(3, element_type);
        stmt.setString(4, value);
        stmt.setString(5, address);
        stmt.setString(6, serial_num);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateSQL(String date_time_arrival, String address,
            String serial_num, String value, String attribute)
            throws SQLException {
        String sql = "UPDATE ORDER_HISTORY SET " + attribute
                + " = ? WHERE Date_time_arrival = ? AND Address = ? AND Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, value);
        stmt.setString(2, date_time_arrival);
        stmt.setString(3, address);
        stmt.setString(4, serial_num);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void deleteSQL(String date_time_arrival, String address,
            String serial_num) throws SQLException {
        String sql = "DELETE FROM ORDER_HISTORY WHERE Date_time_arrival = ? AND Address = ? AND Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, date_time_arrival);
        stmt.setString(2, address);
        stmt.setString(3, serial_num);

        stmt.executeUpdate();
        stmt.close();
    }

    public static boolean selectSQL(String date_time_arrival, String address,
            String serial_num) throws SQLException {
        boolean found = true;
        String sql = "SELECT * FROM ORDER_HISTORY WHERE Date_time_arrival = ? AND Address = ? AND Serial_num = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, date_time_arrival);
        stmt.setString(2, address);
        stmt.setString(3, serial_num);

        ResultSet results = stmt.executeQuery();
        if (Utilities.displayResults(results) == 0) {
            found = false;
            System.out.println("No Results Found");
        }

        stmt.close();

        return found;
    }

    public static void displayAll() throws SQLException {
        String sql = "SELECT * FROM ORDER_HISTORY;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        Utilities.displayResults(results);
    }

}
