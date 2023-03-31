import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payments {

    public static void insert() {
        boolean again = false;
        do {
            again = false;
            System.out.println(
                    "_________________________________________________________________");
            System.out.println("ADD - PAYMENT");

            System.out.println("Enter the information as prompted.");
            System.out.print("Date_time: ");
            String date_time = App.input.nextLine();

            System.out.print("Type: ");
            String type = App.input.nextLine();

            System.out.print("Amount: ");
            String amount = App.input.nextLine();

            System.out.print("Rental_num: ");
            String rental_num = App.input.nextLine();

            System.out.print("Phone: ");
            String phone = App.input.nextLine();

            try {
                insertSQL(date_time, type, amount, rental_num, phone);
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
            System.out.println("EDIT - Payment");

            System.out.println("Enter the information as prompted.");
            System.out.print("Enter the date_time, rental_num, and phone: ");
            String payment_key = App.input.nextLine();
            String[] key = payment_key.split("\\s*,\\s*");
            try {

                if (selectSQL(key)) {
                    System.out.println("**Entry Found**");
                    System.out.println("Select Option Below");
                    System.out.println("[1] Edit\n[2] Delete \n[3] Main Menu");

                    System.out.print("Enter Here: ");

                    String selection = App.input.nextLine();

                    switch (selection) {
                        case "1":
                            System.out.println("Select an option to edit");
                            System.out.println(
                                    "[1] Type\n[2] Amount \n");
                            System.out.print("Enter Here: ");
                            selection = App.input.nextLine();
                            switch (selection) {
                                case "1":
                                    System.out.print("New Type: ");
                                    String Type = App.input.nextLine();
                                    updateSQL(key, Type, "Type");
                                    break;
                                case "2":
                                    System.out.print("New Amount: ");
                                    String Amount = App.input.nextLine();
                                    updateSQL(key, Amount, "Amount");
                                    break;
                                default:
                                    System.out.println("**Invalid Input < "
                                            + selection
                                            + " >, please enter integer between 1-2**");
                                    again = true;
                                    break;
                            }
                            System.out.println("Update Successful");

                            break;
                        case "2":
                            System.out.println(
                                    "Are you sure you want to delete < " + payment_key
                                            + " > ?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();

                            if (selection.equals("y")) {
                                deleteSQL(key);
                                System.out.println("Delete Successful");
                            }

                            System.out.println(
                                    "Would you like to update another?");
                            System.out.print("Enter Here [y/n]: ");
                            selection = App.input.nextLine();
                            if (selection.equals("y")) {
                                again = true;
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
                System.out.println("SEARCH - Payment");

                System.out.println("Select Option Below");
                System.out.println(
                        "[1] Search by Date_time, Rental_num, and Phone\n[2] Display all\n[3] Main Menu\n");
                System.out.print("Enter Here: ");

                String selection = App.input.nextLine();

                switch (selection) {
                    case "1":
                        System.out
                                .println("Enter the information as prompted.");
                        System.out.print("Enter the Date_time, Rental_num, and Phone to search for: ");
                        String payment_key = App.input.nextLine();
                        String[] key = payment_key.split("\\s*,\\s*");

                        if (selectSQL(key)) {
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

    public static void insertSQL(String date, String type, String amount, String rental_num, String phone) throws SQLException {
        String sql = "INSERT into Payment (Date_time, Type, Amount, Rental_num, Phone) values (?, ?, ?, ?, ?);";

        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        stmt.setString(1, date);
        stmt.setString(2, type);
        stmt.setString(3, amount);
        stmt.setString(4, rental_num);
        stmt.setString(5, phone);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateSQL(String[] key, String value, String attribute) throws SQLException {
        String sql = "UPDATE PAYMENT SET " + attribute
                + " = ? WHERE Date_time = ? " + "AND Rental_num = ? " + "AND Phone = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, value);
        stmt.setString(2, key[0]);
        stmt.setString(3, key[1]);
        stmt.setString(4, key[2]);

        stmt.executeUpdate();
        stmt.close();
    }

    public static void deleteSQL(String[] key) throws SQLException {
        String sql = "DELETE FROM PAYMENT WHERE Date_time = ? " + "AND Rental_num = ? " + "AND Phone = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, key[0]);
        stmt.setString(2, key[1]);
        stmt.setString(3, key[2]);

        stmt.executeUpdate();
        stmt.close();
    }

    public static boolean selectSQL(String[] key) throws SQLException {
        boolean found = true;
        String sql = "SELECT * FROM PAYMENT WHERE Date_time = ? " + "AND Rental_num = ? " + "AND Phone = ?;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);

        stmt.setString(1, key[0]);
        stmt.setString(2, key[1]);
        stmt.setString(3, key[2]);

        ResultSet results = stmt.executeQuery();
        if (Utilities.displayResults(results) == 0) {
            found = false;
            System.out.println("No Results Found");
        }

        stmt.close();

        return found;
    }
    
    public static void displayAll() throws SQLException {
        String sql = "SELECT * FROM PAYMENT;";
        PreparedStatement stmt = DBConnection.getConnection()
                .prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        Utilities.displayResults(results);
    }

}
