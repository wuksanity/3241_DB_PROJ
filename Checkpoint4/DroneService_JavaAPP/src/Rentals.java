import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rentals {

    public static void insert() {
        boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("ADD - RENTAL");

            System.out.println("Enter the information as prompted.");

            System.out.print("Due_date: ");
            String Due_date = App.input.nextLine();

            System.out.print("Status: ");
            String Status = App.input.nextLine();

            System.out.print("Payment_received: ");
            String Payment_received = App.input.nextLine();

            System.out.print("Rental_number: ");
            String Rental_number = App.input.nextLine();

            System.out.print("Rental_date_time: ");
            String Rental_date_time = App.input.nextLine();

            String Checkout_date_time = Utilities.getDateTime();

            System.out.print("Phone: ");
            String Phone = App.input.nextLine();

            System.out.print("Address: ");
            String Address = App.input.nextLine();

            try {
                insertSQL(Due_date, Status, Payment_received, Rental_number,
                        Rental_date_time, Checkout_date_time, Phone, Address);
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
            System.out.println("EDIT - RENTAL");

            System.out.println("Enter the information as prompted.");
            System.out.print("Enter the Rental_number: ");
            String Rental_number = App.input.nextLine().trim();
            try {

                if (selectSQL(Rental_number)) {
                    System.out.println("**Entry Found**");
                    System.out.println("Select Option Below");
                    System.out.println("[1] Edit\n[2] Delete \n[3] Main Menu");

                    System.out.print("Enter Here: ");

                    String selection = App.input.nextLine();

                    switch (selection) {
                        case "1":
                            System.out.println("Select an option to edit");
                            System.out.println(
                                    "[1] Due_date\n[2] Status \n[3] Payment_received");
                            System.out.print("Enter Here: ");
                            selection = App.input.nextLine();
                            switch (selection) {
                                case "1":
                                    System.out.print("New Due_date: ");
                                    String due_date = App.input.nextLine();
                                    updateSQL(Rental_number, due_date,
                                            "Due_date");
                                    break;
                                case "2":
                                    System.out.print("New Status: ");
                                    String status = App.input.nextLine();
                                    updateSQL(Rental_number, status, "Status");

                                    break;
                                case "3":
                                    System.out.print("New Payment Status: ");
                                    String payment_received = App.input
                                            .nextLine();
                                    updateSQL(Rental_number, payment_received,
                                            "Payment_received");

                                    break;
                                default:
                                    System.out.println("**Invalid Input < "
                                            + selection
                                            + " >, please enter integer between 1-3**");
                                    again = true;
                                    break;
                            }
                            System.out.println("Update Successful");

                            break;
                        case "2":
                            System.out.println(
                                    "Are you sure you want to delete < "
                                            + Rental_number + " > ?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();

                            if (selection.equals("y")) {
                                deleteSQL(Rental_number);
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
                System.out.println("SEARCH - RENTAL");

                System.out.println("Select Option Below");
                System.out.println(
                        "[1] Search by Rental_number \n[2] Display all\n[3] Main Menu\n");
                System.out.print("Enter Here: ");

                String selection = App.input.nextLine();

                switch (selection) {
                    case "1":
                        System.out
                                .println("Enter the information as prompted.");
                        System.out.print("Enter the Address to search for: ");
                        String Rental_number = App.input.nextLine().trim();

                        if (selectSQL(Rental_number)) {
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

    public static void insertSQL(String due, String status, String payment_rec,
            String rent_num, String rent_date, String checkout_date,
            String phone_num, String addr) throws SQLException {
        String sql = "INSERT into RENTAL (Due_date, Status, Payment_received, Rental_number, Rental_date_time, Checkout_date_time, Phone, Address) values (?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        stmt.setString(1, due);
        stmt.setString(2, status);
        stmt.setString(3, payment_rec);
        stmt.setString(4, rent_num);
        stmt.setString(5, rent_date);
        stmt.setString(6, checkout_date);
        stmt.setString(7, phone_num);
        stmt.setString(8, addr);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateSQL(String Rental_number, String value,
            String attribute) throws SQLException {
        String sql = "UPDATE RENTAL SET " + attribute
                + " = ? WHERE Rental_number = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, value);
        stmt.setString(2, Rental_number);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void deleteSQL(String Rental_number) throws SQLException {
        String sql = "DELETE FROM RENTAL WHERE Rental_number = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, Rental_number);

        stmt.executeUpdate();
        stmt.close();
    }

    public static boolean selectSQL(String Rental_number) throws SQLException {
        boolean found = true;
        String sql = "SELECT * FROM WAREHOUSE WHERE Address = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, Rental_number);

        ResultSet results = stmt.executeQuery();
        if (Utilities.displayResults(results) == 0) {
            found = false;
            System.out.println("No Results Found");
        }

        stmt.close();

        return found;
    }

    public static void displayAll() throws SQLException {
        String sql = "SELECT * FROM RENTAL;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        Utilities.displayResults(results);
    }

}
